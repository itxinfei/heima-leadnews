### 后端工程说明

后端工程基于 Spring Boot 4.1.1 + JDK 25 构建，工程父项目为 xftt，并通过继承方式集成 Spring Boot。

【父项目下分4个公共子项目】：

- xftt-common : 是整个工程的配置核心，包括所有集成三方框架的配置定义，比如redis、kafka等。除此之外还包括项目每个模块及整个项目的常量定义;

- xftt-model ：项目中用到的Dto、Pojo、Mapper、Enums定义工程;

- xftt-utils : 工程公用工具类项目，包含加密/解密、Date、JSON等工具类;

- xftt-apis : 整个项目微服务暴露的接口的定义项目，按每个模块进行子包拆分;


【多个微服务】：

- xftt-login：用于实现APP+自媒体端用户的登录与注册功能；
- xftt-user：用于实现APP端用户中心的功能，比如我的收藏、我的粉丝等功能；
- xftt-article：用于实现APP端文章的获取与搜索等功能；还包括频道、标签等功能；
- xftt-behavior：用于实现APP端各类行为数据的上传服务；
- xftt-crawler：用于实现文章数据的自动化爬取功能；
- xftt-migration：用于实现数据迁移与热点文章计算任务；
- xftt-media：用于实现自媒体管理端的功能；
- xftt-admin：用于实现后台管理系统的功能；
- xftt-images：用于实现图片素材管理功能；
- service-gateway：Spring Cloud Gateway 网关
- Nacos（外部中间件）：注册中心与配置中心，需独立部署
- service-mycat：MyCat 分库分表中间件