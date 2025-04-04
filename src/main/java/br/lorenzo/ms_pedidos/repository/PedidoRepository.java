package br.lorenzo.ms_pedidos.repository;

import br.lorenzo.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
