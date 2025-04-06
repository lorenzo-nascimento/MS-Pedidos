package br.lorenzo.ms_pedidos.config;

import br.lorenzo.ms_pedidos.dto.PedidoResponseDTO;
import br.lorenzo.ms_pedidos.model.Pedido;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true); // Ignora conflitos de mapeamento

        return modelMapper;
    }
}