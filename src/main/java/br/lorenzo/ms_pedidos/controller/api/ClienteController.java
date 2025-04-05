package br.lorenzo.ms_pedidos.controller.api;

import br.lorenzo.ms_pedidos.dto.ClienteDTO;
import br.lorenzo.ms_pedidos.dto.PedidoResponseDTO;
import br.lorenzo.ms_pedidos.service.ClienteService;
import br.lorenzo.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO novoCliente = clienteService.criarCliente(clienteDTO);
        return ResponseEntity
                .created(URI.create("/api/clientes/" + novoCliente.getId()))
                .body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorCliente(id));
    }

} // Fim da classe
