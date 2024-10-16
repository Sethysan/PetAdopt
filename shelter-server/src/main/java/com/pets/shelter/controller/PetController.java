package com.pets.shelter.controller;


import com.pets.shelter.dao.PetDAO;
import com.pets.shelter.model.AdoptionDTO;
import com.pets.shelter.model.Parent;
import com.pets.shelter.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class PetController {

    @Autowired
    private PetDAO petDAO;

    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public List<Pet> allPets() {
        try {
            return petDAO.getPets();
        } catch (Exception e) {

            System.err.println("Error fetching pets: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve pets", e);
        }
    }

    @RequestMapping(path = "/pet/{id}", method = RequestMethod.GET)
    public Pet getPet(@PathVariable int id) {

        return petDAO.getPet(id);
    }
    @RequestMapping(path = "/pet/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet pet) {
        petDAO.updatePet(id, pet);
        return ResponseEntity.ok(pet);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/add-pet", method = RequestMethod.POST)
    public Pet addPet(@RequestBody @Valid Pet pet) {
        return petDAO.savePet(pet);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/add-parent", method = RequestMethod.POST)
    public Parent addParent(@RequestBody Parent parent) {
        return petDAO.addParent(parent);
    }


    @RequestMapping(path = "/parents", method = RequestMethod.GET)
    public List<Parent> getParents() {
        return petDAO.getParents();
    }

    //TODO: Endpoint to retrieve one parent
    @RequestMapping(path = "/parent/{id}", method = RequestMethod.GET)
    public Parent getParent(@PathVariable int id) {
        return petDAO.getParent(id);
    }


    @RequestMapping(path = "/adopt", method = RequestMethod.PUT)
    public void adopt(@RequestBody AdoptionDTO adoptionDTO) {
        petDAO.link(adoptionDTO.getParentId(), adoptionDTO.getPetId());
    }
}
