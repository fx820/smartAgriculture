# 智慧农业管理系统

基于SpringBoot+Vue开发的前后端分离智慧农业管理系统

## 项目技术栈

**前端：**Vue3 + Vue Router + TypeScript + Axios + Element Plus + Echarts

**后端：**SpringBoot + MySQL + Redis + Quartz + Sa-Token + MybatisPlus + RabbitMQ

**模型训练：** EasyDL

## 环境介绍

- JDK : 11
- MySQL 8.0
- Spring Boot : 2.6.14
- Sa-Token : 1.34.0
- MybatisPlus : 3.5.2

## 数据库建表

**数据库名称 : smart**

### 核心建表语句

环境监测表 t_environment_monitor:

```mysql
CREATE TABLE `t_environment_monitor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '环境监测数据编号',
  `AIR_TEMPERATURE` double DEFAULT NULL COMMENT '空气温度',
  `AIR_HUMIDITY` int(11) DEFAULT NULL COMMENT '空气湿度',
  `SOIL_TEMPERATURE` double DEFAULT NULL COMMENT '土壤温度',
  `SOIL_HUMIDITY` int(11) DEFAULT NULL COMMENT '土壤湿度',
  `C02_LEVEL` int(11) DEFAULT NULL COMMENT '二氧化碳含量',
  `LIGHT_LEVEL` int(11) DEFAULT NULL COMMENT '光照度',
  `GREEN_HOUSE_ID` int(11) DEFAULT NULL COMMENT '大棚编号',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETE` tinyint(1) DEFAULT '0' COMMENT '是否删除（0：否 1：是）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='环境监测表';
```

大棚表 t_green_houses:

```mysql
CREATE TABLE `t_green_houses` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '大棚编号',
  `ZONE_ID` int(11) DEFAULT NULL COMMENT '所在区域',
  `BUILD_TIME` date DEFAULT NULL COMMENT '建造时间',
  `STATUS` tinyint(1) DEFAULT '1' COMMENT '状态（1：公开 2：私密）',
  `IS_DELETE` tinyint(1) DEFAULT '0' COMMENT '是否删除（0：否 1：是）',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `NAME` varchar(50) NOT NULL COMMENT '大棚名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='大棚表';
```

害虫监测表 t_pest_monitor:

```mysql
CREATE TABLE `t_pest_monitor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '虫情监测数据编号',
  `GREEN_HOUSE_ID` int(11) DEFAULT NULL COMMENT '大棚编号',
  `IMAGE_SRC` varchar(255) DEFAULT NULL COMMENT '监测图片信息',
  `RESULT` varchar(50) DEFAULT NULL COMMENT '监测结果',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '虫害描述',
  `PRODUCT_ID` int(11) DEFAULT NULL COMMENT '农产品编号',
  `ACCURACY` double(4,2) NOT NULL COMMENT '准确率',
  `STATUS` tinyint(1) DEFAULT '1' COMMENT '状态（1：公开 2：私密）',
  `IS_DELETE` tinyint(1) DEFAULT '0' COMMENT '是否删除（0：否 1：是）',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='害虫监测表';
```

种植表 t_plant:

```mysql
CREATE TABLE `t_plant` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '种植表id',
  `PRODUCT_ID` int(11) DEFAULT NULL COMMENT '农产品id',
  `PLANT_TIME` datetime DEFAULT NULL COMMENT '种植时间',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '种植状态（0：成长中 1：等待收割 2：完成）',
  `HARVEST_TIME` datetime DEFAULT NULL COMMENT '收割时间',
  `GREEN_HOUSE_ID` int(11) DEFAULT NULL COMMENT '大棚编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='种植表';
```

农作物表 t_product:

```mysql
CREATE TABLE `t_product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '农产品编号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '农产品名称',
  `STATUS` tinyint(1) NOT NULL DEFAULT '0' COMMENT '种植状态（0-未种植，1-种植）',
  `CYCLE` int(11) DEFAULT NULL COMMENT '种植周期（单位：天）',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `IS_DELETE` tinyint(1) DEFAULT '0' COMMENT '是否删除（0：否 1：是）',
  `PHOTO` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='农作物表';
```

设备表 t_sensor:

```mysql
CREATE TABLE `t_sensor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '传感器编号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '传感器名称',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '传感器类型',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `STATUS` tinyint(1) DEFAULT '1' COMMENT '状态（1：公开 2：私密）',
  `IS_DELETE` tinyint(1) DEFAULT '0' COMMENT '是否删除（0：否 1：是）',
  `IMAGE` varchar(100) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='设备表';
```

传感器大棚关联表 t_sensor_green_house:

```mysql
CREATE TABLE `t_sensor_green_house` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表编号',
  `SENSOR_ID` int(11) DEFAULT NULL COMMENT '传感器编号',
  `GREEN_HOUSE_ID` int(11) DEFAULT NULL COMMENT '大棚编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='传感器大棚关联表';
```

区域表 t_zone:

```mysql
CREATE TABLE `t_zone` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域编号',
  `NAME` varchar(100) DEFAULT NULL COMMENT '区域名称',
  `ADDRESS` varchar(100) DEFAULT NULL COMMENT '区域地址',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `STATUS` tinyint(1) DEFAULT '1' COMMENT '状态（1：公开 2：私密）',
  `IS_DELETE` tinyint(1) DEFAULT '0' COMMENT '是否删除（0：否 1：是）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='区域表';
```

## 引入依赖（pom.xml）

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.14</version>
        <relativePath/>
    </parent>

    <groupId>cn.ecnu</groupId>
    <artifactId>smart-agriculture</artifactId>
    <version>1.0</version>
    <name>Smart Agriculture</name>
    <description>基于SpringBoot和Vue开发的前后端分离智慧农业系统</description>
    <packaging>jar</packaging>

    <properties>
        <java.version>11</java.version>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <elasticsearch.version>7.17.3</elasticsearch.version>
        <jakarta-json.version>2.0.1</jakarta-json.version>
        <druid.version>1.2.14</druid.version>
        <mybatis.plus.version>3.5.2</mybatis.plus.version>
        <swagger.version>2.0.9</swagger.version>
        <saToken.version>1.34.0</saToken.version>
        <fastjson2.version>2.0.22</fastjson2.version>
        <ip2region.version>2.6.3</ip2region.version>
        <yauaa.version>7.3.0</yauaa.version>
        <sensitive.version>0.2.0</sensitive.version>
        <oss.version>3.10.2</oss.version>
        <cos.version>5.6.89</cos.version>
        <hutool.version>5.8.10</hutool.version>
        <commons-lang3.version>3.12.0</commons-lang3.version>
        <commons-io.version>2.11.0</commons-io.version>
        <mysql.version>8.0.25</mysql.version>
        <qiniu.version>7.7.0</qiniu.version>
        <zhenzi.version>2.0.2</zhenzi.version>
    </properties>

    <dependencies>
        <!--spring test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
        <!-- spring boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- spring aop -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!-- spring mail -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
        <!-- thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!-- configuration-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- elasticsearch -->
        <dependency>
            <groupId>co.elastic.clients</groupId>
            <artifactId>elasticsearch-java</artifactId>
            <version>${elasticsearch.version}</version>
        </dependency>
        <dependency>
            <groupId>jakarta.json</groupId>
            <artifactId>jakarta.json-api</artifactId>
            <version>${jakarta-json.version}</version>
        </dependency>
        <!-- spring validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <!-- spring amqp -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
        <!-- spring quartz -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-quartz</artifactId>
        </dependency>
        <!-- Sa-Token -->
        <dependency>
            <groupId>cn.dev33</groupId>
            <artifactId>sa-token-spring-boot-starter</artifactId>
            <version>${saToken.version}</version>
        </dependency>
        <!-- Sa-Token 整合 Redis （使用 jackson 序列化方式） -->
        <dependency>
            <groupId>cn.dev33</groupId>
            <artifactId>sa-token-dao-redis-jackson</artifactId>
            <version>${saToken.version}</version>
        </dependency>
        <!-- Redis连接池 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!-- mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
            <scope>runtime</scope>
        </dependency>
        <!-- redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>${druid.version}</version>
        </dependency>
        <!-- mybatis plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis.plus.version}</version>
        </dependency>
        <!-- swagger -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <!-- fastjson2 -->
        <dependency>
            <groupId>com.alibaba.fastjson2</groupId>
            <artifactId>fastjson2</artifactId>
            <version>${fastjson2.version}</version>
        </dependency>
        <!-- ip2region -->
        <dependency>
            <groupId>org.lionsoul</groupId>
            <artifactId>ip2region</artifactId>
            <version>${ip2region.version}</version>
        </dependency>
        <!-- 阿里云oss -->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>${oss.version}</version>
        </dependency>
        <!-- 解析客户端操作系统、浏览器等 -->
        <dependency>
            <groupId>nl.basjes.parse.useragent</groupId>
            <artifactId>yauaa</artifactId>
            <version>${yauaa.version}</version>
        </dependency>
        <!-- 腾讯云cos -->
        <dependency>
            <groupId>com.qcloud</groupId>
            <artifactId>cos_api</artifactId>
            <version>${cos.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>${commons-lang3.version}</version>
        </dependency>
        <!-- hutool -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${hutool.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>${commons-io.version}</version>
        </dependency>
        <!--七牛云oss-->
        <dependency>
            <groupId>com.qiniu</groupId>
            <artifactId>qiniu-java-sdk</artifactId>
            <version>${qiniu.version}</version>
        </dependency>
        <dependency>
            <groupId>com.zhenzikj</groupId>
            <artifactId>zhenzisms</artifactId>
            <version>${zhenzi.version}</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

## 配置文件（application.yml）

```yaml
server:
  # 端口
  port: 8088
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
  # 应用名称
  application:
    name: Smart Agriculture
  # mysql配置
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/smart?serverTimezone=Asia/Shanghai&allowMultiQueries=true&rewriteBatchedStatements=true
    username: #自己数据库的用户名
    password: #自己数据库的密码
    druid:
      # 初始连接数
      initial-size: 10
      # 最小连接池数量
      min-idle: 10
      # 最大连接池数量
      max-active: 150
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      min-evictable-idle-time-millis: 600000
      # 配置一个连接在池中最大生存的时间，单位是毫秒
      max-evictable-idle-time-millis: 900000
      # 配置检测连接是否有效
      validation-query: SELECT 1 FROM DUAL
      # 配置检测连接是否有效时间
      validation-query-timeout: 500
      # 在获取连接后，确定是否要进行连接空间时间的检查
      test-while-idle: true
      # 设置从连接池获取连接时是否检查连接有效性，true检查，false不检查
      test-on-borrow: false
      # 设置从连接池归还连接时是否检查连接有效性，true检查，false不检查
      test-on-return: false
  # 文件上传限制
  servlet:
    multipart:
      max-request-size: 100MB
      max-file-size: 100MB
  # redis配置
  redis:
    # Redis服务器地址
    host: #自己的redis服务器地址
    # Redis服务器连接端口
    port: 6379
    # Redis服务器连接密码（默认为空）
    password: #Redis服务器连接密码
    # 连接超时时间
    timeout: 10s
    lettuce:
      pool:
        # 连接池最大连接数
        max-active: 150
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: 5000ms
        # 连接池中的最大空闲连接
        max-idle: 100
        # 连接池中的最小空闲连接
        min-idle: 50
  # rabbitmq配置
  rabbitmq:
    host: #自己的rabbitmq服务器地址
    port: 5672
    username: #自己的用户名
    password: #自己的密码
    listener:
      simple:
        retry:
          enabled: true
          # 重试间隔时间
          initial-interval: 3000
          # 最大重试次数
          max-attempts: 3
  # 邮箱配置
  mail:
    host: smtp.qq.com
    # 用户名
    username: #邮箱
    # 授权码 邮箱授权码
    password: #邮箱授权码

# 线程池配置
thread:
  pool:
    core-pool-size: 5
    max-pool-size: 10
    queue-capacity: 50
    keep-alive-seconds: 60
# 文件上传策略 local、oss
upload:
  strategy: oss
  local:
    # nginx映射本地文件路径
    url: https://你的文件上传子域名
    # 本地文件存储路径
    path: /usr/local/upload
  # 七牛云 oss存储
  oss:
    accessKey: #oss配置accessKey
    secretKey: #oss配置secretKey
    bucket: #oss配置bucket
    imageUrl: ""

  # cos存储
  cos:
    url: https://Bucket域名
    secretId: COS配置secretId
    secretKey: COS配置secretKey
    region: COS配置region
    bucketName: COS配置bucketName
# mybatisPlus配置
mybatis-plus:
  # mapper映射地址
  mapper-locations: classpath:mapper/*.xml
  # 实体类扫描包路径
  type-aliases-package: cn.ecnu.entity
  configuration:
    # sql打印
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    # 开启驼峰命名
    map-underscore-to-camel-case: true
  global-config:
    db-config:
      # 数据库表前缀
      table-prefix: t_
      #逻辑删除配置
      logic-delete-field: IS_DELETE
      logic-delete-value: 1
      logic-not-delete-value: 0
      id-type: auto
# Sa-Token 配置 (文档: https://sa-token.cc)
sa-token:
  # token名称 (同时也是cookie名称)
  token-name: Authorization
  # token前缀
  token-prefix: Bearer
  # token有效期，单位s 默认30天, -1代表永不过期
  timeout: 1800
  # token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
  activity-timeout: -1
  # 关闭自动续签
  auto-renew: false
  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)
  is-concurrent: true
  # token风格
  token-style: uuid
  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
  is-share: false
  # 同一账号最大登录数量
  max-login-count: 20
  # 是否从cookie中读取token
  is-read-cookie: false
  # 是否从请求体里读取token
  is-read-body: false
  # 是否从header中读取token
  is-read-header: true
  # 是否输出操作日志
  is-log: false
```

## Vue项目

```
#项目终端运行 导入依赖
npm install
```

