package com.manga.pokemonservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manga.pokemonService.model.Pokemon;
import com.manga.pokemonservice.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${pokemon.jms.destination}")
    private String jmsQueue;

    @Autowired
    private PokemonRepository pokemonRepository;

    @GetMapping(value = "/sendToPokedex/{id}")
    public ResponseEntity<Pokemon> sendToPokedex(@PathVariable int id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (!pokemon.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(pokemon.get());
            jmsTemplate.convertAndSend(jmsQueue, json);
            return new ResponseEntity<>(pokemon.get(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public Pokemon getPokemon(@PathVariable int id) {
        return pokemonRepository.getOne(id);
    }

    @GetMapping(value = "/all")
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }

    @PostMapping(value = "/addOne")
    public Pokemon save(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @PostMapping(value = "/addList")
    public List<Pokemon> save(@RequestBody List<Pokemon> listPokemon) {
        return pokemonRepository.saveAll(listPokemon);
    }
}
