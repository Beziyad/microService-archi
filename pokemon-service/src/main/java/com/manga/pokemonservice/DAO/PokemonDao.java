package com.manga.pokemonservice.DAO;

import com.manga.pokemonService.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon,Integer> {

}
