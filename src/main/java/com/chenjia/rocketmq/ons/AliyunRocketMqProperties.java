package com.chenjia.rocketmq.ons;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 在 application.yml/properties 中配置，除了具有默认配置的项，其余必填
 *
 * @author chenjia
 */
@ConfigurationProperties(prefix = "aliyun-rocketmq")
public class AliyunRocketMqProperties {
    private String topic;
    private String groupId;
    private String accessKey;
    private String secretKey;
    private String namesrvAddr;
    private String sendMsgTimeout = "2000";
    private List<String> tags;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSendMsgTimeout() {
        return sendMsgTimeout;
    }

    public void setSendMsgTimeout(String sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }
}
