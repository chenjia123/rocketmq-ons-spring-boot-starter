package com.chenjia.rocketmq.ons;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一的 {@link MessageListener} 实现，并在内部结合 IoC 容器实现策略模式
 *
 * @author chenjia
 */
class CommonListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(CommonListener.class);

    private final MessageHandlerContext context;

    CommonListener(MessageHandlerContext context) {
        this.context = context;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Action consume(Message message, ConsumeContext consumeContext) {
        String tag = message.getTag();
        MessageHandler handler = context.getHandler(tag);
        logger.debug("接收到消息：{}", message);
        try {
            handler.accept(new String(message.getBody()));
        } catch (Exception e) {
            logger.error("消息处理出错，message={}，e={}", message, e);
            return handler.onError(message, e);
        }
        return Action.CommitMessage;
    }
}
