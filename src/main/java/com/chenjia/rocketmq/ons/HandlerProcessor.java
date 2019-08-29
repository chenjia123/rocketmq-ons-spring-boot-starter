package com.chenjia.rocketmq.ons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 在 bean 创建完毕后，创建 tag 与 {@link MessageHandler} 类 bean 的映射关系
 *
 * @author chenjia
 */
class HandlerProcessor implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(HandlerProcessor.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> handlerMap = applicationContext.getBeansWithAnnotation(MessageTag.class);
        Map<String, MessageHandler> initialMap = new HashMap<>();
        handlerMap.forEach((k, v) -> {
            if (!(v instanceof MessageHandler)) {
                logger.error("非 [com.chenjia.rocketmq.ons.MessageHandler] 实现类!");
                return;
            }
            MessageHandler messageHandler = (MessageHandler) v;
            String tag = messageHandler.getClass().getAnnotation(MessageTag.class).value();
            initialMap.put(tag, messageHandler);
        });
        MessageHandlerContext.initHandlerMap(initialMap);
    }
}
