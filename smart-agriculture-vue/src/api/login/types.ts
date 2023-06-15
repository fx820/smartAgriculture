/**
 * 登录信息
 */
export interface LoginForm {
  /**
   * 用户名
   */
  username: string;
  /**
   * 密码
   */
  password: string;
}

/**
 * 用户登录信息
 */
export interface UserInfo {
  /**
   * 用户id
   */
  id: number;
  /**
   * 头像
   */
  avatar: string;
  /**
   * 昵称
   */
  nickname: string;
  /**
   * 用户名
   */
  username: string;
  /**
   * 邮箱
   */
  email: string;
  /**
   * 手机号
   */
  phone: string;
  /**
   * 地址
   */
  address: string;
  /**
   * 登录方式
   */
  loginType: number;
}
