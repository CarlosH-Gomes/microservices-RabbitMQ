package com.microservices.consumerProduto;

import org.example.constantes.RabbitMQConstates;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    //True é apenas para não apagar a fila caso o RAbbit reinicie
    @Bean
    public Queue filaPedidos() {
        return new Queue(RabbitMQConstates.FILA_PEDIDO, true);
    }


    //Apenas para converter a mensagem em json utilizando o Jackson
    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

        //pacotes a serem deserializado
        typeMapper.setTrustedPackages("org.example.domains");
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }


}
