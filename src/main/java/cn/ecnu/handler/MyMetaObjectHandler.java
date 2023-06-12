package cn.ecnu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static cn.ecnu.enums.ZoneEnum.SHANGHAI;

/**
 * mybatis plus自动填充
 *
 * @author zachary
 */
@Log4j2
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        setFieldValByName("createTime", new Date(), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
