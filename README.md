<div align="center">  
  <h1>🚀 黑马头条</h1>  
  <p><strong>微服务 + 大数据架构的资讯平台解决方案</strong></p>  
<div style="white-space: nowrap; overflow-x: auto; padding: 15px 0;">
  <!-- 基础依赖 -->
  <img alt="JDK" src="https://img.shields.io/badge/JDK-1.8%2B-brightgreen">  
  <img alt="Maven" src="https://img.shields.io/badge/maven-3.3%2B-yellowgreen">  
  <img alt="License" src="https://img.shields.io/badge/license-Apache-green">  

  <!-- 前端 -->
  <img alt="前端" src="https://img.shields.io/badge/前端-Vue3%20+%20Weex-blueviolet">  
  <img alt="前端框架" src="https://img.shields.io/badge/前端框架-ECharts%20+%20Vant-orange">  

  <!-- 后端 -->
  <img alt="后端框架" src="https://img.shields.io/badge/后端-SpringCloud%20+%20MyBatis-green">  
  <img alt="网关" src="https://img.shields.io/badge/网关-SpringCloudGateway-brightgreen">  

  <!-- 大数据 -->
  <img alt="大数据" src="https://img.shields.io/badge/大数据-Spark%20+%20Hive%20+%20HBase-red">  

  <!-- 数据库 -->
  <img alt="数据库" src="https://img.shields.io/badge/数据库-MySQL%20+%20MongoDB%20+%20Redis-yellowgreen">  

  <!-- 中间件 -->
  <img alt="消息队列" src="https://img.shields.io/badge/消息队列-Kafka%20+%20RocketMQ-blue">  
  <img alt="配置中心" src="https://img.shields.io/badge/配置中心-Nacos%20+%20Sentinel-orange">  

  <!-- 监控 -->
  <img alt="监控" src="https://img.shields.io/badge/监控-Prometheus%20+%20SkyWalking-purple">  
</div>

 </div>

### 🌟 项目简介  
**黑马头条** 是基于 **微服务 + 大数据技术架构** 构建的资讯平台，专注于获取最新热点新闻并通过大数据分析用户兴趣进行精准推送。  
项目涵盖 **APP端、自媒体后台、管理系统** 三大模块，支持高并发场景下的实时数据处理与个性化推荐。  

---

### 🔍 核心亮点  
- ✅ **全栈技术覆盖**：前端（Weex/Vue）、后端（Spring Cloud）、大数据（Spark/Hive）、AI（实名认证自动化）  
- ✅ **分库分表设计**：5大核心数据库解耦业务模块，提升扩展性  
- ✅ **实时与离线双引擎**：Kafka实时消息、Spark Streaming实时分析 + Hive离线统计  

---

### 📦 技术栈 
| 类型       | 技术栈                                                                 |
|------------|------------------------------------------------------------------------|
| **前端**   | Weex + Vue + ECharts + WebSocket                                        |
| **后端**   | Spring Boot + Spring Cloud Gateway + Kafka + Redis                    |
| **大数据** | Spark + Hive + HBase + Neo4j                                          |
| **数据库** | MySQL + MongoDB + Elasticsearch                                       |
| **其他**   | FastDFS + ZooKeeper + AI（OCR + 人脸比对）                            |

---

### 🌐 在线体验  
🔗 [后台管理系统体验地址](https://heima-admin-java.itheima.net/#/login)  
📁 [前端代码仓库](https://gitee.com/itxinfei/heima-leadnews-portal)  

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
- **项目**：泛指黑马头条整个项目或某一模块  
- **工程**：泛指黑马头条某一源码工程  
- **用户**：APP端用户  
- **自媒体人**：通过自媒体系统发布文章的用户  
- **管理员**：管理系统使用用户  

---

### 🧾 部署与依赖  
- **JDK**：1.8+  
- **Maven**：3.6.3+  
- **中间件**：Kafka/ZooKeeper/Redis/FastDFS  

---

### 🤝 参与贡献  
欢迎提交 Pull Request！  
1. Fork 项目  
2. 创建新分支 (`git checkout -b feature/YourFeatureName`)  
3. 提交代码 (`git commit -m 'Add some feature'`)  
4. 推送分支 (`git push origin feature/YourFeatureName`)  
5. 创建 Pull Request  

---

### 📜 许可证  
Apache License 2.0 © [心飞为你飞](https://gitee.com/itxinfei)  

---

---
### 🔗 项目链接 & 社区支持  
![微信公众号二维码](docs/心飞为你飞.jpg)  
🚀 项目地址：[https://gitee.com/itxinfei/heima-leadnews](https://gitee.com/itxinfei/heima-leadnews)  
👥 QQ交流群：[661543188](https://qm.qq.com/cgi-bin/qm/qr?k=gNgch-wCkfUu-QbI7DZSudrax2BN7vY0&jump_from=webapi&authKey=QHSRnxQvu+h5S3AXGn/DSHrVPiFQAYEk6bSlCE1lS276SFjQAUagV4FG7bHf0OSM)  
📧 邮箱支持：[747011882@qq.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=f0hLSE9OTkdHTT8ODlEcEBI)  



