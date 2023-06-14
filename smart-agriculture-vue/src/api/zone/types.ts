import { PageQuery } from "@/model";

/**
 * 区域
 */
export interface Zone {
  /**
   * 区域id
   */
  id: number;
  /**
   * 区域名称
   */
  name: string;
  /**
   * 区域地址
   */
  address: string;
  /**
   * 创建时间
   */
  createTime: string;
  /**
   * 修改时间
   */
  updateTime:string;
  /**
   * 可用状态
   */
  status: number;
}

/**
 * 区域查询参数
 */
export interface ZoneQuery extends PageQuery {
  /**
   * 关键词
   */
  keyword?: string;
  /**
   * 区域使用状态（1：不可用 2：可用）
   */
  status?: number;
}

/**
 * 地区表单
 */
export interface ZoneForm {
  /**
   * 区域id
   */
  id: number;
  /**
   * 区域名称
   */
  name: string;
  /**
   * 区域地址
   */
  address: string;
  /**
   * 可用状态
   */
  status: number;
}

/**
 * 地区大棚关联
 */
export interface ZoneAndHouse{
  zoneId: number
}


