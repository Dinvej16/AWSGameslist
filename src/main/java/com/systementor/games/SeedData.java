package com.systementor.games;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.systementor.games.model.Genre;
import com.systementor.games.model.GenreRepository;
import com.systementor.games.model.Game;
import com.systementor.games.model.GameRepository;

@Component
public class SeedData implements CommandLineRunner {
    
    private GameRepository gameRepository;
    private GenreRepository genreRepository;

    @Autowired
    public SeedData(GameRepository gameRepository, GenreRepository genreRepository) {
        super();
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        seedGenres();
        seedGames();
    }

    private void seedGames() {
        var allGenres = new ArrayList<Genre>();
        for(Genre r : genreRepository.findAll())
            allGenres.add(r);

        var allGames = new ArrayList<Game>();
        for(Game r : gameRepository.findAll())
            allGames.add(r);
    
        addGameIsNotExisting("Call of duty modern warfare 2 ",2022, "Action", allGames, allGenres );
        addGameIsNotExisting("Need for speed Heat",2019, "Racing", allGames, allGenres );

    }

    private void addGameIsNotExisting(String title, int year, String genreName, ArrayList<Game> allGames,
                                      ArrayList<Genre> allGenres) {

            for(Game acc: allGames){
                if(acc.getTitle().equals(title)) return;
            }

            var game = new Game();
            game.setTitle(title);
            game.setGenre(GetGenreId(genreName,allGenres));
            game.setYear(year);
            gameRepository.save(game);
    }

    private Integer GetGenreId(String genreName, ArrayList<Genre> allGenres) {
        for(Genre acc: allGenres){
            if(acc.getName().equals(genreName)) return acc.getId();
        }
        return null;
    }

    private void seedGenres() {
        var all = new ArrayList<Genre>();
        for(Genre r : genreRepository.findAll())
            all.add(r);
        addGenreIsNotExisting(all,"Action");
        addGenreIsNotExisting(all,"Racing");
    }


    private void addGenreIsNotExisting(ArrayList<Genre> all, String name) {
        for(Genre acc: all){
            if(acc.getName().equals(name)) return;
        }
        var n = new Genre();
        n.setName(name);
        genreRepository.save(n);
    }

}
