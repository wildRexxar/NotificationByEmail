package com.example.NotificationByEmail.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String EXCHANGE = "exchange";
    public static final String QUEUE = "sending";

    @Bean
    public Queue receiveQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public DirectExchange rpcExchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding receiveBinding() {
        return BindingBuilder.bind(receiveQueue()).to(rpcExchange()).with(QUEUE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}