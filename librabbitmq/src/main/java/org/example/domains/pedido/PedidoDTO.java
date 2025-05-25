package org.example.domains.pedido;

import org.example.domains.produto.ProdutoDTO;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class PedidoDTO implements Serializable {

    private UUID id;
    private String emailCliente;
    private List<ProdutoDTO> produtos;
    private PedidoStatus status;

    public PedidoDTO() {}

    public PedidoDTO(UUID id, String emailCliente, List<ProdutoDTO> produtos, PedidoStatus status) {
        this.id = id;
        this.emailCliente = emailCliente;
        this.produtos = produtos;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmailCliente() {
        return emailCliente;
    }
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public PedidoStatus getStatus() {
        return status;
    }
    public void setStatus(PedidoStatus status) {
        this.status = status;
    }
}
