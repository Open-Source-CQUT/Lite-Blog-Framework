# 代码生成器



目前代码生成模块下只有一个自动生成MVC代码的功能，结合改造了mybatis-plus的代码生成器

填写好generator模块下的mpg配置文件即可

```properties
#作者名
author=stranger
#数据库URL
url=jdbc:mysql://localhost:3306/lite_blog?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong&allowMultiQueries=true
username=root
password=wyh246859
#要生成在那个模块
moduleName=Lite-Blog-Business
#生成的包名
packageName=com.lite.business
#生成DTO
dtoEnable=true
#生成VO
voEnable=true
#生成mapstruct
mapEnable=true
#需要映射的数据库表,默认为全部表
includeTableList=info_article,info_share,info_label,info_category,info_comment
#模糊匹配过滤,过滤是在includeTableList里面过滤
likeTable=
#表转换实体命名时，会去掉的前缀 例如 表info_user ---> 实体User.java
tablePrefix=relation_,info_,info_sys_

```

然后在GenertorApplication运行即可,可以自动生成

- controller
- service
- serviceimpl
- dao
- mapperxml
- entity
- convert
- dto
- vo

并且自动生成注解和自动import

生成文件结构是 `模块层名+实体表名+文件`
