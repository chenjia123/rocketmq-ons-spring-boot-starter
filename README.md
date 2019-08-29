# rocketmq-ons-spring-boot-starter
对阿里云rocketmq服务SDK-ons 的封装，并实现的对tag的策略模式处理，使得消费端仅仅需要关心单个tag的处理逻辑

[阿里云rocketMQ参考](https://help.aliyun.com/document_detail/29532.html?spm=5176.234368.1278132.btn4.23ffdb25Ytf0HO)

apache 官方提供了对 RocketMQ 的 spring-boot-starter 支持，但是至少这个[需求](https://github.com/apache/rocketmq-spring/issues/103)尚未解决，个人认为这是
刚需，所以自行实现了。

目前尚未对消息体的自动序列化与反序列化提供支持，欢迎 PR ！
