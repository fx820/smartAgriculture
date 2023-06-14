<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-form ref="queryFormRef" :model="queryParams" :inline="true" v-show="showSearch">
        <el-form-item label="大棚">
            <el-select v-model="queryParams.greenHouseId" placeholder="大棚" clearable style="width: 200px">
                <el-option v-for="item in houseEnable" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
        </el-form-item>
        <el-form-item label="农作物 ">
            <el-select v-model="queryParams.productId" placeholder="农作物" clearable style="width: 200px">
                <el-option v-for="item in productEnable" :key="item.id" :label="item.name" :value="item.id" />
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
        <el-button type="danger" plain :disabled="pestIdList.length === 0" icon="Delete"
          @click="handleDelete(undefined)">批量删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <!-- 表格展示 -->
    <el-table border :data="pestList" @selection-change="handleSelectionChange" v-loading="loading">
      <!-- 表格列 -->
      <el-table-column type="selection" width="55" align="center"></el-table-column>
        <!-- 编号 -->
        <el-table-column prop="id" width="100" label="编号" align="center"></el-table-column>
      <!-- 大棚名 -->
      <el-table-column prop="greenHouseName" width="180" label="大棚名" align="center"></el-table-column>
      <!-- 农作物名 -->
      <el-table-column prop="productName" width="180" label="农作物名" align="center"></el-table-column>
      <el-table-column label="监测图片" align="center" #default="scope" width="180px" >
          <el-image :src="scope.row.imageSrc" />
      </el-table-column>
        <!-- 识别结果 -->
        <el-table-column prop="result" width="180" label="识别结果" align="center"></el-table-column>
        <!-- 识别准确率 -->
        <el-table-column prop="accuracy" width="180" label="识别准确率" align="center"></el-table-column>
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

    <!-- 添加-->
    <el-dialog :title="title" v-model="add" width="500px" append-to-body>
      <el-form ref="pestFormRef" :model="addPestForm" label-width="100px">
          <el-form-item label="大棚">
              <el-select v-model="addPestForm.greenHouseId" placeholder="大棚" clearable style="width: 200px">
                  <el-option v-for="item in houseEnable" :key="item.name" :label="item.name" :value="item.id" />
              </el-select>
          </el-form-item>
          <el-form-item label="农作物">
              <el-select v-model="addPestForm.productId" placeholder="农作物" clearable style="width: 200px">
                  <el-option v-for="item in productEnable" :key="item.name" :label="item.name" :value="item.id" />
              </el-select>
          </el-form-item>
          <el-form-item label="相册封面" prop="imageSrc">
              <el-upload drag :show-file-list="false" :headers="authorization" action="/api/pest/upload"
                         accept="image/*" :before-upload="beforeUpload" :on-success="handleAddSuccess">
                  <el-icon class="el-icon--upload" v-if="addPestForm.imageSrc === ''"><upload-filled /></el-icon>
                  <div class="el-upload__text" v-if="addPestForm.imageSrc === ''">
                      将文件拖到此处，或<em>点击上传</em>
                  </div>
                  <img v-else :src="addPestForm.imageSrc" width="180" />
              </el-upload>
          </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="add = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>

  <!--   修改   -->
      <el-dialog :title="title" v-model="update" width="500px" append-to-body>
          <el-form ref="pestFormRef" :model="updatePestForm" label-width="100px">
              <el-form-item label="相册封面" prop="imageSrc">
                  <el-upload drag :show-file-list="false" :headers="authorization" action="/api/pest/upload"
                             accept="image/*" :before-upload="beforeUpload" :on-success="handleUpdateSuccess">
                      <el-icon class="el-icon--upload" v-if="updatePestForm.imageSrc === ''"><upload-filled /></el-icon>
                      <div class="el-upload__text" v-if="updatePestForm.imageSrc === ''">
                          将文件拖到此处，或<em>点击上传</em>
                      </div>
                      <img v-else :src="updatePestForm.imageSrc" width="180" />
                  </el-upload>
              </el-form-item>
          </el-form>
          <template #footer>
              <div class="dialog-footer">
                  <el-button type="primary" @click="submitForm">确 定</el-button>
                  <el-button @click="update = false">取 消</el-button>
              </div>
          </template>
      </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { addPest, deletePest, getPestList, updatePest } from "@/api/pest";
import { Pest, AddPestForm,UpdatePestForm, PestQuery } from "@/api/pest/types";
import { formatDateTime } from "@/utils/date";
import { messageConfirm, notifySuccess } from "@/utils/modal";
import {FormInstance, FormRules, UploadRawFile} from 'element-plus';
import {computed, nextTick, onMounted, reactive, ref, toRefs} from "vue";
import {getToken, token_prefix} from "@/utils/token";
import * as imageConversion from "image-conversion";
import {AxiosResponse} from "axios/index";
import {Product} from "@/api/product/types";
import {getHouses} from "@/api/house";
import {getProductAll} from "@/api/product";
import {House} from "@/api/house/types";
import {hooks} from "prismjs";
const pestFormRef = ref<FormInstance>();

const authorization = computed(() => {
    return {
        Authorization: token_prefix + getToken(),
    }
});

const handleAddSuccess = (response: AxiosResponse) => {
    addPestForm.value.imageSrc = response.data;
};

const handleUpdateSuccess = (response: AxiosResponse) => {
    updatePestForm.value.imageSrc = response.data;
};

const beforeUpload = (rawFile: UploadRawFile) => {
    return new Promise(resolve => {
        if (rawFile.size / 1024 < 200) {
            resolve(rawFile);
        }
        // 压缩到200KB,这里的200就是要压缩的大小,可自定义
        imageConversion
            .compressAccurately(rawFile, 50)
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
  add: false,
  update: false,
  queryParams: {
    current: 1,
    size: 10,
  } as PestQuery,
  addPestForm: {} as AddPestForm,
  updatePestForm: {} as UpdatePestForm,
  pestIdList: [] as number[],
  pestList: [] as Pest[],
  productEnable: [] as Product[],
  houseEnable: [] as House[]
});
const {
  count,
  showSearch,
  loading,
  title,
  add,
  update,
  queryParams,
  addPestForm,
  updatePestForm,
  pestIdList,
  pestList,
  productEnable,
  houseEnable
} = toRefs(data);

const handleSelectionChange = (selection: Pest[]) => {
  pestIdList.value = selection.map((item) => item.id);
};
const reset = () => {
  pestFormRef.value?.clearValidate();
};
const openModel = async (pest?: Pest) => {
    reset();
    if (pest !== undefined) {
        title.value = "修改监测图片";
        updatePestForm.value.id = pest.id;
        updatePestForm.value.imageSrc = pest.imageSrc;
        update.value = true;
    } else {
        title.value = "添加监测信息";
        addPestForm.value = {
            id: undefined,
            greenHouseId: undefined,
            productId: undefined,
            imageSrc: ""
        };
        add.value = true;
    }
};

const submitForm = () => {
  pestFormRef.value?.validate((valid) => {
    if (valid) {
      if (updatePestForm.value.id != undefined) {
        updatePest(updatePestForm.value).then(({ data }) => {
          if (data.flag) {
            notifySuccess(data.msg);
            getList();
          }
          update.value = false;
        })
      } else {
        addPest(addPestForm.value).then(({ data }) => {
          if (data.flag) {
            notifySuccess(data.msg);
            getList();
          }
          add.value = false;
        })
      }
    }
  })
};

const handleDelete = (id?: number) => {
  let ids: number[] = [];
  if (id == undefined) {
    ids = pestIdList.value;
  } else {
    ids = [id];
  }
  messageConfirm("确认删除已选中的数据项?").then(() => {
    deletePest(ids).then(({ data }) => {
      if (data.flag) {
        notifySuccess(data.msg);
        getList();
      }
    });
  }).catch(() => { });
};
const getList = () => {
  loading.value = true;
  getPestList(queryParams.value).then(({ data }) => {
    pestList.value = data.data.recordList;
    count.value = data.data.count;
    loading.value = false;
  });
};

const getProducts = () =>{
    getProductAll().then(({data})=>{
        productEnable.value = data.data;
    })
}

const getHouseAll = () =>{
    getHouses().then(({data})=>{
        houseEnable.value = data.data;
    })
}

const handleQuery = () => {
  queryParams.value.current = 1;
  getList();
};
onMounted(() => {
  getList();
  getProducts();
  getHouseAll();
});
</script>

<style scoped></style>
