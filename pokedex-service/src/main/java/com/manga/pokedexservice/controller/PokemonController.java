package com.manga.pokedexservice.controller;

import com.manga.pokedexservice.dao.PokemonDao;
import com.manga.pokedexservice.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pokedex")
public class PokemonController {
    @Autowired
    public PokemonDao pokemonDao;

    @GetMapping(value = "/getPokemons")
    public List<Pokemon> getPokemons() {
        return pokemonDao.findAll();
    }
}
