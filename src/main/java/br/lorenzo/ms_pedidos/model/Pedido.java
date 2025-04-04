package br.lorenzo.ms_pedidos.model;

import br.lorenzo.ms_pedidos.model.enums.StatusPedido;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


public class Pedido {

    private Long id;
    private StatusPedido status;
    private Cliente cliente;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }

}
