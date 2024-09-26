package com.pets.shelter.dao;

import com.pets.shelter.model.Parent;
import com.pets.shelter.model.Pet;

import java.util.List;

public interface PetDAO {

    public List<Pet> getPets();

    public Pet getPet(int petId);

    public Pet savePet(Pet petToSave);

    public List<Parent> getParents();

    public Parent getParent(int parentId);

    public Parent addParent(Parent parent);

    public void link(int parentId, int petId);
}
