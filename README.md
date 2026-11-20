<div align="center">
  <h1>心飞头条</h1>
  <p>微服务 + 大数据的资讯平台，三端一体</p>

  <img alt="JDK" src="https://img.shields.io/badge/JDK-25-brightgreen">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen">
  <img alt="Spring Cloud" src="https://img.shields.io/badge/Spring%20Cloud-2025.1.3-orange">
  <img alt="Kafka" src="https://img.shields.io/badge/Kafka-4.2.1-red">
  <img alt="Elasticsearch" src="https://img.shields.io/badge/Elasticsearch-9.4.5-red">
  <img alt="Nacos" src="https://img.shields.io/badge/Nacos-3.x-blue">
  <img alt="Vue3" src="https://img.shields.io/badge/Vue-3-blueviolet">
  <img alt="uni-app" src="https://img.shields.io/badge/uni--app-Vue3-blueviolet">
  <br>
  <a href="https://gitee.com/itxinfei/xftt/stargazers"><img alt="star" src="https://gitee.com/itxinfei/xftt/badge/star.svg?theme=gvp"></a>
  <a href="https://gitee.com/itxinfei/xftt/members"><img alt="fork" src="https://gitee.com/itxinfei/xftt/badge/fork.svg?theme=gvp"></a>
  <img alt="License" src="https://img.shields.io/badge/license-Apache--2.0-blue">
  <img alt="Business" src="https://img.shields.io/badge/business-content%20platform-important">
  <img alt="Services" src="https://img.shields.io/badge/microservices-5-success">
</div>

## 一、这是什么

一套完整跑通的内容平台：用户端看新闻、自媒体后台写稿、管理端审核发频道。后端是 Spring Cloud 微服务，检索走 Elasticsearch，行为数据走 Kafka，库按业务拆成五个。

不是 demo。每个服务都能单独起，网关统一收口，端口分配、Nacos 配置集、分库规则都写在文档里。

## 二、三端

| 端 | 技术 | 干什么 |
|---|---|---|
| APP | uni-app 3 + Vue 3 + TS + Pinia + wot-design-uni | 看文章、搜内容、收藏关注、实名认证 |
| 自媒体（wemedia） | Vue 3 + Vite 5 + Element Plus | 写稿、发布、管评论、看数据 |
| 管理后台（admin） | Vue 3 + Vite 5 + Element Plus | 审内容、管用户、配频道、看大盘 |

三端共用一套视觉基线，主色 `#ED4040`，交互习惯对齐主流资讯产品——不是为了像谁，是因为用户看新闻的手势已经定型了，改反而增加成本。

## 三、后端

| 层 | 选型 |
|---|---|
| 框架 | Spring Boot 4.1.1 + Spring Cloud 2025.1.3 + Gateway + OpenFeign |
| 注册配置 | Nacos 3.x |
| 消息 | Kafka 4.2.1 + Kafka Stream |
| 存储 | MySQL 9.7.0（MyCat 分库分表）+ MongoDB + HBase |
| 检索缓存 | Elasticsearch 9.4.5 + Redis |
| 其他 | FastDFS + ZooKeeper + Quartz，实名认证走 OCR + 人脸比对 |

服务拆分：

```
xftt-gateway   10000   统一入口
xftt-login     9001    登录注册
xftt-article   9003    文章
xftt-behavior  9004    行为采集
xftt-user      9005    用户
xftt-media     9006    自媒体
xftt-admin     9007    管理后台
xftt-crawler   9008    内容采集
xftt-images    9010    图片服务
xftt-migration 9012    数据迁移
xftt-mycat     8066    分库分表中间件
```

端口以 `xftt-common/src/main/resources/config/application.properties` 为准，README 里的数字只是方便你对照。

公共库 `xftt-common / xftt-utils / xftt-apis / xftt-model` 单独抽出来，是因为十个服务都要用同一套 DTO 和工具，复制粘贴迟早会分叉。

## 四、为什么拆五个库

```
AppInfo    用户、文章、评论
Behavior   点赞、转发、阅读等行为日志
WeMedia    自媒体图文、粉丝关系
Crawlers   采集内容
Admin      管理员与权限
```

行为日志写入量是文章表的几十倍，混在一起会把主业务的查询拖垮。拆开之后 Behavior 库单独扩容，其他库不受影响。热点文章的分片键是自定义的，不按文章 ID 直接取模——热点内容集中在一篇上，按 ID 分片会把压力全压到一个库。

## 五、跑起来

```bash
# 依赖：Nacos 单机、MySQL、Redis、Kafka、ZooKeeper、ES、FastDFS
# 先在 Nacos 建 xftt 命名空间

mysql -u root -p < xftt.sql

mvn -U clean compile
mvn -pl xftt-gateway spring-boot:run    # :10000
mvn -pl xftt-login   spring-boot:run    # :9001
mvn -pl xftt-article spring-boot:run    # :9003

cd xftt-web/xftt-app     && npm install && npm run dev:h5   # APP H5
cd xftt-web/xftt-wemedia && npm install && npm run dev      # :8081
cd xftt-web/xftt-admin   && npm install && npm run dev      # :8082
```

中间件不少，一次全起对机器有要求。只想看前端效果的话，APP 端 H5 模式可以单独跑。

环境要求：JDK 25、Maven 3.9+、Node 18 LTS+。端口表、Nacos 配置集和常见坑见[环境搭建与启动指南](docs/环境搭建与启动指南.md)。

## 六、截图

三张功能截图原来挂在阿里云 OSS 的临时签名地址上，`Expires` 是 2026-05-16，到期即失效，现在打开只会看到裂图。已经把它们从 README 里摘掉，免得一直占着位置。

重新截图后放到 `docs/images/screenshots/`，按这三个文件名引用：

| 文件 | 内容 |
|---|---|
| `app.png` | 用户端（APP H5 或真机） |
| `wemedia.png` | 自媒体后台 |
| `admin.png` | 管理后台 |

图片进仓库，别再引外部临时地址——签名一过期，README 就是空白，这种事发生过一次就够了。

## 七、文档

先读 [文档中心](docs/README.md)，再按顺序：

| 序 | 文档 | 用途 |
|---|---|---|
| 1 | [后端工程说明](MYREADME.md) | 子工程职责 |
| 2 | [环境搭建与启动指南](docs/环境搭建与启动指南.md) | 依赖、端口、Nacos、自检 |
| 3 | [需求文档](docs/需求文档.md) | 三端功能基线与里程碑 |
| 4 | [架构设计说明书](docs/架构设计说明书.md) | 分层、调用链、数据架构 |
| 5 | [接口清单](docs/接口清单.md) | HTTP 接口汇总 |
| 6 | [数据库规范设计说明书](docs/心飞头条-数据库规范设计说明书.md) | 表设计与分片 |
| 7 | [部署运维手册](docs/部署运维手册.md) | 打包发布排障 |
| 8 | [开发规范](docs/开发规范.md) | 编码、分支、提交 |
| 9 | [变更日志](CHANGELOG.md) | 版本记录 |

## 八、许可

Apache-2.0，见 [LICENSE](LICENSE)。

## 九、联系

前端仓库：[xftt-portal](https://gitee.com/itxinfei/xftt-portal) ｜ QQ 747011882 ｜ 邮箱 747011882@qq.com