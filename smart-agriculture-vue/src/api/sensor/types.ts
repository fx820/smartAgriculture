import { PageQuery } from "@/model";

/**
 * 设备
 */
export interface Sensor {
  //设备编号
  id: number;
  //设备名称
  name: string;
  //设备类型
  type: string;
  //创建时间
  createTime: string;
  //更新时间
  updateTime: string;
  //设备使用状态
  status : number;
  //图像路径
  image: string;
}

/**
 * 设备查询参数
 */
export interface SensorQuery extends PageQuery {
  /**
   * 关键词
   */
  keyword?: string;
  /**
   * 设备使用状态
   */
  status?: number;
}

/**
 * 设备表单
 */
export interface SensorForm {
  //设备编号
  id?: number;
  //设备名称
  name: string;
  //设备类型
  type: string;
  //设备使用状态
  status : number;
  //图片路径
  image : string;
}

