package br.lorenzo.ms_pedidos.dto;

import br.lorenzo.ms_pedidos.model.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private StatusPedido status;

    @NotNull(message = "ID é obrigatório")
    private Long clienteId;

}
