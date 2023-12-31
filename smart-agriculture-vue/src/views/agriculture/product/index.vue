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
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb15">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="openModel(undefined)">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain :disabled="productIdList.length === 0" icon="Delete"
          @click="handleDelete(undefined)">批量删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <!-- 表格展示 -->
    <el-table border :data="productList" @selection-change="handleSelectionChange" v-loading="loading">
      <!-- 表格列 -->
      <el-table-column type="selection" width="55" align="center"></el-table-column>
        <!-- 编号 -->
        <el-table-column prop="id" width="100" label="编号" align="center"></el-table-column>
      <!-- 农作物名 -->
      <el-table-column prop="name" width="200" label="农作物名" align="center"></el-table-column>
      <!-- 农作物种植周期 -->
      <el-table-column prop="cycle" width="180" label="种植周期(天)" align="center"></el-table-column>
      <!-- 状态 -->
      <el-table-column prop="status" label="种植状态" align="center" width="160">
        <template #default="scope">
            <el-tag v-if="scope.row.status == 0" type="info">未种植</el-tag>
            <el-tag v-if="scope.row.status == 1" type="success">种植中</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="农作物图片" align="center" #default="scope" width="180">
          <el-image :src="scope.row.photo" style="width: 120px;height: 120px"/>
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
      <!-- 操作 -->
      <el-table-column width="270" label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" icon="Edit" link @click="openPlantModel(scope.row)">
            种植
          </el-button>
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
      <el-form ref="productFormRef" :model="productForm" :rules="rules" label-width="100px">
        <el-form-item label="农作物名称" prop="name" v-if="productForm.name=='' ">
          <el-input placeholder="请输入农作物名称" v-model="productForm.name" />
        </el-form-item>
        <el-form-item label="农作物名称" prop="name" v-else>
          <el-input placeholder="请输入农作物名称" v-model="productForm.name" disabled />
        </el-form-item>
          <el-form-item label="种植周期(天)">
              <el-input-number
                      v-model="productForm.cycle"
                      class="mx-4"
                      :min="1"
                      :max="365"
                      controls-position="right"
              />
          </el-form-item>
          <el-form-item label="相册封面" prop="albumCover">
              <el-upload drag :show-file-list="false" :headers="authorization" action="/api/product/upload"
                         accept="image/*" :before-upload="beforeUpload" :on-success="handleSuccess">
                  <el-icon class="el-icon--upload" v-if="productForm.photo === ''"><upload-filled /></el-icon>
                  <div class="el-upload__text" v-if="productForm.photo === ''">
                      将文件拖到此处，或<em>点击上传</em>
                  </div>
                  <img v-else :src="productForm.photo" width="180" />
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

    <!--  种植农作物  -->
    <el-dialog :title="title" v-model="plantModel" width="500px" append-to-body>
      <el-form :model="plantForm" :rules="plantRules" label-width="100px">
        <el-form-item label="农作物名称" prop="name">
          <el-input placeholder="请输入农作物名称" v-model="productForm.name" disabled/>
        </el-form-item>

        <el-form-item label="种植大棚" prop="houseId">
          <el-select v-model="plantForm.greenHouseId" placeholder="大棚所在区域" clearable style="width: 200px">
            <el-option v-for="item in allHouse" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitPlantForm">确 定</el-button>
          <el-button @click="plantModel = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import {addProduct, deleteProduct, getProductList, plant, updateProduct} from "@/api/product";
import {PlantForm, Product, ProductForm, ProductQuery} from "@/api/product/types";
import { formatDateTime } from "@/utils/date";
import { messageConfirm, notifySuccess } from "@/utils/modal";
import {FormInstance, FormRules, UploadRawFile} from 'element-plus';
import {computed, nextTick, onMounted, reactive, ref, toRefs} from "vue";
import {getToken, token_prefix} from "@/utils/token";
import * as imageConversion from "image-conversion";
import {AxiosResponse} from "axios/index";
import {getHouses} from "@/api/house";
import {AllHouse} from "@/api/house/types";
const productFormRef = ref<FormInstance>();
const plantFormRef = ref<FormInstance>();
const rules = reactive<FormRules>({
  name: [{ required: true, message: "请输入农作物名称", trigger: "blur" }]
});
const plantRules = reactive<FormRules>({
  houseId: [{ required: true, message: "请选择大棚", trigger: "blur" }]
});

const authorization = computed(() => {
    return {
        Authorization: token_prefix + getToken(),
    }
});

const handleSuccess = (response: AxiosResponse) => {
    productForm.value.photo = response.data;
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
  plantModel:false,
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
  productForm: {} as ProductForm,
  productIdList: [] as number[],
  productList: [] as Product[],
  plantForm: {} as PlantForm,
  allHouse: [] as AllHouse[],
});
const {
  count,
  showSearch,
  loading,
  title,
  addOrUpdate,
  plantModel,
  queryParams,
  status,
  allHouse,
  plantForm,
  productForm,
  productIdList,
  productList,
} = toRefs(data);

const handleSelectionChange = (selection: Product[]) => {
  productIdList.value = selection.map((item) => item.id);
};
const reset = () => {
  productFormRef.value?.clearValidate();
  plantFormRef.value?.clearValidate();

};
const openModel = async (product?: Product) => {
  reset();
  if (product !== undefined) {
    title.value = "修改农作物";
    productForm.value.id = product.id;
    productForm.value.name = product.name;
    productForm.value.cycle = product.cycle;
    productForm.value.status = product.status;
    productForm.value.photo = product.photo;
  } else {
    title.value = "添加农作物";
    productForm.value = {
      id: undefined,
      name: "",
      cycle: 0,
      status: 0,
      photo: "",
    };
  }
  addOrUpdate.value = true;
};

/**
 * 打开种植表单
 */

const openPlantModel = async (product?: Product) => {
  reset();
  if (product != null) {
    title.value = "种植农作物";
    productForm.value.name = product.name;
    plantForm.value.productId = product.id;
    plantForm.value.status = 0;
    plantForm.value.greenHouseId = undefined;
  }
  plantModel.value = true;
};

/**
 * 提交种植表单
 */
const submitPlantForm = () => {
  console.log("提交")
  plant(plantForm.value).then(({data}) => {
    if (data.flag) {
      notifySuccess(data.msg);
      getList();
    }
    plantModel.value = false;
  })
};

const submitForm = () => {
  productFormRef.value?.validate((valid) => {
    if (valid) {
      if (productForm.value.id !== undefined) {
        updateProduct(productForm.value).then(({ data }) => {
          if (data.flag) {
            notifySuccess(data.msg);
            getList();
          }
          addOrUpdate.value = false;
        })
      } else {
        addProduct(productForm.value).then(({ data }) => {
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
    ids = productIdList.value;
  } else {
    ids = [id];
  }
  messageConfirm("确认删除已选中的数据项?").then(() => {
    deleteProduct(ids).then(({ data }) => {
      if (data.flag) {
        notifySuccess(data.msg);
        getList();
      }
    });
  }).catch(() => { });
};
const getList = () => {
  loading.value = true;
  getProductList(queryParams.value).then(({ data }) => {
    productList.value = data.data.recordList;
    count.value = data.data.count;
    loading.value = false;
  });
};

const getHouse =() =>{
  getHouses().then(({data}) =>{
    allHouse.value = data.data;
  })
}
const handleQuery = () => {
  queryParams.value.current = 1;
  getList();
};
onMounted(() => {
  getList();
  getHouse();
});
</script>

<style scoped></style>
