package com.hecai.service.mq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RabbitListener(queues = "topic.man1")
public class TopicManReceiver {
 
    @RabbitHandler
    public void process(Map testMessage) {
        log.info("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}