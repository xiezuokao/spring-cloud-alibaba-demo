package com.hecai.service.mq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanoutReceiver {

//    @RabbitListener(queues = "fanout.A")
//    @RabbitListener(
//            bindings =
//                    {
//                            @QueueBinding(value = @Queue(value = "A", durable = "true"),
//                                    exchange = @Exchange(value = "fanoutExchange", type = "fanout"))
//                    })
//    @RabbitHandler
//    public void processA(String testMessage) {
//        log.info("FanoutReceiverA消费者收到消息  : " + testMessage);
//    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = "", durable = ""),
                                    exchange = @Exchange(value = "fanoutExchange", type = "fanout"))
                    })
//    @RabbitHandler
    public void processPro(String testMessage) {
        log.info("FanoutReceiverPro消费者收到消息  : " + testMessage);
    }
}
 
