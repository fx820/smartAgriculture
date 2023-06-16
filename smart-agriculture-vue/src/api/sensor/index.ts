import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { Sensor, SensorForm, SensorQuery } from "./types";

/**
 * 查看设备列表
 * @param params 查询条件
 * @returns 设备列表
 */
export function getSensorList(params: SensorQuery): AxiosPromise<Result<PageResult<Sensor[]>>> {
  return request({
    url: "/sensor/list",
    method: "get",
    params,
  });
}

/**
 * 删除设备
 * @param data 设备id集合
 */
export function deleteSensor(data: number[]): AxiosPromise<Result<null>> {
  return request({
    url: "/sensor/delete",
    method: "delete",
    data,
  });
}

/**
 * 添加设备
 * @param data 设备信息
 */
export function addSensor(data: SensorForm): AxiosPromise<Result<null>> {
  return request({
    url: "/sensor/add",
    method: "post",
    data,
  });
}

/**
 * 修改设备
 * @param data 设备信息
 */
export function updateSensor(data: SensorForm): AxiosPromise<Result<null>> {
  return request({
    url: "/sensor/update",
    method: "put",
    data,
  });
}


