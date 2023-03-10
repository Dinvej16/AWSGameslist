package com.systementor.games.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
    private String title;
    private int year;
    private int genreId;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    

    public Integer getGenre() {
        return genreId;
      }
    
      public void setGenre(Integer id) {
        this.genreId = id;
      }


    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }

      public Integer getYear() {
        return year;
      }
    
      public void setYear(Integer year) {
        this.year = year;
      }


      public void setTitle(String v)
      {
          title = v;
      }
  

      public String getTitle()
      {
          return title;
      }
      
}
