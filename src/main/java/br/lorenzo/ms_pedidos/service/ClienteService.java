package br.lorenzo.ms_pedidos.service;


import br.lorenzo.ms_pedidos.dto.ClienteDTO;
import br.lorenzo.ms_pedidos.model.Cliente;
import br.lorenzo.ms_pedidos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return modelMapper.map(clienteSalvo, ClienteDTO.class);
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteDTO.class);
    }


    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        modelMapper.map(clienteDTO, clienteExistente);

        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        return modelMapper.map(clienteAtualizado, ClienteDTO.class);
    }

    @Transactional
    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

} // Fim da Classe