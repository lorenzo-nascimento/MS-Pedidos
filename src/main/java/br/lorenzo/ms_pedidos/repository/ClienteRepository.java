package br.lorenzo.ms_pedidos.repository;

import br.lorenzo.ms_pedidos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
        boolean existsByEmail(String email);
}
