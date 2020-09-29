# spring-cloud-alibaba-demo
* 下载 [nacos-server-1.3.1](https://github.com/alibaba/nacos/releases)
* 下载 [seata-server-1.3.0](https://github.com/seata/seata/releases)
* 下载 [sentinel-dashboard-1.8.0](https://github.com/alibaba/Sentinel/releases)

## 技术栈
* nacos 注册中心与配置中心
* gateway 服务网关
* sentinel 限流熔断（在gateway层结合nacos动态配置接口限流熔断）
* openfeign RPC远程调用
* seata 分布式事务
* druid 连接池
* mybatisplus ORM
* rabbit mq 
* jwt token
* aop日志切面
* swagger2 网关整合swagger文档
* 全局统一异常处理以及rest ful风格统一返回封装
