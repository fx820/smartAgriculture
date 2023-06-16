import { PageQuery } from "@/model";

/**
 * 大棚环境监测
 */
export interface Environment {
  /**
   * 大棚环境监测id
   */
  id: number;
  /**
   * 大棚名称
   */
  name: string;
  /**
   * 空气温度
   */
  airTemperature:number;
  /**
   * 空气湿度
   */
  airHumidity: number;
  /**
   * 土壤温度
   */
  soilTemperature:number;
  /**
   * 土壤湿度
   */
  soilHumidity:number;
  /**
   * 二氧化碳含量
   */
  c02Level:number;
  /**
   * 光照度
   */
  lightLevel:number;
  /**
   * 传感器编号
   */
  sensorIds: number[];

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
 * 大棚环境监测查询参数
 */
export interface EnvironmentQuery extends PageQuery {
  /**
   * 关键词
   */
  keyword?: string;
}




