package com.pets.shelter.dao;

import com.pets.shelter.model.Parent;
import com.pets.shelter.model.Pet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PetJdbcDAO implements PetDAO{

    private JdbcTemplate template;

    public PetJdbcDAO(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    private Pet mapRowToPet(SqlRowSet rowSet) {
        Pet pet = new Pet();

        pet.setId(rowSet.getInt("id"));
        pet.setName(rowSet.getString("name"));
        pet.setWeight(rowSet.getInt("weight"));
        pet.setSpecies(rowSet.getString("species"));
        pet.setPaperTrained(rowSet.getBoolean("paper_trained"));
        pet.setParent(rowSet.getInt("parent_id"));

        return pet;
    }

    @Override
    public List<Pet> getPets() {

        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);

            while (results.next()) {
                Pet pet = mapRowToPet(results);
                pets.add(pet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return pets;
    }

    @Override
    public Pet getPet(int petId) {

        Pet pet = null;

        String sql = "SELECT * FROM pet WHERE id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, petId);

            if(results.next()) {

                pet = mapRowToPet(results);

            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return pet;
    }

    @Override
    public Pet savePet(Pet petToSave) {

        String sql = "INSERT INTO pet (name,weight,species,paper_trained,parent_id) VALUES (?,?,?,?,?) RETURNING id";

        int newPetId = -1;
        try {
            newPetId = template.queryForObject(sql, Integer.class,
                    petToSave.getName(),
                    petToSave.getWeight(),
                    petToSave.getSpecies(),
                    petToSave.isPaperTrained(),
                    1
            );
        } catch(CannotGetJdbcConnectionException e) {

        } catch(DataIntegrityViolationException e) {

        }

        return getPet(newPetId);
    }


    private Parent mapRowToParent(SqlRowSet results) {

        Parent parent = new Parent();
        parent.setId( results.getInt("id") );
        parent.setName( results.getString("name") );

        return parent;
    }


    @Override
    public List<Parent> getParents() {

        List<Parent> parents = new ArrayList<>();
        String sql = "SELECT * from parent WHERE id <> ?;";

        int homePlaceholder = 1;

        SqlRowSet results = template.queryForRowSet(sql, homePlaceholder);

        while(results.next()) {
            Parent parent = mapRowToParent(results);
            parents.add(parent);
        }


        return parents;
    }

    @Override
    public Parent getParent(int parentId) {

        if(parentId == 1) {
            return null;
        }

        String sql = "SELECT * FROM parent where id = ?";

        SqlRowSet results = template.queryForRowSet(sql, parentId);

        Parent parent = null;
        if(results.next()) {
            parent = mapRowToParent(results);
        }

        return parent;
    }

    @Override
    public Parent addParent(Parent parent) {

        String sql = "INSERT INTO parent (name) VALUES(?) RETURNING id;";

        int newId = -1;

        newId = template.queryForObject(sql, int.class, parent.getName() );

        return getParent(newId);
    }

    @Override
    public void link(int parentId, int petId) {

        String sql = "UPDATE pet SET parent_id = ? WHERE id = ?";

        template.update(sql, parentId, petId);
    }
}
