package cn.ecnu.exception;

import lombok.Getter;

import static cn.ecnu.enums.StatusCodeEnum.FAIL;

/**
 * 业务异常
 *
 * @author zachary
 * @date 2022/12/02 16:18
 **/
@Getter
public final class ServiceException extends RuntimeException {

    /**
     * 返回失败状态码
     */
    private Integer code = FAIL.getCode();

    /**
     * 返回信息
     */
    private final String message;

    public ServiceException(String message) {
        this.message = message;
    }

}