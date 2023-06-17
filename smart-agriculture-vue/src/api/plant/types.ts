import { PageQuery } from "@/model";

/**
 * 种植
 */
export interface Plant {
  productId: number;
  productName:string;
  plantTime:string;
  status: number;
  greenHouseId:number;
  houseName:string;
}

/**
 * 农作物查询参数
 */
export interface PlantQuery extends PageQuery {
  /**
   * 关键词
   */
  keyword?: string;
  /**
   * 种植状态（0种植中 1等待收割 2完成）
   */
  status?: number;

}

