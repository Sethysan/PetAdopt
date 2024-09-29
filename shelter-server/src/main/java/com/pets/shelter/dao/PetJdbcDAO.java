package com.pets.shelter.dao;

import com.pets.shelter.model.Parent;
import com.pets.shelter.model.Pet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PetJdbcDAO implements PetDAO {

    private JdbcTemplate template;

    public PetJdbcDAO(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    private Pet mapRowToPetWithParentName(SqlRowSet rowSet) {
        Pet pet = new Pet();

        pet.setId(rowSet.getInt("id"));
        pet.setName(rowSet.getString("pet_name"));
        pet.setWeight(rowSet.getInt("weight"));
        pet.setSpecies(rowSet.getString("species"));
        pet.setPaperTrained(rowSet.getBoolean("paper_trained"));

        // Mapping the parent ID and parent name
        pet.setParent(rowSet.getInt("parent_id"));
        pet.setParentName(rowSet.getString("parent_name"));

        return pet;
    }

    private Pet mapRowToNewPet(SqlRowSet rowSet) {
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
        String sql = "SELECT pet.id, pet.name AS pet_name, pet.weight, pet.species, pet.paper_trained, pet.parent_id, " +
                "CASE WHEN pet.parent_id = 1 THEN 'Needs Adopted' ELSE parent.name END AS parent_name " +
                "FROM pet LEFT JOIN parent ON pet.parent_id = parent.id";


        try {
            SqlRowSet results = template.queryForRowSet(sql);

            while (results.next()) {
                pets.add(mapRowToPetWithParentName(results));
                ;
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting" + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error fetching pets: " + e.getMessage());
            e.printStackTrace();
        }
        return pets;
    }

    @Override
    public Pet getPet(int petId) {
        String sql = "SELECT p.id AS pet_id, p.name, p.weight, p.species, p.paper_trained, p.parent_id, p.pet_details_id, " +
                "pd.id AS details_id, pd.age, pd.breed, pd.description, pd.image_url " +
                "FROM pet p " +
                "JOIN pet_details pd ON p.pet_details_id = pd.id " +
                "WHERE p.id = ?";

        return template.queryForObject(sql, new Object[]{petId}, (rs, rowNum) -> {
            Pet pet = new Pet();
            pet.setId(rs.getInt("pet_id"));
            pet.setName(rs.getString("name"));
            pet.setWeight(rs.getInt("weight"));
            pet.setSpecies(rs.getString("species"));
            pet.setPaperTrained(rs.getBoolean("paper_trained"));
            pet.setParent(rs.getInt("parent_id"));
            pet.setPetDetailsId(rs.getInt("pet_details_id"));  // Set the foreign key to the pet_details table
            pet.setAge(rs.getInt("age"));
            pet.setBreed(rs.getString("breed"));
            pet.setDescription(rs.getString("description"));
            pet.setImageUrl(rs.getString("image_url"));
            return pet;
        });
    }
    public void updatePet(int petId, Pet pet) {
        // Update the pet table
        String updatePetSql = "UPDATE pet SET name = ?, weight = ?, species = ?, paper_trained = ? WHERE id = ?";

        // Update the pet_details table
        String updatePetDetailsSql = "UPDATE pet_details SET age = ?, breed = ?, description = ?, image_url = ? WHERE id = ?";

        // Update the pet table using the provided petId
        template.update(updatePetSql, pet.getName(), pet.getWeight(), pet.getSpecies(), pet.isPaperTrained(), petId);

        // Update the pet_details table using the petDetailsId, which corresponds to pet_details.id
        template.update(updatePetDetailsSql, pet.getAge(), pet.getBreed(), pet.getDescription(), pet.getImageUrl(), pet.getPetDetailsId());
    }

    @Override
    public Pet savePet(Pet petToSave) {

        String sql = "INSERT INTO pet (name, weight, species, paper_trained, parent_id) VALUES (?,?,?,?,?) RETURNING id";

        int newPetId = -1;
        try {
            newPetId = template.queryForObject(sql, Integer.class,
                    petToSave.getName(),
                    petToSave.getWeight(),
                    petToSave.getSpecies(),
                    petToSave.isPaperTrained(),
                    petToSave.getParent() != 0 ? petToSave.getParent() : 1 // Default parent ID to 1 for 'Needs Adopted'
            );
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting: " + e.getMessage());

        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems: " + e.getMessage());
        }

        return getPet(newPetId);
    }


    private Parent mapRowToParent(SqlRowSet results) {

        Parent parent = new Parent();
        parent.setId(results.getInt("id"));
        parent.setName(results.getString("name"));

        return parent;
    }


    @Override
    public List<Parent> getParents() {

        List<Parent> parents = new ArrayList<>();
        String sql = "SELECT * from parent WHERE id <> ?;";

        int homePlaceholder = 1;

        SqlRowSet results = template.queryForRowSet(sql, homePlaceholder);

        while (results.next()) {
            Parent parent = mapRowToParent(results);
            parents.add(parent);
        }


        return parents;
    }

    @Override
    public Parent getParent(int parentId) {

        if (parentId == 1) {
            return null;
        }

        String sql = "SELECT * FROM parent where id = ?";

        SqlRowSet results = template.queryForRowSet(sql, parentId);

        Parent parent = null;
        if (results.next()) {
            parent = mapRowToParent(results);
        }

        return parent;
    }

    @Override
    public Parent addParent(Parent parent) {

        String sql = "INSERT INTO parent (name) VALUES(?) RETURNING id;";

        int newId = -1;

        newId = template.queryForObject(sql, int.class, parent.getName());

        return getParent(newId);
    }

    @Override
    public void link(int parentId, int petId) {

        String sql = "UPDATE pet SET parent_id = ? WHERE id = ?";

        template.update(sql, parentId, petId);
    }
}
