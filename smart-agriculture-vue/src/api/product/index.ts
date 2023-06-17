import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import {PlantForm, Product, ProductForm, ProductQuery} from "./types";

/**
 * 查看农作物列表
 * @param params 查询条件
 * @returns 农作物列表
 */
export function getProductList(params: ProductQuery): AxiosPromise<Result<PageResult<Product[]>>> {
  return request({
    url: "/product/list",
    method: "get",
    params,
  });
}

/**
 * 删除农作物
 * @param data 农作物id集合
 */
export function deleteProduct(data: number[]): AxiosPromise<Result<null>> {
  return request({
    url: "/product/delete",
    method: "delete",
    data,
  });
}

/**
 * 添加农作物
 * @param data 农作物信息
 */
export function addProduct(data: ProductForm): AxiosPromise<Result<null>> {
  return request({
    url: "/product/add",
    method: "post",
    data,
  });
}

/**
 * 修改农作物
 * @param data 农作物信息
 */
export function updateProduct(data: ProductForm): AxiosPromise<Result<null>> {
  return request({
    url: "/product/update",
    method: "put",
    data,
  });
}

/**
 * 获取所有的农作物信息
 */
export function getProductAll(): AxiosPromise<Result<Product[]>> {
  return request({
    url: "/product/selectAll",
    method: "get"
  });
}

/**
 * 种植农作物
 * @param data
 */
export function plant(data: PlantForm): AxiosPromise<Result<null>> {
  return request({
    url: "/plant/plant",
    method: "post",
    data,
  });
}
