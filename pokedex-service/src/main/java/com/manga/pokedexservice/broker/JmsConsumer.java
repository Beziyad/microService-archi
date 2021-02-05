package com.manga.pokedexservice.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manga.pokedexservice.Dao.PokemonDao;
import com.manga.pokedexservice.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class JmsConsumer {
    @Autowired
    private PokemonDao pokemonDao;

    @JmsListener(destination="${pokemon.jms.destination}")
    public void consumeMessage(String data){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Pokemon pokemon = mapper.readValue(data, Pokemon.class);
            pokemonDao.save(pokemon);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
