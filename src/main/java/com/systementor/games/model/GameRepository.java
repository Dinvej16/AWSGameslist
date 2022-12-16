package com.systementor.games.model;

import org.springframework.data.repository.CrudRepository;
public interface GameRepository extends CrudRepository<Game, Integer> {
    
}
