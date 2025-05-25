package com.microservices.consumerProduto.consumer;

import com.microservices.consumerProduto.service.PedidoRecebidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.constantes.RabbitMQConstates;
import org.example.domains.pedido.PedidoDTO;

import org.example.domains.pedido.PedidoStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {


    private final PedidoRecebidoService pedidoRecebidoService;

    public PedidoConsumer(PedidoRecebidoService pedidoRecebidoService)
    {
        this.pedidoRecebidoService = pedidoRecebidoService;
    }

    //Define a fila a ser escutada
    @RabbitListener(queues = RabbitMQConstates.FILA_PEDIDO)
    public void consumidor(String mensagem) throws JsonProcessingException {

        //Fou necessário trafegar como mensagem e após converrter a DTO, pois estava apresentando erro ao
        //desializar o DTO, então tiver que implementar conversor json "JAcksonn"
        PedidoDTO pedidoDTO = new ObjectMapper().readValue(mensagem, PedidoDTO.class);

        System.out.println("Recebido pedido ID: " + pedidoDTO.getId());

        //Atualiza status e logo após salva em mémoria
        pedidoDTO.setStatus(PedidoStatus.PENDENTE);
        pedidoRecebidoService.salvarPedido(pedidoDTO);

        try {
            Thread.sleep(2000); // Delay artificial
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //atualiza o status após 2 min
        pedidoRecebidoService.atualizarStatus(pedidoDTO.getId(), PedidoStatus.PROCESSADO);
        System.out.println("Pedido processado ID: " + pedidoDTO.getId());
    }


}
