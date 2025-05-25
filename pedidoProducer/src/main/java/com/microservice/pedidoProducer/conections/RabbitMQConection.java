package com.microservice.pedidoProducer.conections;

import org.example.constantes.RabbitMQConstates;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {
    //Exchange standard quando inicia o serviço MQ
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }


    //Retorno da fila do rabbitMQ
    private Queue fila(String nomeFila){
        //Intancia a fila
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    //Relacionamento entre a Exchante e a fila
    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
    @PostConstruct
    private void adiciona() {

        //Criação da fila
        Queue filaEstoque = this.fila(RabbitMQConstates.FILA_PEDIDO);

        //Criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaEstoque);
    }

}
