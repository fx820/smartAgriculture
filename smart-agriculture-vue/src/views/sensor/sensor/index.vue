<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-form ref="queryFormRef" :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="设备名称">
        <el-input v-model="queryParams.keyword" style="width: 200px" placeholder="请输入设备名称" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="设备状态" clearable style="width: 200px">
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
        <el-button type="danger" plain :disabled="sensorIdList.length === 0" icon="Delete"
          @click="handleDelete(undefined)">批量删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <!-- 表格展示 -->
    <el-table border :data="sensorList" @selection-change="handleSelectionChange" v-loading="loading">
      <!-- 表格列 -->
      <el-table-column type="selection" width="55" align="center"></el-table-column>
        <!-- 编号 -->
        <el-table-column prop="id" width="100" label="编号" align="center"></el-table-column>
      <!-- 设备名 -->
      <el-table-column prop="name" width="280" label="设备名" align="center"></el-table-column>
      <!-- 设备种植周期 -->
      <el-table-column prop="type" width="300" label="设备类型" align="center"></el-table-column>
      <!-- 状态 -->
      <el-table-column prop="status" label="设备状态" align="center">
        <template #default="scope">
            <el-tag v-if="scope.row.status == 1" type="info">下线</el-tag>
            <el-tag v-if="scope.row.status == 2" type="success">在线</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="设备图片" align="center" #default="scope">
          <el-image :src="scope.row.image"/>
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
        <!-- 创建时间 -->
        <el-table-column prop="updateTime" width="270" label="上一次修改时间" align="center">
            <template #default="scope">
                <div class="create-time">
                    <el-icon>
                        <clock />
                    </el-icon>
                    <span style="margin-left: 10px" v-if="scope.row.updateTime!=null">{{ formatDateTime(scope.row.updateTime) }}</span>
                    <span style="margin-left: 10px" v-else>{{ formatDateTime(scope.row.createTime) }}</span>
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
      <el-form ref="sensorFormRef" :model="sensorForm" :rules="rules" label-width="100px">
        <el-form-item label="设备名称" prop="name">
          <el-input placeholder="请输入设备名称" v-model="sensorForm.name" />
        </el-form-item>
        <el-form-item label="设备状态">
          <el-radio-group v-model="sensorForm.status">
            <el-radio v-for="dict in status" :key="dict.value" :label="dict.value">
              {{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
          <el-form-item label="设备类型" prop="name">
              <el-input placeholder="请输入设备类型" v-model="sensorForm.type" />
          </el-form-item>
          <el-form-item label="设备图片封面" prop="image">
              <el-upload drag :show-file-list="false" :headers="authorization" action="/api/sensor/upload"
                         accept="image/*" :before-upload="beforeUpload" :on-success="handleSuccess">
                  <el-icon class="el-icon--upload" v-if="sensorForm.image === ''"><upload-filled /></el-icon>
                  <div class="el-upload__text" v-if="sensorForm.image === ''">
                      将文件拖到此处，或<em>点击上传</em>
                  </div>
                  <img v-else :src="sensorForm.image" width="180" />
              </el-upload>
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
import { addSensor, deleteSensor, getSensorList, updateSensor } from "@/api/sensor";
import { Sensor, SensorForm, SensorQuery } from "@/api/sensor/types";
import { formatDateTime } from "@/utils/date";
import { messageConfirm, notifySuccess } from "@/utils/modal";
import {FormInstance, FormRules, UploadRawFile} from 'element-plus';
import {computed, nextTick, onMounted, reactive, ref, toRefs} from "vue";
import {getToken, token_prefix} from "@/utils/token";
import * as imageConversion from "image-conversion";
import {AxiosResponse} from "axios/index";
const sensorFormRef = ref<FormInstance>();
const rules = reactive<FormRules>({
  name: [{ required: true, message: "请输入设备名称", trigger: "blur" }],
  type: [{ required: true, message: "请输入设备类型", trigger: "blur" }]
});

const authorization = computed(() => {
    return {
        Authorization: token_prefix + getToken(),
    }
});

const handleSuccess = (response: AxiosResponse) => {
    sensorForm.value.image = response.data;
};

const beforeUpload = (rawFile: UploadRawFile) => {
    return new Promise(resolve => {
        if (rawFile.size / 1024 < 200) {
            resolve(rawFile);
        }
        // 压缩到200KB,这里的200就是要压缩的大小,可自定义
        imageConversion
            .compressAccurately(rawFile, 200)
            .then(res => {
                resolve(res);
            });
    });
};

const data = reactive({
  count: 0,
  showSearch: true,
  loading: false,
  title: "",
  addOrUpdate: false,
  queryParams: {
    current: 1,
    size: 10,
  } as SensorQuery,
  status: [
    {
      value: 1,
      label: "下线",
    },
    {
      value: 2,
      label: "在线",
    },
  ],
  sensorForm: {} as SensorForm,
  sensorIdList: [] as number[],
  sensorList: [] as Sensor[],
});
const {
  count,
  showSearch,
  loading,
  title,
  addOrUpdate,
  queryParams,
  status,
  sensorForm,
  sensorIdList,
  sensorList,
} = toRefs(data);

const handleSelectionChange = (selection: Sensor[]) => {
  sensorIdList.value = selection.map((item) => item.id);
};
const reset = () => {
  sensorFormRef.value?.clearValidate();
};
const openModel = async (sensor?: Sensor) => {
  reset();
  if (sensor !== undefined) {
    title.value = "修改设备";
    sensorForm.value.id = sensor.id;
    sensorForm.value.name = sensor.name;
    sensorForm.value.type = sensor.type;
    sensorForm.value.status = sensor.status;
    sensorForm.value.image = sensor.image;
  } else {
    title.value = "添加设备";
    sensorForm.value = {
      id: undefined,
      name: "",
      type: "",
      status: 1,
      image: "",
    };
  }
  addOrUpdate.value = true;
};
const submitForm = () => {
  sensorFormRef.value?.validate((valid) => {
    if (valid) {
      if (sensorForm.value.id !== undefined) {
        updateSensor(sensorForm.value).then(({ data }) => {
          if (data.flag) {
            notifySuccess(data.msg);
            getList();
          }
          addOrUpdate.value = false;
        })
      } else {
        addSensor(sensorForm.value).then(({ data }) => {
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
    ids = sensorIdList.value;
  } else {
    ids = [id];
  }
  messageConfirm("确认删除已选中的数据项?").then(() => {
    deleteSensor(ids).then(({ data }) => {
      if (data.flag) {
        notifySuccess(data.msg);
        getList();
      }
    });
  }).catch(() => { });
};
const getList = () => {
  loading.value = true;
  getSensorList(queryParams.value).then(({ data }) => {
    sensorList.value = data.data.recordList;
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
