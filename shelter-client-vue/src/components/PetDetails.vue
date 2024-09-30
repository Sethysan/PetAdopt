<template>
  <div class="details-container">
    <div class="pet-card">
      <h1>Pet Details</h1>
      <div v-if="pet && pet.imageUrl">
        <img :src="pet.imageUrl" alt="Pet Image" class="pet-image" />

        <div class="pet-info">
          <div>
            <label>Name:</label>
            <span v-if="!isEditing">{{ pet.name }}</span>
            <input v-else v-model="editablePet.name" type="text" />
          </div>

          <div>
            <label>Weight:</label>
            <span v-if="!isEditing">{{ pet.weight }} lbs</span>
            <input v-else v-model="editablePet.weight" type="number" />
          </div>

          <div>
            <label>Species:</label>
            <span v-if="!isEditing">{{ pet.species }}</span>
            <input v-else v-model="editablePet.species" type="text" />
          </div>

          <div>
            <label>Paper Trained:</label>
            <span v-if="!isEditing">{{ pet.paperTrained ? 'Yes' : 'No' }}</span>
            <select v-else v-model="editablePet.paperTrained">
              <option :value="true">Yes</option>
              <option :value="false">No</option>
            </select>
          </div>

          <div>
            <label>Parent Name:</label>
            <span v-if="!isEditing">{{ pet.parentName }}</span>
            <input v-else v-model="editablePet.parentName" type="text" />
          </div>

          <div>
            <label>Age:</label>
            <span v-if="!isEditing">{{ pet.age }}</span>
            <input v-else v-model="editablePet.age" type="number" />
          </div>

          <div>
            <label>Breed:</label>
            <span v-if="!isEditing">{{ pet.breed }}</span>
            <input v-else v-model="editablePet.breed" type="text" />
          </div>

          <div>
            <label>Description:</label>
            <span v-if="!isEditing">{{ pet.description }}</span>
            <textarea v-else v-model="editablePet.description"></textarea>
          </div>
        </div>
      </div>

      <div v-else>
        <p>Loading pet details...</p>
      </div>

      <div class="buttons">
        <button @click="toggleEditMode" class="btn">
          {{ isEditing ? 'Cancel' : 'Edit Details' }}
        </button><br>
        <div v-if="isAdoptable">
          <router-link :to="{ path: '/adopt', query: { petId: pet.id } }" class="btn">Adopt Me!</router-link><br>
        </div>
        <router-link to="/" class="btn">Return Home</router-link>
        <br>
        <button v-if="isEditing" @click="saveChanges" class="btn btn-save">Save</button>
      </div>
    </div>
  </div>
</template>

<script>
import ShelterService from '../services/ShelterService.js';

export default {
  data() {
    return {
      pet: null,         // Fetched pet data
      editablePet: {},   // Editable copy of the pet data
      isEditing: false,  // Flag for edit mode
      isAdoptable: false,
    };
  },
  created() {
    const petId = this.$route.params.id;
    this.getPetDetails(petId);  // Fetch pet details when the component is created
  },
  methods: {
    // Fetch the pet details from the backend
    getPetDetails(petId) {
      ShelterService.getPet(petId)
        .then(response => {
          this.pet = response.data;  // Set the pet data
          console.log(this.pet);
          if (this.pet.parent === 1) {
            this.isAdoptable = true;  // Set adoptable to true
          } else {
            this.isAdoptable = false; // Otherwise, set to false
          }
          if (!this.pet.imageUrl.startsWith('http://localhost:8080')) {
            this.pet.imageUrl = `http://localhost:8080${this.pet.imageUrl}`;
          }
          this.editablePet = { ...this.pet };  // Create a copy of the pet data for editing
        })
        .catch(error => {
          console.error('Error fetching pet details:', error);
        });
    },

    // Toggle between edit mode and view mode
    toggleEditMode() {
      this.isEditing = !this.isEditing;
      if (!this.isEditing) {
        // Reset the editable fields if edit mode is cancelled
        this.editablePet = { ...this.pet };
      }
    },

    // Save the edited data
    saveChanges() {
      const updatedPet = {
        ...this.editablePet,
        imageUrl: this.editablePet.imageUrl.replace('http://localhost:8080', '') // Remove base URL
      };
      ShelterService.updatePet(this.pet.id, updatedPet)
        .then(response => {
          this.pet = response.data;  // Update the pet data with the new changes
          if (!this.pet.imageUrl.startsWith('http://localhost:8080')) {
            this.pet.imageUrl = `http://localhost:8080${this.pet.imageUrl}`;
          }
          this.isEditing = false;  // Exit edit mode
          alert('Pet details updated successfully');

        })
        .catch(error => {
          console.error('Error saving pet details:', error);
          alert('Failed to update pet details');
        });
    }
  }
};
</script>

<style scoped>
.details-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.pet-card {
  background-color: white;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  max-width: 30%;
  width: 100%;
  text-align: center;
  font-family: 'Arial', sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow: hidden;
}

.pet-image {
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 10px;
  margin-bottom: 15px;
}

.pet-info {
  overflow-y: auto;
  margin-bottom: 20px;
  padding-bottom: 10px;
}

.buttons {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

button {
  all: unset;
  text-decoration-style: solid;
  text-decoration-thickness: auto;
}

.btn {
  display: block;
  box-sizing: border-box;
  height: 30px;
  margin-left: 5px;
  margin-right: 5px;
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  padding-block: 1px;
  padding-inline: 6px;
  border-style: outset;
  border-color: rgb(108, 105, 105);
  border-image: initial;

}

.btn:hover {
  background-color: #0056b3;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
  /* Enhance shadow on hover */
}

.btn-save {
  background-color: #28a745;
}

.btn-save:hover {
  background-color: #218838;
}

.router-link {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border-radius: 5px;
  text-decoration: none;
  display: inline-block;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  /* Ensure shadow is applied */
}

.router-link:hover {
  background-color: #0056b3;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
  /* Enhance shadow on hover */
}

.pet-info label {
  font-weight: bold;
  display: block;
  margin-top: 10px;
}

.pet-info input,
.pet-info textarea,
.pet-info select {
  width: 100%;
  padding: 5px;
  margin-top: 5px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 1rem;
}

.pet-info span {
  display: inline-block;
  margin-top: 5px;
  font-size: 1.1rem;
}
</style>
