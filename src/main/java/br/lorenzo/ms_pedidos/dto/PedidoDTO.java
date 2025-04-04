package br.lorenzo.ms_pedidos.dto;

import br.lorenzo.ms_pedidos.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private StatusPedido status;
    private Long clienteId;

}
