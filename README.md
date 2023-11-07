# 物业后台管理系统-后端

### 前端代码仓库链接
![前端代码仓库](https://github.com/Pitayafruits/manage_front)

### 运行前配置
1. 在yml配置文件中修改自己的数据库配置
2. 在utils包下的SMSUtils下修改自己短信服务key
3. 数据库SQL文件在resources目录下

### 介绍
这是一款前后端分离的基于Spring Boot的物业管理系统，该系统实现了物业工作人员管理、业主管理、房屋管理、物业管理、人事管理、报修管理等主要功能。

### 软件架构
* 核心框架：Spring Boot 2.3.7.RELEASE
* 安全框架：Spring Security 5.3.6.RELEASE
* 持久层框架：MyBatis-Plus 3.5.2
* 关系型数据库: Mysql 5.7.35
* 数据库连接池：Druid 1.2.11
* 缓存数据库: Redis 4.0.9
* 项目管理工具: Maven 3.8.4

### 界面展示
1. 前台页面展示

![](https://github.com/Pitayafruits/myPicRep/blob/main/PropertyManage/202306062244908.jpg)

2. 后台页面展示

![](https://github.com/Pitayafruits/myPicRep/blob/main/PropertyManage/202306052102444.jpg)

### 下一步工作
* 在考虑把权限框架由Spring Security替换为更轻量的Sa-Token
* 想把缴费管理改成定时任务那种批量自动生成账单的形式

### 感谢
感谢B站上那些分享无私技术的UP主，这个毕设项目对于我的提升主要在前端方面，在做它之前，自己还没有过独立完成前端项目的经验，而正是凭借B站上的那
些课程，也算磕磕绊绊地写出了自己满意的页面，这个项目也是给我的本科生涯画上了句号，希望自己的程序员生涯能够走得久一点吧！
