package com.microservice.pedidoProducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domains.pedido.PedidoDTO;
import org.example.domains.pedido.PedidoStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitMQService {

    //Injecção de depencia, utilezando a interface para envio da mensagem conforme os parametro do properties
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    public UUID enviarMensagem(String nomeFila, PedidoDTO pedidoDTO){

        pedidoDTO.setId(UUID.randomUUID());

        //Conversão da mensagem e envio
        try {
            String mensagemJson = this.objectMapper.writeValueAsString(pedidoDTO);
            this.rabbitTemplate.convertAndSend(nomeFila, mensagemJson);
        } catch (Exception e){
            e.printStackTrace();
        }
        return pedidoDTO.getId();
    }
}
