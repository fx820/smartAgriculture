import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import {Plant, PlantQuery} from "@/api/plant/types";


/**
 * 查看种植列表
 * @param params 查询条件
 * @returns 种植列表
 */
export function getPlantList(params: PlantQuery): AxiosPromise<Result<PageResult<Plant[]>>> {
  return request({
    url: "/plant/list",
    method: "get",
    params,
  });
}


/**
 * 收割
 * @param params
 */
export function cutProduct(id:number): AxiosPromise<Result<null>> {
  return request({
    url: "/plant/cut?id="+id,
    method: "get"
  });
}