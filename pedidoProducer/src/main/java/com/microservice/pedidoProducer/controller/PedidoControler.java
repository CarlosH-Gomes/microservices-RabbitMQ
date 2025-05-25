package com.microservice.pedidoProducer.controller;

import org.example.constantes.RabbitMQConstates;
import org.example.domains.pedido.PedidoDTO;
import com.microservice.pedidoProducer.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.UID;
import java.util.UUID;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoControler {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    private ResponseEntity<UUID> insertPedido(@RequestBody PedidoDTO pedidoDTO){

        return ResponseEntity.ok(this.rabbitMQService.enviarMensagem(RabbitMQConstates.FILA_PEDIDO, pedidoDTO));
    }
}
