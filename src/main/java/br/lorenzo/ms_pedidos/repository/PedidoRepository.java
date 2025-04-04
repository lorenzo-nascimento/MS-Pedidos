package br.lorenzo.ms_pedidos.repository;

import br.lorenzo.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId")
    List<Pedido> buscarPorClienteId(@Param("clienteId") Long clienteId);

}
