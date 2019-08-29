package com.chenjia.rocketmq.ons;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

/**
 * 消息处理器，用于实现处理逻辑。
 * <p>
 * <ol>
 * <li>只包含消息体内容，其它内容在更上层提供；</li>
 * <li>不同于{@link MessageListener}，此处只会针对某一种tag类型的消息进行处理，{@link MessageListener}的实现类中将会使用策略模式决定执行哪一种{@link MessageHandler}；</li>
 * </ol>
 *
 * @author chenjia
 */
public interface MessageHandler {
    /**
     * 具体某个 tag 类型下的消息处理逻辑
     *
     * @param msgBody 消息体，String 类型，需要自行做 JSON 序列化处理，推荐 fastjson
     */
    void accept(String msgBody);

    /**
     * 如果想处理错误，应该重写这个方法
     *
     * @return {@link Action} 消费端出现错误时，告知服务端应对的逻辑，默认将会执行 RocketMQ 的
     * <a href="https://help.aliyun.com/document_detail/43490.html?spm=a2c4g.11186623.6.555.16b7208dLRtlPO">延迟重试策略</a>
     */
    default Action onError(Message message, Exception e) {
        // DO NOTHING
        return Action.ReconsumeLater;
    }

}
