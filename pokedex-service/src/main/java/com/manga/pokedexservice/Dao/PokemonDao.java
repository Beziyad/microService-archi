package com.manga.pokedexservice.Dao;

import com.manga.pokedexservice.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon, Integer> {

}
