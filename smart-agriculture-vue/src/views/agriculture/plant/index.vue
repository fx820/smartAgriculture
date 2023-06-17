<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-form ref="queryFormRef" :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="农作物名称">
        <el-input v-model="queryParams.keyword" style="width: 200px" placeholder="请输入农作物名称" clearable
          @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="农作物种植状态" clearable style="width: 200px">
          <el-option v-for="item in status" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格展示 -->
    <el-table border :data="plantList"  v-loading="loading">
      <!-- 农作物名 -->
      <el-table-column prop="productName" width="200" label="农作物名" align="center"></el-table-column>
      <!-- 农作物种植周期 -->
      <el-table-column prop="houseName" width="180" label="大棚名" align="center"></el-table-column>
      <!-- 状态 -->
      <el-table-column prop="status" label="收割状态" align="center" width="160">
        <template #default="scope">
            <el-tag v-if="scope.row.status == 0" type="info">种植中</el-tag>
            <el-tag v-if="scope.row.status == 1" type="warning">等待收割</el-tag>
            <el-tag v-if="scope.row.status == 2" type="success">完成</el-tag>
        </template>
      </el-table-column>
      <!-- 创建时间 -->
      <el-table-column prop="plantTime" width="270" label="种植时间" align="center">
        <template #default="scope">
          <div class="create-time">
            <el-icon>
              <clock />
            </el-icon>
            <span style="margin-left: 10px">{{ formatDateTime(scope.row.plantTime) }}</span>
          </div>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column width="270" label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" icon="Edit" link @click="cut(scope.row.id)" v-if="scope.row.status == 0">
            收割
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
import {ProductQuery} from "@/api/product/types";
import { formatDateTime } from "@/utils/date";
import {computed, nextTick, onMounted, reactive, ref, toRefs} from "vue";
import {getToken, token_prefix} from "@/utils/token";
import {Plant} from "@/api/plant/types";
import {cutProduct, getPlantList} from "@/api/plant";
import {notifySuccess} from "@/utils/modal";

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
  queryParams: {
    current: 1,
    size: 10,
  } as ProductQuery,
  status: [
    {
      value: 0,
      label: "未种植",
    },
    {
      value: 1,
      label: "种植中",
    },
  ],
  plantList: [] as Plant[]
});
const {
  count,
  showSearch,
  loading,
  title,
  queryParams,
  status,
  plantList,
} = toRefs(data);



const getList = () => {
  loading.value = true;
  getPlantList(queryParams.value).then(({ data }) => {
    plantList.value = data.data.recordList;
    count.value = data.data.count;
    loading.value = false;
  });
};

const handleQuery = () => {
  queryParams.value.current = 1;
  getList();
};

const cut = (id:number) => {
  cutProduct(id).then(({ data }) => {
    if (data.flag) {
      notifySuccess(data.msg);
      getList();
    }
})
}
onMounted(() => {
  getList();
});
</script>

<style scoped></style>
