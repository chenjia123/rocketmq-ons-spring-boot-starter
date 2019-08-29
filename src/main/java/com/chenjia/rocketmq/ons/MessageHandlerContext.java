package com.chenjia.rocketmq.ons;

import java.util.Collections;
import java.util.Map;

/**
 * 消息处理器上下文，用于持有并维护 {@link MessageHandler} bean
 *
 * @author chenjia
 */
class MessageHandlerContext {

    /**
     * 持有消息处理器 {@link MessageHandler} bean，key 值就是 tag
     */
    private static Map<String, MessageHandler> handlerStrategyBeanMap;

    /**
     * 根据一个确定的 tag 返回处理逻辑
     *
     * @param tag 处理器对应的 tag
     * @return {@link MessageHandler} 消息处理器 bean
     * @throws IllegalArgumentException 当输入的 tag 不存在时
     */
    MessageHandler getHandler(String tag) {
        MessageHandler messageHandler = handlerStrategyBeanMap.get(tag);
        if (messageHandler == null) throw new IllegalArgumentException("没有对应的消息类型");
        return messageHandler;
    }

    /**
     * 用于启动时设置 tag 与 {@link MessageHandler } bean 的 Map 关系
     *
     * @param initializedMap 初始化的map
     *
     */
    static void initHandlerMap(Map<String, MessageHandler> initializedMap) {
        handlerStrategyBeanMap = Collections.unmodifiableMap(initializedMap);
    }
}
