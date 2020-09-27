package com.hecai.service.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布订阅模式--订阅者
 *
 * 消费者通过一个临时队列和交换器绑定，接收发送到交换器上的消息
 */
public class Consumer {

    private static Runnable receive = new Runnable() {
        @Override
        public void run() {
            // 1、创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            // 2、设置连接属性
            factory.setHost("192.168.10.113");
            factory.setUsername("admin");
            factory.setPassword("123abc");

            Connection connection = null;
            Channel channel = null;
            final String clientName = Thread.currentThread().getName();

            try {
                // 3、从连接工厂获取连接
                connection = factory.newConnection("消费者");

                // 4、从链接中创建通道
                channel = connection.createChannel();

                // 代码定义交换器
                channel.exchangeDeclare("pro", "fanout");
                //  还可以定义一个临时队列，连接关闭后会自动删除，此队列是一个排他队列
                String queueName = channel.queueDeclare().getQueue();
                // 将队列和交换器绑定
                channel.queueBind(queueName, "fanoutExchange", "");

                // 定义消息接收回调对象
                DeliverCallback callback = new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println(clientName + " 收到消息：" + new String(message.getBody(), "UTF-8"));
                    }
                };
                // 监听队列
                channel.basicConsume(queueName, true, callback, new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println(consumerTag);
                    }
                });

                System.out.println(clientName + " 开始接收消息");
                System.in.read();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } finally {
                // 8、关闭通道
                if (channel != null && channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }

                // 9、关闭连接
                if (connection != null && connection.isOpen()) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        new Thread(receive, "c1").start();
    }

}
