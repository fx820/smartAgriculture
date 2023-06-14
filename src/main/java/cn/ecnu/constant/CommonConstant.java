package cn.ecnu.constant;

/**
 * 公共常量
 *
 * @author zachary
 * @date 2022/12/07 15:46
 **/
public class CommonConstant {

    /**
     * 否
     */
    public static final Integer FALSE = 0;

    /**
     * 是
     */
    public static final Integer TRUE = 1;


    /**
     * 验证码
     */
    public static final String CAPTCHA = "验证码";

    /**
     * 默认用户昵称
     */
    public static final String USER_NICKNAME = "用户";

    /**
     * 审核提醒
     */
    public static final String CHECK_REMIND = "审核提醒";

    /**
     * 评论提醒
     */
    public static final String ALERT_REMIND = "报警提醒";

    /**
     * 通用邮件HTMl模板
     */
    public static final String COMMON_TEMPLATE = "common.html";

    /**
     * 用户邮件HTMl模板
     */
    public static final String USER_TEMPLATE = "user.html";

    /**
     * 作者邮件HTML模板
     */
    public static final String AUTHOR_TEMPLATE = "author.html";

    /**
     * 在线用户
     */
    public static final String ONLINE_USER = "onlineUser";

    /**
     * 父级菜单id
     */
    public static final Integer PARENT_ID = 0;

    /**
     * 管理员id
     */
    public static final String ADMIN = "1";

    /**
     * 菜单类型（目录）
     */
    public static final String TYPE_DIR = "M";

    /**
     * 菜单类型（菜单）
     */
    public static final String TYPE_MENU = "C";

    /**
     * Layout组件标识
     */
    public final static String LAYOUT = "Layout";

    /**
     * ParentView组件标识
     */
    public final static String PARENT_VIEW = "ParentView";

    /**
     * 可用
     */
    public final static Integer ENABLE_STATUS = 2;

    /**
     * 不可用
     */
    public final static Integer NO_ENABLE_STATUS = 1;

    /**
     * EasyDL API路径
     */
    public final static String EASY_DL_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/smart-agriculture";
    /**
     * EasyDL 鉴权路径
     */
    public final static String EASY_DL_OAUTH = "https://aip.baidubce.com/oauth/2.0/token?client_id=zvxd1RrOmRvjO60fbiKv9gsD&client_secret=A3dYvGr3OU4rG9idoVrkKswgsK1kP3pp&grant_type=client_credentials";


}