import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import {Zone, ZoneForm, ZoneQuery} from "./types";
import {House} from "@/api/house/types";

/**
 * 查看区域信息
 * @param params 查询条件
 * @returns 区域列表
 */
export function getZoneList(params:ZoneQuery): AxiosPromise<Result<PageResult<Zone[]>>> {
  return request({
    url: "/zone/list",
    method: "get",
    params,
  });
}

/**
 * 删除区域
 * @param data 区域id集合
 */
export function deleteZone(data: number[]): AxiosPromise<Result<null>> {
  return request({
    url: "/zone/delete",
    method: "delete",
    data,
  });
}

/**
 * 添加区域
 * @param data 区域信息
 */
export function addZone(data:ZoneForm): AxiosPromise<Result<null>> {
  return request({
    url: "/zone/add",
    method: "post",
    data,
  });
}

/**
 * 修改区域
 * @param data 区域信息
 */
export function updateZone(data:ZoneForm): AxiosPromise<Result<null>> {
  return request({
    url: "/zone/update",
    method: "put",
    data,
  });
}

  /**
   * 获取所有的地区信息
   */
export function getZones(): AxiosPromise<Result<Zone[]>> {
  return request({
     url: "/zone/selectEnableAll",
     method: "get"
  });
}


/**
 * 判断所在地区是否有大棚
 */

export function hasHouse(data: number[]): AxiosPromise<Result<House>> {
  return request({
    url: "/zone/hasHouse?ids="+data,
    method: "get",
  })
}

