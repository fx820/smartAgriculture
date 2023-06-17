import { PageQuery } from "@/model";
import {Zone} from "@/api/zone/types";

/**
 * 大棚
 */
export interface House {
  /**
   * 大棚id
   */
  id: number;
  /**
   * 大棚名称
   */
  name: string;
  /**
   * 所在区域id
   */
  zoneId: number;
  /**
   * 所在区域名称
   */
  zoneName: string;
  /**
   * 大棚使用状态（1：荒废 2：使用中）
   */
  status: number;
  /**
   * 大棚建造时间
   */
  buildTime: string;
  /**
   * 创建时间
   */
  createTime: string;
  /**
   * 修改时间
   */
  updateTime:string;
}

/**
 * 大棚查询参数
 */
export interface HouseQuery extends PageQuery {
  /**
   * 关键词
   */
  keyword?: string;
  /**
   * 大棚使用状态（1：荒废 2：使用中）
   */
  status?: number;
  /**
   * 所在地区
   */
  zoneId?: number;
}

/**
 * 分页查询
 */

export interface pageSensorQuery extends PageQuery{
  /**
   * 大棚编号
   */
  id: number;
}

/**
 * 农作物表单
 */
export interface HouseForm {
  /**
   * 大棚id
   */
  id?: number;
  /**
   * 大棚名称
   */
  name: string;
  /**
   * 所在区域id
   */
  zoneId?: number;
  /**
   * 大棚使用状态（1：荒废 2：使用中）
   */
  status: number;
  /**
   * 大棚建造时间
   */
  buildTime: string;
}

export interface Sensor{
  id?: number;
  name: string;
  type: string;
}

export interface AllHouse{
  id: number;
  name: string;
}
