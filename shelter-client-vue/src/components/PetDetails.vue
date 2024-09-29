<template>
  <div class="details-container">
    <div class="pet-card">
      <h1>Pet Details</h1>
      <div v-if="pet && pet.imageUrl">
        <img :src="pet.imageUrl" alt="Pet Image" class="pet-image" />
        <div class="pet-info">
          <h3>Name: {{ pet.name }}</h3>
          <p>Age: {{ pet.age }}</p>
          <p>Breed: {{ pet.breed }}</p>
          <p>Description: {{ pet.description }}</p>
        </div>
      </div>
      <div v-else>
        <p>Loading pet details...</p>
      </div>
    </div>
  </div>
</template>

<script>
import ShelterService from '../services/ShelterService.js';

export default {
  data() {
    return {
      pet: null,  // Initialize pet as null
    };
  },
  created() {
    const petId = this.$route.params.id;
    this.getPetDetails(petId);  // Fetch pet details when the component is created
  },
  methods: {
    getPetDetails(petId) {
      ShelterService.getPet(petId)
        .then(response => {
          this.pet = response.data;  // Set the response data (including imageUrl)
          // Construct the full image URL by prepending localhost:8080 if imageUrl is relative
          this.pet.imageUrl = `http://localhost:8080${this.pet.imageUrl}`;
        })
        .catch(error => {
          console.error('Error fetching pet details:', error);
        });
    }
  }
};
</script>

<style>
/* Center the card on the page */
.details-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;  /* Full viewport height */
  
}

/* Style the pet card */
.pet-card {
  background-color: white;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  text-align: center;
  font-family: 'Arial', sans-serif;
}

/* Image styling */
.pet-image {
  width: 100%;
  height: auto;
  border-radius: 10px;
  margin-bottom: 15px;
}

/* Information styling */
.pet-info h3 {
  margin-top: 10px;
  font-size: 1.5rem;
}

.pet-info p {
  margin: 5px 0;
  font-size: 1rem;
  color: #333;
}

</style>
