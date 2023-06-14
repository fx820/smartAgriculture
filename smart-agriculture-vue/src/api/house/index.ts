import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { House, HouseForm, HouseQuery } from "./types";

/**
 * 查看大棚信息
 * @param params 查询条件
 * @returns 大棚列表
 */
export function getHouseList(params: HouseQuery): AxiosPromise<Result<PageResult<House[]>>> {
  return request({
    url: "/house/list",
    method: "get",
    params,
  });
}

/**
 * 删除大棚
 * @param data 大棚id集合
 */
export function deleteHouse(data: number[]): AxiosPromise<Result<null>> {
  return request({
    url: "/house/delete",
    method: "delete",
    data,
  });
}

/**
 * 添加大棚
 * @param data 大棚信息
 */
export function addHouse(data: HouseForm): AxiosPromise<Result<null>> {
  return request({
    url: "/house/add",
    method: "post",
    data,
  });
}

/**
 * 修改大棚
 * @param data 大棚信息
 */
export function updateHouse(data: HouseForm): AxiosPromise<Result<null>> {
  return request({
    url: "/house/update",
    method: "put",
    data,
  });
}

/**
 * 获取所有可用的大棚信息信息
 */
export function getHouses(): AxiosPromise<Result<House[]>> {
  return request({
    url: "/house/selectEnableAll",
    method: "get"
  });
}
