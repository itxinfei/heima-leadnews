### 后端工程说明

后端工程基于Spring-boot 2.1.5.RELEASE 版本构建，工程父项目为heima-leadnews，并通过继承方式集成Spring-boot。

【父项目下分4个公共子项目】：

- heima-leadnews-common : 是整个工程的配置核心，包括所有集成三方框架的配置定义，比如redis、kafka等。除此之外还包括项目每个模块及整个项目的常量定义;

- heima-leadnews-model ：项目中用到的Dto、Pojo、Mapper、Enums定义工程;

- heima-leadnews-utils : 工程公用工具类项目，包含加密/解密、Date、JSON等工具类;

- heima-leadnew-apis : 整个项目微服务暴露的接口的定义项目，按每个模块进行子包拆分;


【多个微服务】：

- heima-leadnews-login：用于实现APP+自媒体端用户的登录与注册功能；
- heima-leadnews-user：用于实现APP端用户中心的功能，比如我的收藏、我的粉丝等功能；
- heima-leadnews-article：用于实现APP端文章的获取与搜索等功能；还包括频道、标签等功能；
- heima-leadnews-behavior：用于实现APP端各类行为数据的上传服务；
- heima-leadnews-webmagic：用于实现文章数据的自动化爬取功能；
- heima-leadnews-quartz：用于封装项目中所有的调度计算任务；
- heima-leadnews-wemedia：用于实现自媒体管理端的功能；
- heima-leadnews-admin：用于实现后台管理系统的功能；
- service-gateway：spring cloud 网关
- service-eureka：spring cloud 注册中心