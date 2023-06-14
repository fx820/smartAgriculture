<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-form ref="queryFormRef" :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="区域名称">
        <el-input v-model="queryParams.keyword" style="width: 200px" placeholder="请输入区域名称" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="可用状态">
        <el-select v-model="queryParams.status" placeholder="区域可用状态" clearable style="width: 200px">
          <el-option v-for="item in status" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb15">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="openModel(undefined)">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain :disabled="zoneIdList.length === 0" icon="Delete"
          @click="handleDelete(undefined)">批量删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <!-- 表格展示 -->
    <el-table border :data="zoneList" @selection-change="handleSelectionChange" v-loading="loading">
      <!-- 表格列 -->
      <el-table-column type="selection" width="55" align="center"></el-table-column>
        <!-- 编号 -->
        <el-table-column prop="id" width="100" label="编号" align="center"></el-table-column>
      <!-- 区域名 -->
      <el-table-column prop="name" width="280" label="区域名" align="center"></el-table-column>
      <!-- 区域所在地区 -->
      <el-table-column prop="address" width="300" label="所在地区" align="center"></el-table-column>
      <!-- 状态 -->
      <el-table-column prop="status" label="区域使用状态" align="center" width="130px">
        <template #default="scope">
            <el-tag v-if="scope.row.status == 1" type="info">已满</el-tag>
            <el-tag v-if="scope.row.status == 2" type="success">未满</el-tag>
        </template>
      </el-table-column>
      <!-- 创建时间 -->
      <el-table-column prop="createTime" width="270" label="创建时间" align="center">
        <template #default="scope">
          <div class="create-time">
            <el-icon>
              <clock />
            </el-icon>
            <span style="margin-left: 10px">{{ formatDateTime(scope.row.createTime) }}</span>
          </div>
        </template>
      </el-table-column>
          <!-- 上一次更新时间 -->
          <el-table-column prop="updateTime" width="270" label="上一次更新时间" align="center">
              <template #default="scope">
                  <div class="create-time" v-if="scope.row.updateTime != null">
                      <el-icon>
                          <clock />
                      </el-icon>
                      <span style="margin-left: 10px">{{ formatDateTime(scope.row.updateTime) }}</span>
                  </div>
                  <div class="create-time" v-if="scope.row.updateTime == null">
                      <el-icon>
                          <clock />
                      </el-icon>
                      <span style="margin-left: 10px">{{ formatDateTime(scope.row.createTime) }}</span>
                  </div>
              </template>
          </el-table-column>
      <!-- 操作 -->
      <el-table-column width="270" label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" icon="Edit" link @click="openModel(scope.row)">
            编辑
          </el-button>
          <el-button v-if="scope.row.id !== '1'" type="danger" icon="Delete" link @click="handleDelete(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination v-if="count > 0" :total="count" v-model:page="queryParams.current" v-model:limit="queryParams.size"
      @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" v-model="addOrUpdate" width="500px" append-to-body>
      <el-form ref="zoneFormRef" :model="zoneForm" :rules="rules" label-width="100px">
        <el-form-item label="区域名称" prop="name">
          <el-input placeholder="请输入区域名称" v-model="zoneForm.name" />
        </el-form-item>
          <el-form-item label="区域地址" prop="address">
              <el-input placeholder="请输入区域地址" v-model="zoneForm.address" />
          </el-form-item>
        <el-form-item label="可用状态">
          <el-radio-group v-model="zoneForm.status">
            <el-radio v-for="dict in status" :key="dict.value" :label="dict.value">
              {{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="addOrUpdate = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import {addZone, deleteZone, getZoneList, hasHouse, updateZone} from "@/api/zone";
import { Zone, ZoneForm, ZoneQuery } from "@/api/zone/types";
import { formatDateTime } from "@/utils/date";
import { messageConfirm, notifySuccess } from "@/utils/modal";
import {FormInstance, FormRules, UploadRawFile} from 'element-plus';
import {computed, nextTick, onMounted, reactive, ref, toRefs} from "vue";
import {getToken, token_prefix} from "@/utils/token";
import * as imageConversion from "image-conversion";
import {AxiosResponse} from "axios/index";
import {getHouseList} from "@/api/house";
const zoneFormRef = ref<FormInstance>();
const rules = reactive<FormRules>({
  name: [{ required: true, message: "请输入区域名称", trigger: "blur" }],
  address: [{ required: true, message: "请输入该区域所在地址", trigger: "blur" }]
});

const authorization = computed(() => {
    return {
        Authorization: token_prefix + getToken(),
    }
});

const data = reactive({
  count: 0,
  showSearch: true,
  loading: false,
  title: "",
  addOrUpdate: false,
  queryParams: {
    current: 1,
    size: 10,
  } as ZoneQuery,
  status: [
    {
      value: 1,
      label: "已满",
    },
    {
      value: 2,
      label: "未满",
    },
  ],
  zoneForm: {} as ZoneForm,
  zoneIdList: [] as number[],
  zoneList: [] as Zone[],
});
const {
  count,
  showSearch,
  loading,
  title,
  addOrUpdate,
  queryParams,
  status,
  zoneForm,
  zoneIdList,
  zoneList,
} = toRefs(data);

const handleSelectionChange = (selection: Zone[]) => {
  zoneIdList.value = selection.map((item) => item.id);
};
const reset = () => {
  zoneFormRef.value?.clearValidate();
};
const openModel = async (zone?: Zone) => {
  reset();
  if (zone !== undefined) {
    title.value = "修改区域";
    zoneForm.value.id = zone.id;
    zoneForm.value.name = zone.name;
    zoneForm.value.address = zone.address;
    zoneForm.value.status = zone.status;
  } else {
    title.value = "添加区域";
    zoneForm.value = {
      id: undefined,
      name: "",
      zoneId: 1,
      status: 2,
    };
  }
  addOrUpdate.value = true;
};
const submitForm = () => {
  zoneFormRef.value?.validate((valid) => {
    if (valid) {
      if (zoneForm.value.id !== undefined) {
        updateZone(zoneForm.value).then(({ data }) => {
          if (data.flag) {
            notifySuccess(data.msg);
            getList();
          }
          addOrUpdate.value = false;
        })
      } else {
        addZone(zoneForm.value).then(({ data }) => {
          if (data.flag) {
            notifySuccess(data.msg);
            getList();
          }
          addOrUpdate.value = false;
        })
      }
    }
  })
};


const handleDelete = (id?: number) => {
  let ids: number[] = [];
  if (id == undefined) {
    ids = zoneIdList.value;
  } else {
    ids = [id];
  }
    //查询是否有相关的大棚信息 若有，则无法删除
    hasHouse(ids).then(({ data }) => {
        if (data.flag) {
            notifySuccess(data.msg);
            getList();
        }
    });

  messageConfirm("确认删除已选中的数据项?").then(() => {
      //查询是否有相关的大棚信息 若有，则无法删除
      deleteZone(ids).then(({ data }) => {
          if (data.flag) {
              notifySuccess(data.msg);
              getList();
          }
      });
  }).catch(() => { });
};
const getList = () => {
  loading.value = true;
  getZoneList(queryParams.value).then(({ data }) => {
    zoneList.value = data.data.recordList;
    count.value = data.data.count;
    loading.value = false;
  });
};

const handleQuery = () => {
  queryParams.value.current = 1;
  getList();
};
onMounted(() => {
  getList();
});
</script>

<style scoped></style>
