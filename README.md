# 黑马头条项目  
**微服务 + 大数据架构的资讯平台解决方案**  
🚀 项目地址：[https://gitee.com/itxinfei](https://gitee.com/itxinfei)  
👥 QQ交流群：[661543188](https://qm.qq.com/cgi-bin/qm/qr?k=9yLlyD1dRBL97xmBKw43zRt0-6xg8ohb&jump_from=webapi)  
📧 邮箱支持：[747011882@qq.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=f0hLSE9OTkdHTT8ODlEcEBI)  

[![JDK 1.8+](https://img.shields.io/badge/JDK-1.8%2B-brightgreen)](https://openjdk.org/)
[![Maven 3.6.3+](https://img.shields.io/badge/maven-3.6.3%2B-yellowgreen)](https://maven.apache.org/)
[![License Apache](https://img.shields.io/badge/license-Apache-green)](https://www.apache.org/licenses/LICENSE-2.0)

---

### **项目简介**  
随着智能手机普及，碎片化获取新闻成为主流需求。黑马头条项目采用 **微服务 + 大数据技术架构**，实现新闻采集、分类、个性化推荐及用户行为分析，满足高并发、实时性要求高的场景需求。

#### **核心亮点**  
- **全栈技术覆盖**：前端（Weex/Vue）、后端（Spring Cloud）、大数据（Spark/Hive）、AI（实名认证自动化）  
- **分库分表设计**：5大核心数据库解耦业务模块，提升扩展性  
- **实时与离线双引擎**：Kafka实时消息、Spark Streaming实时分析 + Hive离线统计  

---

### **在线体验**  
🔗 [后台管理系统体验地址](https://heima-admin-java.itheima.net/#/login)  
📁 [前端代码仓库](https://gitee.com/itxinfei/heima-leadnews-portal)

---

### **功能架构**  
#### **1. APP端功能**  
| 模块         | 核心功能                                                                 |
|--------------|--------------------------------------------------------------------------|
| **频道管理** | 动态添加频道，基于用户兴趣推荐标签                                        |
| **文章浏览** | 展示标题/图片/评论数，监控用户阅读行为（停留时长、完读率）                |
| **搜索系统** | 联想搜索 + 历史记录追踪                                                 |
| **用户中心** | 收藏/关注管理、系统设置、手机号验证码登录/注册（首次自动注册）            |
| **实名认证** | 身份证认证 + 自媒体发布权限                                             |

#### **2. 自媒体（WeMedia）**  
| 模块         | 核心功能                                                                 |
|--------------|--------------------------------------------------------------------------|
| **内容管理** | 文章状态管理（草稿/已发布/未通过）、上下架操作                            |
| **评论管理** | 全部评论展示、关闭评论功能                                                |
| **素材管理** | 图片库管理，支持多图发布                                                  |
| **数据分析** | 图文阅读量/评论量统计、粉丝画像（性别/年龄/终端分布）                      |

#### **3. 管理系统（Admin）**  
| 模块         | 核心功能                                                                 |
|--------------|--------------------------------------------------------------------------|
| **用户管理** | 用户增删改查、冻结违规账号                                                |
| **内容审核** | 审核文章文字/图片/敏感信息                                                |
| **频道管理** | 新增/修改频道及关联标签                                                   |
| **数据统计** | 日活用户、访问量趋势、文章采集量/阅读量统计                               |

---

### **技术栈全景**  
#### **前端技术**  
- **APP端**：Weex + Vue + WebSocket（实时消息推送）  
- **自媒体系统**：Vue + Echarts（粉丝画像可视化）  
- **管理系统**：Vue + Echarts + WebSocket（实时看板更新）  

#### **后端技术栈**  
- **微服务框架**：Spring Boot + Spring Cloud Gateway（网关路由）  
- **消息队列**：Kafka（实时消息通知）  
- **缓存中间件**：Redis（热数据缓存、NoSession设计）  
- **数据库**：MySQL（用户数据） + MongoDB（热数据） + HBase（冷数据存储）  
- **搜索引擎**：Elasticsearch（冷数据/文章索引）  

#### **大数据与AI**  
- **离线分析**：Hive + Spark（统计报表生成）  
- **实时计算**：Spark Streaming + Kafka（文章推荐引擎）  
- **知识图谱**：Neo4j（粉丝等级计算、关系分析）  
- **AI能力**：实名认证自动化（OCR + 人脸比对）  

---

### **分库设计**  
通过分库分表应对高并发场景，5大核心数据库解耦业务：  
```plaintext
AppInfo     -> 用户信息、文章信息、评论数据  
Behavior    -> 用户行为日志（点赞/转发/评论）  
WeMedia     -> 自媒体图文数据、粉丝关系  
Crawlers    -> 爬虫采集的文章内容  
Admin       -> 管理员账号、权限配置  