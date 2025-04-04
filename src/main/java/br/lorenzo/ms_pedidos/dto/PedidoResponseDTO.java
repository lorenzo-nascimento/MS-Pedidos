package br.lorenzo.ms_pedidos.dto;

import br.lorenzo.ms_pedidos.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {
    private Long id;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private Long clienteId;
    private String clienteNome;

}
