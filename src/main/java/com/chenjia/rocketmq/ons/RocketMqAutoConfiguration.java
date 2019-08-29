package com.chenjia.rocketmq.ons;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Properties;

/**
 * 自动配置类
 *
 * @author chenjia
 */
@Configuration
@EnableConfigurationProperties(AliyunRocketMqProperties.class)
public class RocketMqAutoConfiguration {

    @Bean
    MessageHandlerContext messageHandlerContext() {
        return new MessageHandlerContext();
    }

    @Bean
    CommonListener commonListener(MessageHandlerContext messageHandlerContext) {
        return new CommonListener(messageHandlerContext);
    }

    @Bean
    HandlerProcessor handlerProcessor() {
        return new HandlerProcessor();
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean rocketmqProducer(AliyunRocketMqProperties mqProperties) {
        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(getProperties(mqProperties));
        return producerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer rocketmqConsumer(AliyunRocketMqProperties mqProperties, CommonListener commonListener) {
        Consumer consumer = ONSFactory.createConsumer(getProperties(mqProperties));
        consumer.subscribe(mqProperties.getTopic(), getSubExpression(mqProperties.getTags()), commonListener);
        return consumer;
    }

    private Properties getProperties(AliyunRocketMqProperties mqProperties) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, mqProperties.getGroupId());
        properties.setProperty(PropertyKeyConst.AccessKey, mqProperties.getAccessKey());
        properties.setProperty(PropertyKeyConst.SecretKey, mqProperties.getSecretKey());
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, mqProperties.getSendMsgTimeout());
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, mqProperties.getNamesrvAddr());
        return properties;
    }

    private String getSubExpression(List<String> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            throw new IllegalArgumentException("tags 不能为空");
        }
        return String.join("||", tags);
    }
}
