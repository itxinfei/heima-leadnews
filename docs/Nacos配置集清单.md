# 心飞头条（xftt）Nacos 配置集清单

> 维护者：心飞团队　　最后更新：2026-10-23
> 中间件已统一部署在 `192.168.20.130`，本清单中的连接地址均指向该机。
> 配置来源：`xftt-common/src/main/resources/*.properties`（已核对），仅做集中化呈现，便于在 Nacos 控制台录入。

---

## 1. Nacos 连接信息（录入前先确认）

| 项 | 值 |
|---|---|
| 地址 | `192.168.20.130:8848` |
| 命名空间 | `xftt`（已存在，配置已录入，见第 8 节） |
| 分组 | `DEFAULT_GROUP` |
| 用户名 / 密码 | `xinfei` / `123456`（**全局管理员**，配置读写用此账号）；`xftt` / `123456` 可登录但未授权，配置 API 全部 403 |
| API | Nacos 3.x：登录 `POST /nacos/v1/auth/login`，配置读写 `POST/GET /nacos/v1/cs/configs`（`accessToken` 参数携带令牌，`tenant=xftt`） |

> **录入状态（2026-10-24）**：第 3 节 10 个 Data ID 中的 9 个 `.properties` 已通过 API 写入 `xftt` 命名空间并回读验证（UTF-8 中文注释完好）；`xftt-gateway.yml` 按第 4.2 节约定不录入（网关用本地 yml）。

> 各业务服务的 `application.properties` 通过
> `spring.config.import=optional:nacos:${spring.application.name}.properties?group=DEFAULT_GROUP`
> 导入**自身** Data ID。`optional:` 意味着缺失不报错，但缺失后相关连接会回退到 common 包内 classpath 默认值——**生产环境应显式录入，避免依赖打包默认值**。

---

## 2. 中间件连接地址总览（192.168.20.130）

| 中间件 | 地址 | 默认账号/口令 | 说明 |
|---|---|---|---|
| MySQL（经 MyCat） | `192.168.20.130:8066` | 见第 4 节 | 分库分表入口（**当前不可达**，已录入配置改指 3306 直连） |
| Redis | `192.168.20.130:6379` | 密码 `123456` | 缓存 / 登录态 |
| Kafka | `192.168.20.130:9092` | — | 行为 / 文章消息 |
| Elasticsearch | `http://192.168.20.130:9200` | — | 文章检索 |
| MinIO（对象存储） | `http://192.168.20.130:9005` | `ruoyi` / `12345678` | 素材 / 图片（替换原 FastDFS） |
| MongoDB | `192.168.20.130:27017` | db `xftt` | 热点数据 |
| ZooKeeper / HBase | `192.168.20.130:2181` | — | 序列号 / 冷数据 |
| Nacos | `192.168.20.130:8848` | `xinfei` / `123456` | 注册 / 配置 |

---

## 3. 需创建的 Data ID 列表

每个微服务一个 Data ID，分组 `DEFAULT_GROUP`，格式为 `properties`（**网关例外，为 `xftt-gateway.yml`**）。

| 序号 | Data ID | 归属服务 | 端口 |
|---|---|---|---|
| 1 | `xftt-gateway.yml` | xftt-gateway | 10000 |
| 2 | `xftt-login.properties` | xftt-login | 9001 |
| 3 | `xftt-article.properties` | xftt-article | 9003 |
| 4 | `xftt-behavior.properties` | xftt-behavior | 9004 |
| 5 | `xftt-user.properties` | xftt-user | 9005 |
| 6 | `xftt-media.properties` | xftt-media | 9006 |
| 7 | `xftt-admin.properties` | xftt-admin | 9007 |
| 8 | `xftt-crawler.properties` | xftt-crawler | 9008 |
| 9 | `xftt-images.properties` | xftt-images | 9010 |
| 10 | `xftt-migration.properties` | xftt-migration | 9012 |

> 端口与服务名取自 `xftt-common/src/main/resources/config/application.properties`，与 `sn.*` 一致。

---

## 4. 每个 Data ID 的内容（共享配置块）

> **重要**：因为各服务只导入自己的 Data ID，所以**每个 Data ID 都必须包含下面这段共享块**（除网关 yml 外，其余均为 properties 格式）。如需避免重复，可额外建一个共享 Data ID（如 `xftt-shared.properties`）并在各服务 `application.properties` 增加一行
> `spring.config.import=optional:nacos:xftt-shared.properties?group=DEFAULT_GROUP&refreshEnabled=true`，
> 再把下面共享块放进去。

### 4.1 共享块（复制进每个 `.properties` Data ID）

```properties
# ============ MySQL（经 MyCat 8066）============
# 注意：密码明文存储（MysqlCoreConfig 已移除反转逻辑）
mysql.core.jdbc.url=jdbc:mysql://192.168.20.130:8066/xftt?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
mysql.core.jdbc.username=<mycat_user>
mysql.core.jdbc.password=<real_password>
mysql.core.jdbc.driver=com.mysql.cj.jdbc.Driver
mysql.core.root-mapper=mappers
mysql.core.aliases.package=com.xftt.model
mysql.core.tx.scan.package=com.xftt

# ============ Redis ============
spring.data.redis.host=192.168.20.130
spring.data.redis.port=6379
spring.data.redis.password=123456
spring.data.redis.timeout=90000
spring.data.redis.lettuce.pool.max-active=8
spring.data.redis.lettuce.pool.max-idle=8
spring.data.redis.lettuce.pool.max-wait=-1
spring.data.redis.lettuce.pool.min-idle=0

# ============ Kafka ============
kafka.hosts=192.168.20.130:9092
kafka.group=xftt.${profiles.name}.${spring.application.name}

# ============ Elasticsearch ============
spring.elasticsearch.uris=http://192.168.20.130:9200
spring.elasticsearch.connection-timeout=20s
spring.elasticsearch.socket-timeout=20s

# ============ MinIO（对象存储，替换 FastDFS）============
minio.endpoint=http://192.168.20.130:9005
minio.access-key=ruoyi
minio.secret-key=12345678
minio.bucket=xftt
minio.public-url=http://192.168.20.130:9005/xftt/

# ============ MongoDB ============
mongo.host=192.168.20.130
mongo.port=27017
mongo.dbname=xftt

# ============ ZooKeeper / HBase ============
zk.host=192.168.20.130:2181
zk.sequence-path=/xftt/sequence/
hbase.zookeeper.quorum=192.168.20.130:2181
hbase.client.keyvalue.maxsize=500000

# ============ Quartz ============
spring.quartz.group-prefix=${spring.application.name}_
spring.quartz.scheduler-name=quartz.${profiles.name}.${spring.application.name}

# ============ 阿里云（OCR / 人脸，敏感）============
aliyun.access-key=LTAIiu7S1tYB8KV6
aliyun.secret=rpu99duohLQFl5CtOQnMGyMnL708wS
```

### 4.2 服务级差异（目前无强制差异）

- **xftt-login / xftt-user / xftt-article / xftt-behavior / xftt-media / xftt-admin / xftt-crawler / xftt-images / xftt-migration**：均使用同一份共享块即可。
- **xftt-gateway.yml**：网关只做路由，不连业务库，保留本地已有 `xftt-gateway/src/main/resources/application.yml` 内容即可（已正确指向 `192.168.20.130:8848` / namespace `xftt`），无需在 Nacos 重复录入共享块。

---

## 5. 验证清单（录入后逐项核对）

| 检查 | 方法 | 期望 |
|---|---|---|
| 命名空间存在 | Nacos 控制台「命名空间」 | 存在 `xftt` |
| Data ID 齐全 | 控制台「配置管理 → 配置列表」 | 10 个 Data ID（见第 3 节） |
| 服务能拉到配置 | 启动日志无 `Could not resolve placeholder` | 正常起服 |
| MySQL 能连 | 启动日志打印 `数据库连接：HikariDataSource` | 无连接异常 |
| Redis 能连 | 首次缓存/登录态操作 | 无 Jedis 超时 |
| Kafka 能连 | 行为上报 / 文章消息 | 无 Group 协调错误 |
| MinIO 能连 | 素材上传 | 返回 `minio.public-url` 前缀的 URL |

---

## 6. 已知坑（务必注意）

| 坑 | 说明 | 处理 |
|---|---|---|
| 密码反转 | ~~`MysqlCoreConfig.getRealPassword()` 对 `mysql.core.jdbc.password` 做 `StringUtils.reverse`~~ | ✅ 已修复（ENV-05）：反转逻辑已移除，配置直接填**真实密码明文**；Nacos 9 个配置集已同步改为明文 |
| 端口两个来源 | `xftt-common` 里 `port.gateway=8081`，但网关本地 yml 为 10000 | 统一以 10000 为准（ENV-02） |
| 共享配置重复 | 各服务只导入自身 Data ID，无共享块 | 见第 4 节开头建议，建 `xftt-shared.properties` 复用 |
| 阿里云密钥硬编码 | `aliyun.properties` 含真实 AK/SK | 生产应改从 Nacos 密文或 KMS 注入，不入库明文 |

---

## 7. 相关文档

- [环境搭建与启动指南](./环境搭建与启动指南.md)（ENV-03 指向本清单）
- [架构设计说明书](./架构设计说明书.md)
- [接口清单](./接口清单.md)

---

## 8. 录入记录（2026-10-24）

| 项 | 结果 |
|---|---|
| 已录入 Data ID | `xftt-{login,article,behavior,user,media,admin,crawler,images,migration}.properties` 共 9 个，分组 `DEFAULT_GROUP`，命名空间 `xftt` |
| 未录入 | `xftt-gateway.yml`（网关用本地 `application.yml`，见 4.2） |
| 数据源调整 | MyCat 8066 连接超时（10060），已录入配置改指 `192.168.20.130:3306/xftt` 直连（账号 `xftt`，`jdbc-password` 填明文 `123456`）；MyCat 恢复后按 4.1 节模板切回并重录 |
| 验证 | 逐个回读成功；UTF-8 中文注释完好；Redis/ES/MinIO 地址与第 2 节一致；测试配置已清理 |
| 操作方式 | API 录入（`POST /nacos/v1/cs/configs`，`xinfei` 管理员令牌） |
