package com.systementor.games.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.systementor.games.model.Genre;
import com.systementor.games.model.GenreRepository;
import com.systementor.games.model.Game;
import com.systementor.games.model.GameRepository;
import com.systementor.viewmodel.HomeGame;

import org.springframework. ui.Model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    private GameRepository gameRepository;
    private GenreRepository genreRepository;


    @Autowired
    public HomeController(GameRepository gameRepository, GenreRepository genreRepository) {
        super();
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }


    @GetMapping(path="/")
    String empty(Model model)
    {
        var gameViewModels = new ArrayList<HomeGame>();
        var allGenres = new ArrayList<Genre>();
        for(Genre r : genreRepository.findAll())
            allGenres.add(r);

        for(Game game : gameRepository.findAll()){
            gameViewModels.add(new HomeGame(game.getTitle(),game.getYear(), getGenreName(game.getGenre(), allGenres)));
        }
        model.addAttribute("Games", gameViewModels);
        return "home";
    }


    private String getGenreName(Integer genre, ArrayList<Genre> allGenres) {
        for(Genre acc: allGenres){
            if(acc.getId().equals(genre)) return acc.getName();
        }
        return null;
    }
    
}
