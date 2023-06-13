import { PageQuery } from "@/model";

/**
 * 农作物
 */
export interface Product {
  /**
   * 农作物id
   */
  id: number;
  /**
   * 农作物名称
   */
  name: string;
  /**
   * 种植周期
   */
  cycle: number;
  /**
   * 种植状态 (0未种植 1种植)
   */
  status: number;
  /**
   * 创建时间
   */
  createTime: string;
  /**
   * 修改时间
   */
  updateTime:string;
  /**
   * 农作物图片
   */
  photo:string;
}

/**
 * 农作物查询参数
 */
export interface ProductQuery extends PageQuery {
  /**
   * 关键词
   */
  keyword?: string;
  /**
   * 种植状态（0未种植 1种植）
   */
  status?: number;
}

/**
 * 农作物表单
 */
export interface ProductForm {
  /**
   * 农作物id
   */
  id?: number;
  /**
   * 农作物名称
   */
  name: string;
  /**
   * 种植周期
   */
  cycle: number;
  /**
   * 种植状态 (0未种植 1种植)
   */
  status: number;
  /**
   * 农作物图像
   */
  photo: string;
}

