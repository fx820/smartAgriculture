<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-form ref="queryFormRef" :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="大棚名">
        <el-input v-model="queryParams.keyword" style="width: 200px" placeholder="请输入大棚名称" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb15">
      <el-col :span="1.5">
        <el-button type="danger" plain :disabled="environmentList.length === 0" icon="Delete"
          @click="handleDelete(undefined)">批量删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <!-- 表格展示 -->
    <el-table border :data="environmentList" @selection-change="handleSelectionChange" v-loading="loading">
      <!-- 表格列 -->
      <el-table-column type="selection" width="55" align="center"></el-table-column>
        <!-- 编号 -->
        <el-table-column prop="id" width="100" label="编号" align="center"></el-table-column>
      <!-- 区域名 -->
      <el-table-column prop="name" width="280" label="大棚名" align="center"></el-table-column>
      <!-- 区域所在地区 -->
      <el-table-column prop="airTemperature" width="100" label="空气温度" align="center"></el-table-column>
      <el-table-column prop="airHumidity" width="100" label="空气湿度" align="center"></el-table-column>
      <el-table-column prop="soilTemperature" width="100" label="土壤温度" align="center"></el-table-column>
      <el-table-column prop="soilHumidity" width="100" label="土壤湿度" align="center"></el-table-column>
      <el-table-column prop="c02Level" width="100" label="二氧化碳含量" align="center"></el-table-column>
      <el-table-column prop="lightLevel" width="100" label="光照度" align="center"></el-table-column>
<!--      <el-table-column prop="sensorIds" width="100" label="传感器编号" align="center"></el-table-column>-->
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
      <!-- 操作 -->
      <el-table-column width="270" label="操作" align="center">
        <template #default="scope">
          <el-button v-if="scope.row.id !== '1'" type="danger" icon="Delete" link @click="handleDelete(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination v-if="count > 0" :total="count" v-model:page="queryParams.current" v-model:limit="queryParams.size"
      @pagination="getList" />
  </div>
</template>

<script setup lang="ts">
import { formatDateTime } from "@/utils/date";
import { messageConfirm, notifySuccess } from "@/utils/modal";
import {FormInstance, FormRules, UploadRawFile} from 'element-plus';
import {computed, nextTick, onMounted, reactive, ref, toRefs} from "vue";
import {getToken, token_prefix} from "@/utils/token";
import {Environment, EnvironmentQuery} from "@/api/environment/types";
import {deleteEnvironment, getEnvironmentList} from "@/api/environment";
const zoneFormRef = ref<FormInstance>();

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
  } as EnvironmentQuery,
  environmentIdList: [] as number[],
  environmentList: [] as Environment[],
});
const {
  count,
  showSearch,
  loading,
  title,
  queryParams,
  environmentList,
  environmentIdList,
} = toRefs(data);

const handleSelectionChange = (selection: Environment[]) => {
  environmentIdList.value = selection.map((item) => item.id);
};
const reset = () => {
  zoneFormRef.value?.clearValidate();
};


const handleDelete = (id?: number) => {
  let ids: number[] = [];
  if (id == undefined) {
    ids = environmentIdList.value;
  } else {
    ids = [id];
  }
  messageConfirm("确认删除已选中的数据项?").then(() => {
      //查询是否有相关的大棚信息 若有，则无法删除
      deleteEnvironment(ids).then(({ data }) => {
          if (data.flag) {
              notifySuccess(data.msg);
              getList();
          }
      });
  }).catch(() => { });
};
const getList = () => {
  loading.value = true;
  getEnvironmentList(queryParams.value).then(({ data }) => {
    environmentList.value = data.data.recordList;
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
