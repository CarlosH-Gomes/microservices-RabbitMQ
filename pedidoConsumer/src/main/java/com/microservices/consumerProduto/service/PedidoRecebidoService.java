package com.microservices.consumerProduto.service;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.example.domains.pedido.PedidoDTO;
import org.example.domains.pedido.PedidoStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PedidoRecebidoService {

    private final ConcurrentHashMap<UUID, PedidoDTO> pedidos = new ConcurrentHashMap<>();

    public void salvarPedido(PedidoDTO pedido) {
        pedidos.put(pedido.getId(), pedido);
    }

    public PedidoDTO getPedido(UUID id) {
        return pedidos.get(id);
    }

    public void atualizarStatus(UUID id, PedidoStatus status) {
        PedidoDTO pedido = pedidos.get(id);

        if (pedido != null) {
            pedido.setStatus(status);
        }
    }
}
