package com.microservices.consumerProduto.controller;


import com.microservices.consumerProduto.service.PedidoRecebidoService;
import org.example.domains.pedido.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRecebidoService pedidoRecebidoService;

    public PedidoController(PedidoRecebidoService pedidoRecebidoService) {
        this.pedidoRecebidoService = pedidoRecebidoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> consultarStatus(@PathVariable UUID id) {
        PedidoDTO pedido = pedidoRecebidoService.getPedido(id);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pedido);
    }
}
