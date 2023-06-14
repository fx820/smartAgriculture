import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { Pest, PestForm, PestQuery } from "./types";

/**
 * 查看虫害信息列表
 * @param params 查询条件
 * @returns 虫害信息列表
 */
export function getPestList(params: PestQuery): AxiosPromise<Result<PageResult<Pest[]>>> {
  return request({
    url: "/pest/list",
    method: "get",
    params,
  });
}

/**
 * 删除虫害信息
 * @param data 虫害信息id集合
 */
export function deletePest(data: number[]): AxiosPromise<Result<null>> {
  return request({
    url: "/pest/delete",
    method: "delete",
    data,
  });
}

/**
 * 添加虫害信息
 * @param data 虫害信息信息
 */
export function addPest(data: PestForm): AxiosPromise<Result<null>> {
  return request({
    url: "/pest/add",
    method: "post",
    data,
  });
}

/**
 * 修改虫害信息
 * @param data 虫害信息信息
 */
export function updatePest(data: PestForm): AxiosPromise<Result<null>> {
  return request({
    url: "/pest/update",
    method: "put",
    data,
  });
}
