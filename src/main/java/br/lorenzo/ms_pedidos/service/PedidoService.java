package br.lorenzo.ms_pedidos.service;

import br.lorenzo.ms_pedidos.dto.PedidoDTO;
import br.lorenzo.ms_pedidos.dto.PedidoResponseDTO;
import br.lorenzo.ms_pedidos.model.Cliente;
import br.lorenzo.ms_pedidos.model.Pedido;
import br.lorenzo.ms_pedidos.model.enums.StatusPedido;
import br.lorenzo.ms_pedidos.repository.ClienteRepository;
import br.lorenzo.ms_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    // CREATE
    @Transactional
    public PedidoResponseDTO criarPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setStatus(pedidoDTO.getStatus() != null ?
                pedidoDTO.getStatus() : StatusPedido.PENDENTE);
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return toResponseDTO(pedidoSalvo);
    }

    public List<PedidoResponseDTO> listarPedidosPorCliente(Long clienteId) {
        clienteService.buscarPorId(clienteId);
        return pedidoRepository.buscarPorClienteId(clienteId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return toResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);
        return toResponseDTO(atualizado);
    }

    @Transactional
    public void excluirPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    private PedidoResponseDTO toResponseDTO(Pedido pedido) {
        PedidoResponseDTO dto = modelMapper.map(pedido, PedidoResponseDTO.class);
        dto.setClienteNome(pedido.getCliente().getNome());
        return dto;
    }

} // Fim da Classe