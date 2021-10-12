# shiro-sso

#### 项目介绍
1. springBoot shiro reids 实现session共享sso单点登录
2. 模块分为 shiro-auth  （shiro 认证中心），
 shiro-project-a  （A工程），
 shiro-project-b  （B工程）

#### 软件架构
springBoot, shiro, redis, thymeleaf

#### 使用说明

 端口号：
1. shiro-auth           // 8810
2. shiro-project-a      // 8811
3. shiro-project-b      // 8812

#################################################

### 使用步骤：
 ！！！ 运行 db.sql 数据库脚本文件
#### 分别启动
    shiro-auth ==》 ShiroAuthApplication.java
    shiro-project-a ==》 ShiroProjectAApplication
    shiro-project-b ==》 ShiroProjectBApplication
####  浏览器分别访问
1. 127.0.0.1:8011/test
2. 127.0.0.1:8012/test
#### 登陆
1. 随便登陆一个网址 输入admin, 123456，刷新另一个
#### 登出
访问: 127.0.0.1:8010/loginOut

