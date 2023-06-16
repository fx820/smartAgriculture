import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import {Environment,EnvironmentQuery} from "./types";

/**
 * 查看监测数据信息
 * @param params 查询条件
 * @returns 监测列表
 */
export function getEnvironmentList(params:EnvironmentQuery): AxiosPromise<Result<PageResult<Environment[]>>> {
  return request({
    url: "/environment/monitor/list",
    method: "get",
    params,
  });
}

/**
 * 删除监测数据
 * @param data 监测id集合
 */
export function deleteEnvironment(data: number[]): AxiosPromise<Result<null>> {
  return request({
    url: "/environment/monitor/delete",
    method: "delete",
    data,
  });
}



