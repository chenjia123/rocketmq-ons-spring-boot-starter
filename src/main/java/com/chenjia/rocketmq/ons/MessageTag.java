package com.chenjia.rocketmq.ons;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 用于声明一个消息处理器，并在 {@link MessageTag#value()} 中声明所要处理的消息的 tag
 *
 * @author chenjia
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface MessageTag {
    /**
     * 单个 tag 名，指定此处理器将会处理的消息
     */
    String value();
}
