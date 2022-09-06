# Lite-Blog--Framework -- 轻量简洁的后台系统框架

------

## 前言



作者在写这个博客的时候还只是一个大二的学生，水平能力极其有限。

这个博客是作者第一次从独立搭建框架到完成所有功能的项目，用手一点点敲出来的，没有用任何的后端框架。

中间走了很多的坑，碰了很多的雷，也许项目本身并不完美，也许代码可能也会很烂，不过好歹是自己一点点写出来的。

有了这次的经历，以后编写代码时会有更多可以借鉴的地方且避免同样的事情再次发生。

希望以后在后端的路上可以越走越远，能够达成自己想要的目标。



本框架适用于中小型的博客系统，只保留最基础的框架，没有任何业务代码，也可用于基本的个人学习与使用。

## 开发说明



**数据库SQL构建脚本位于 :**

`Lite-Blog-Api/src/main/resources/dbScript/`



**POST接口调试导出数据位于:**

`Lite-Blog-Api/src/main/resources/postmanCollection/`



**项目的配置文件位于：**

`Lite-Blog-Api/src/main/resources/application-dev.yml`



**项目的语言文件位于：**

`Lite-Blog-Common/src/main/resources/i18n/messages.properties`



**windows启动脚本：**

`./LiteBlogBoot.bat`

**Linux启动脚本：**

`./LiteBlogBoot.sh`



## **版本要求**

- **SpringBoot：**`2.7.2`

- **Mysql：**`8.0.27`

- **Tomcat:**  `8.5.73`

- **RabbitMq:** `3.10.6`

- **Nginx:** `1.16.1`

- **Redis:**`3.0.504`

- **Maven:**`3.8.1`

    

**开发工具：**`Intellj IDEA Ultimate 2022.3`

**必需插件：**

- MybatisX

- MybatisCodesHelper

- TreeInfoTips

- Comments Highlighter

    

## 模块



```
|
|
|----Lite-Blog                 Maven父工程
|
|-------Lite-Blog-Common       公共模块，语言管理，通用工具
|
|-------Lite-Blog-Auth         权限管理模块
|
|-------Lite-Blog-Cos          腾讯云COS对象存储模块
|
|-------Lite-Blog-Mail         邮件服务模块
|
|-------Lite-Blog-Mq           消息队列模块 * (尚未完成)
|
|-------Lite-Blog-Generator    代码生成模块
|
|-------Lite-Blog-Schedule     定时任务模块 * (尚未完成)
|
|-------Lite-Blog-Business     业务模块
|
|-------Lite-Blog-System       系统模块
|
|-------Lite-Blog-Api          Web接口Api模块
```



## 技术栈



**接口文档:**[[登陆接口 - Lite-Blog-Framework (apifox.cn)](https://www.apifox.cn/apidoc/project-1573585/api-38408277)](https://www.apifox.cn/apidoc/shared-25197369-00c2-4c14-a603-1c453cfcc812/doc-881581)

**技术总栈:**[[项目概览 - Lite-Blog-Framework (apifox.cn)](https://www.apifox.cn/apidoc/project-1573585/doc-1361562)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/所用技术.md)

**登陆校验:**[Lite-Blog-Doc/Token校验.md at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/Token校验.md)

**实体映射:**[CQUT-Programmer/Lite-Blog-Doc: Documentation repository for blog systems (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/实体映射.md)

**邮件服务:**[Lite-Blog-Doc/邮件服务.md at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/邮件服务.md)

**多语言配置:**[Lite-Blog-Doc/dev at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/i18n多语言.md)

**对象存储:**[Lite-Blog-Doc/dev at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/腾讯COS.md)

**接口权限:**[Lite-Blog-Doc/接口权限管理.md at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/接口权限管理.md)

**数据缓存:**[Lite-Blog-Doc/数据缓存.md at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/数据缓存.md)

**XSS防御:** [Lite-Blog-Doc/XSS脚本过滤.md at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/XSS脚本过滤.md)

**Redis脚本:**[Lite-Blog-Doc/Redis脚本.md at main · CQUT-Programmer/Lite-Blog-Doc (github.com)](https://github.com/CQUT-Programmer/Lite-Blog-Doc/blob/main/dev/Redis脚本.md)



## 使用说明

**注：部分功能尚未开发完毕,但不影响基本使用**

此后台系统上手十分简单，遵循以下步骤即可

**1.导入数据库**

将`Lite-Blog-Api/src/main/resources/dbScript/`位置的sql脚本导入数据库，初始化数据库结构

**2.书写配置文件**

位于`Lite-Blog-Api/src/main/resources/application-dev.yml`的配置文件，每一个配置都有详细的注释

其中mysql与redis配置为**必填**

 邮件模块与对象存储模块不想使用不填写即可

**3.导入postman接口调试数据**

导入位于`Lite-Blog-Api/src/main/resources/postmanCollection/`,目录下已经编写好的接口调试数据

便于开发调试接口

**4.测试配置**

运行位于`Lite-Blog-Api/src/test/java/com/lite/api/LiteBlogWebApplicationTest.java`类下的一个基础单元测试

```java
/**
* 这是一个非常简单的查询执行用户的测试方法
* 建议用于测试后端应用是否可以正常运行
*/
@Test
void applicationTest() {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getMail, "2633565580@qq.com");
    redisCache.setCacheObject("redis",authMapper.selectList(queryWrapper).toString());
    log.info(redisCache.getCacheObject("redis").toString());
}
```

无报错成功运行即代表配置完成。

**5.启动应用**

运行位于`Lite-Blog-Api/src/main/java/com/lite/api/LiteBlogWebApplication.java`的启动类

## 部署说明

暂无
