<div align="center">  
  <h1>🚀 心飞头条</h1>  
  <p><strong>微服务 + 大数据架构的资讯平台解决方案</strong></p>  

<div style="white-space: nowrap; overflow-x: auto; padding: 15px 0;">
  <img alt="JDK" src="https://img.shields.io/badge/JDK-25-brightgreen">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-3.9%2B-yellowgreen">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen">
  <img alt="Spring Cloud" src="https://img.shields.io/badge/Spring%20Cloud-2025.1.3-orange">
  <img alt="MyBatis" src="https://img.shields.io/badge/MyBatis-3.5.19-blue">
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-9.7.0-blueviolet">
  <img alt="Jackson" src="https://img.shields.io/badge/Jackson-3.1.5-informational">
  <img alt="JWT" src="https://img.shields.io/badge/JWT-0.12.6-lightgrey">
  <img alt="Apache Commons IO" src="https://img.shields.io/badge/Apache%20Commons%20IO-2.18.0-lightgrey">
  <img alt="Apache Commons Codec" src="https://img.shields.io/badge/Apache%20Commons%20Codec-1.21.0-lightgrey">
  <img alt="Apache Commons Lang3" src="https://img.shields.io/badge/Apache%20Commons%20Lang3-3.20.0-lightgrey">
  <img alt="Elasticsearch" src="https://img.shields.io/badge/Elasticsearch-9.4.5-red">
  <img alt="Kafka" src="https://img.shields.io/badge/Kafka-4.2.1-red">
  <img alt="Nacos" src="https://img.shields.io/badge/Nacos-3.x-blue">
  <img alt="前端-APP" src="https://img.shields.io/badge/前端APP-uni--app%20Vue3-blueviolet">  
  <img alt="前端-后台" src="https://img.shields.io/badge/前端后台-Vue3%20+%20Element%20Plus-orange">  
  <img alt="前端-状态" src="https://img.shields.io/badge/状态管理-Pinia-blue">  
</div>

 </div>

### 🌟 项目简介  
**心飞头条** 是基于 **微服务 + 大数据技术架构** 构建的资讯平台，专注于获取最新热点新闻并通过大数据分析用户兴趣进行精准推送。  
项目涵盖 **APP端、自媒体后台、管理系统** 三大模块，支持高并发场景下的实时数据处理与个性化推荐。  

---

### 🔍 核心亮点  
- ✅ **全栈技术覆盖**：uni-app（APP）、Vue3 + Element Plus（后台）、Spring Cloud 微服务、AI（实名认证自动化）  
- ✅ **分库分表设计**：MyCat 中间件 + 自定义热点分片算法，5 大核心库解耦业务模块  
- ✅ **实时与离线双引擎**：Kafka 消息驱动、Kafka Stream 实时聚合热点 + 定时任务离线统计  
- ✅ **统一视觉语言**：三端对齐今日头条设计令牌（品牌红 `#ED4040`）  

---

### 📦 技术栈 
| 类型       | 技术栈                                                                 |
|------------|------------------------------------------------------------------------|
| **前端·APP** | uni-app 3.x + Vue 3 + TypeScript + Pinia + wot-design-uni            |
| **前端·后台** | Vue 3 + Vite 5 + TypeScript + Element Plus + Axios（wemedia / admin） |
| **后端**   | Spring Boot 4.1.1 + Spring Cloud 2025.1.3 + Spring Cloud Gateway + OpenFeign |
| **注册配置** | Nacos 3.x                                                            |
| **消息与计算** | Kafka 4.2.1 + Kafka Stream                                          |
| **数据库** | MySQL 9.7.0（MyCat 分库分表）+ MongoDB + HBase                       |
| **检索与缓存** | Elasticsearch 9.4.5 + Redis                                        |
| **其他**   | FastDFS + ZooKeeper + Quartz + AI（OCR + 人脸比对）                  |

> 修改点：前端由 Weex/Vue2 重构为 uni-app + Vue3；技术栈表对齐当前 `master` 实际依赖。

---

### 📚 文档入口  

全部文档集中在 `docs/`，**首次接触项目建议按以下顺序阅读**：

| 顺序 | 文档 | 说明 |
|---|---|---|
| 1 | [文档中心](docs/README.md) | 全部文档索引与写作约定 |
| 2 | [后端工程说明](MYREADME.md) | 15 个子工程职责划分 |
| 3 | [环境搭建与启动指南](docs/环境搭建与启动指南.md) | 依赖、端口、Nacos 配置、自检 |
| 4 | [需求文档](docs/需求文档.md) | 三端功能基线、逐页 PRD、里程碑 |
| 5 | [架构设计说明书](docs/架构设计说明书.md) | 分层架构、调用链、数据架构 |
| 6 | [接口清单](docs/接口清单.md) | 全部 HTTP 接口汇总 |
| 7 | [部署运维手册](docs/部署运维手册.md) | 打包、发布、排障 |
| 8 | [开发规范](docs/开发规范.md) | 编码、分支、提交规范 |
| 9 | [数据库规范设计说明书](docs/心飞头条-数据库规范设计说明书.md) | 表设计与分库分表 |
| 10 | [变更日志](CHANGELOG.md) | 版本变更记录 |

### 🚀 快速开始  

```bash
# 1. 启动 Nacos（单机模式），并创建 xftt 命名空间
# 2. 初始化数据库
mysql -u root -p < xinfei-leadnews.sql

# 3. 编译并启动后端
mvn -U clean compile
mvn -pl service-gateway spring-boot:run   # 网关 :10000
mvn -pl xftt-login spring-boot:run        # :9001
mvn -pl xftt-article spring-boot:run      # :9003

# 4. 启动前端
cd xftt-web/xftt-app     && npm install && npm run dev:h5   # APP H5
cd xftt-web/xftt-wemedia && npm install && npm run dev      # :8081
cd xftt-web/xftt-admin   && npm install && npm run dev      # :8082
```

> 端口表、Nacos 配置集清单与常见问题见 [环境搭建与启动指南](docs/环境搭建与启动指南.md)。

### 🌐 在线体验  
📁 [前端代码仓库](https://gitee.com/itxinfei/xftt-portal)  

---

### 📷 功能截图  
#### **APP端功能截图**  
![APP功能截图](https://broadscope-dialogue-new.oss-cn-beijing.aliyuncs.com/output/20250516/16647e76ac21a04f586176b03858a1b6.png?Expires=1778894083&OSSAccessKeyId=LTAI5tL97mBYzVcjkG1cUyin&Signature=WiMEbH6W4nkSQIpEp%2FgaT0A0X6I%3D)  

#### **自媒体后台截图**  
![WeMedia功能截图](https://broadscope-dialogue-new.oss-cn-beijing.aliyuncs.com/output/20250516/f063d0e70f0abeca273018be557b0fd3.png?Expires=1778894083&OSSAccessKeyId=LTAI5tL97mBYzVcjkG1cUyin&Signature=4igLiywn2z3eZBQqnW3sKH0jGYg%3D)  

#### **管理系统截图**  
![Admin功能截图](https://broadscope-dialogue-new.oss-cn-beijing.aliyuncs.com/output/20250516/b76428e6a09d058a30f3739af1e76562.png?Expires=1778894083&OSSAccessKeyId=LTAI5tL97mBYzVcjkG1cUyin&Signature=CD6IkQ1NdV7S8YGw%2FU3AZ5KGcig%3D)  

---

### 🧩 模块功能概览  

#### **APP端功能**  
| 模块         | 核心功能                                                                 |
|--------------|--------------------------------------------------------------------------|
| **频道管理** | 动态添加频道，基于用户兴趣推荐标签                                        |
| **文章浏览** | 展示标题/图片/评论数，监控用户阅读行为（停留时长、完读率）                |
| **搜索系统** | 联想搜索 + 历史记录追踪                                                 |
| **用户中心** | 收藏/关注管理、系统设置、手机号验证码登录/注册（首次自动注册）            |
| **实名认证** | 身份证认证 + 自媒体发布权限                                             |

#### **自媒体（WeMedia）**  
| 模块         | 核心功能                                                                 |
|--------------|--------------------------------------------------------------------------|
| **内容管理** | 文章状态管理（草稿/已发布/未通过）、上下架操作                            |
| **评论管理** | 全部评论展示、关闭评论功能                                                |
| **素材管理** | 图片库管理，支持多图发布                                                  |
| **数据分析** | 图文阅读量/评论量统计、粉丝画像（性别/年龄/终端分布）                      |

#### **管理系统（Admin）**  
| 模块         | 核心功能                                                                 |
|--------------|--------------------------------------------------------------------------|
| **用户管理** | 用户增删改查、冻结违规账号                                                |
| **内容审核** | 审核文章文字/图片/敏感信息                                                |
| **频道管理** | 新增/修改频道及关联标签                                                   |
| **数据统计** | 日活用户、访问量趋势、文章采集量/阅读量统计                               |

---

### 🗂️ 分库设计  
通过分库分表应对高并发场景，5大核心数据库解耦业务：  
```plaintext
AppInfo     -> 用户信息、文章信息、评论数据  
Behavior    -> 用户行为日志（点赞/转发/评论）  
WeMedia     -> 自媒体图文数据、粉丝关系  
Crawlers    -> 爬虫采集的文章内容  
Admin       -> 管理员账号、权限配置  
```

---

### 📄 项目术语定义  
- **项目**：泛指心飞头条整个项目或某一模块  
- **工程**：泛指心飞头条某一源码工程  
- **用户**：APP端用户  
- **自媒体人**：通过自媒体系统发布文章的用户  
- **管理员**：管理系统使用用户  

---

### 🗂️ 工程结构  

```
xftt/（父工程 com.xftt:xftt）
├── xftt-common / xftt-utils / xftt-apis / xftt-model   公共库
├── xftt-login 9001     xftt-user 9005     xftt-article 9003
├── xftt-behavior 9004  xftt-media 9006    xftt-admin 9007
├── xftt-crawler 9008   xftt-images 9010   xftt-migration 9012
├── service-gateway 10000   service-nacos 8848   service-mycat 8066
└── xftt-web/{xftt-app, xftt-wemedia, xftt-admin}        前端三端
```

后端服务端口取自 `xftt-common/src/main/resources/config/application.properties`，为唯一权威来源。

### 🧾 部署与依赖  
- **JDK**：25  
- **Maven**：3.9+  
- **Node.js**：18 LTS+（Vite 5 要求）  
- **中间件**：Nacos / Kafka / ZooKeeper / Redis / FastDFS / Elasticsearch  
- **部署**：见 [部署运维手册](docs/部署运维手册.md)  

---

### 🤝 参与贡献  
欢迎提交 Pull Request！  
1. Fork 项目  
2. 创建新分支 (`git checkout -b feature/YourFeatureName`)  
3. 提交代码 (`git commit -m 'Add some feature'`)  
4. 推送分支 (`git push origin feature/YourFeatureName`)  
5. 创建 Pull Request  

---

