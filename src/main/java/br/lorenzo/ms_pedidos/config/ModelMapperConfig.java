package br.lorenzo.ms_pedidos.config;

import br.lorenzo.ms_pedidos.dto.ClienteDTO;
import br.lorenzo.ms_pedidos.model.Cliente;
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
                .setSkipNullEnabled(true);
        modelMapper.createTypeMap(Cliente.class, ClienteDTO.class)
                .addMapping(Cliente::getId, ClienteDTO::setId);
        return modelMapper;
    }
}