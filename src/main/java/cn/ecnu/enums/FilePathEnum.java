package cn.ecnu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件路径枚举
 *
 * @author zachary
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 头像路径
     */
    AVATAR("/avatar/", "/avatar", "头像路径"),

    /**
     * 农产品图片路径
     */
    PRODUCT("/product/", "/product", "农产品图片路径"),

    /**
     * 虫害图片路径
     */
    PEST("/pest/", "/pest", "虫害图片路径"),

    /**
     * 设备路径
     */
    SENSOR("/sensor/", "/sensor", "设备路径");

    /**
     * 路径
     */
    private final String path;

    /**
     * 文件路径
     */
    private final String filePath;

    /**
     * 描述
     */
    private final String description;
}
