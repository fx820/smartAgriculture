import { PageQuery } from "@/model";

/**
 * 虫害监测
 */
export interface Pest {
  /**
   * 虫害监测id
   */
  id: number;
  /**
   * 大棚编号
   */
  greenHouseId: number;
  /**
   * 大棚名
   */
  greenHouseName: string;
  /**
   * 图片监测url
   */
  imageSrc: string;
  /**
   * 识别结果
   */
  result: string;
  /**
   * 农作物编号
   */
  productId: number;
  /**
   * 农作物名
   */
  productName: string;
  /**
   * 识别准确率
   */
  accuracy: string;
  /**
   * 数据可见状态 (0不可见 1可见)
   */
  status: number;
  /**
   * 创建时间
   */
  createTime: string;
}

/**
 * 虫害监测查询参数
 */
export interface PestQuery extends PageQuery {

  /**
   * 大棚编号
   */
  greenHouseId: number;
  /**
   * 农作物编号
   */
  productId?: number;
  /**
   * 种植状态（0未种植 1种植）
   */
  status?: number;
}

/**
 * 虫害监测表单
 */
export interface AddPestForm {
  /**
   * 虫害监测id
   */
  id?: number;
  /**
   * 大棚编号
   */
  greenHouseId?: number;

  /**
   * 图片监测url
   */
  imageSrc: string;
  /**
   * 农作物编号
   */
  productId?: number;
}

export interface UpdatePestForm {
  /**
   * 虫害监测id
   */
  id?: number;

  /**
   * 图片监测url
   */
  imageSrc: string;
}

