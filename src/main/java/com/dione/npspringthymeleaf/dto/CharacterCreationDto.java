package com.dione.npspringthymeleaf.dto;

import com.dione.npspringthymeleaf.model.Charac;

import java.util.ArrayList;
import java.util.List;

public class CharacterCreationDto {
    public List<Charac> characters;


    public CharacterCreationDto(){
        this.characters = new ArrayList<>();
    }

    public CharacterCreationDto(List<Charac> characters) {
        this.characters = characters;
    }

    public List<Charac> getCharacList() {
        return characters;
    }

    public void setCharacList(List<Charac> characters) {
        this.characters = characters;
    }


    public void addCharac(Charac charac) {
        this.characters.add(charac);
    }

    @Override
    public String toString() {
        return "CharacterCreationDto{" +
                "characters=" + characters +
                '}';
    }
}
