package com.pets.shelter.model;

import javax.validation.constraints.Max;

public class Pet {

    private int id;
    private String name;

    @Max(value=150, message="Uh oh big doggo alert" )
    private int weight;
    private String species;
    private boolean paperTrained;
    private int parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isPaperTrained() {
        return paperTrained;
    }

    public void setPaperTrained(boolean paperTrained) {
        this.paperTrained = paperTrained;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
