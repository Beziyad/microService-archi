package com.manga.pokemonservice.repository;

import com.manga.pokemonService.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {

}
