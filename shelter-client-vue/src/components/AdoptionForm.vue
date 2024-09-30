<template>
    <div class="container">
        <div class="adopt">
            <h1>Adoption Form!</h1>
            <form @submit.prevent="adopt">
                <label for="pet">Choose a Pet: </label>
                <select v-model="selectedPetId" id="pet" required>
                    <option disabled value="">Please select a pet</option>
                    <option v-for="pet in pets" v-bind:key="pet.id" v-bind:value="pet.id">
                        {{ pet.name }} (ID: {{ pet.id }})
                    </option>
                </select>

                <label for="parent">Choose a Parent: </label>
                <select v-model="selectedParentId" id="parent" required>
                    <option disabled value="">Please select a parent</option>
                    <option v-for="parent in parents" v-bind:key="parent.id" v-bind:value="parent.id">
                        {{ parent.name }} (ID: {{ parent.id }})
                    </option>
                </select>
                <div class="buttons">
                    <router-link to="/" class="btn">Return Home</router-link>
                    <button type="submit" class="btn">Adopt</button>
                </div>
            </form>
        </div>
        <img v-if="selectedPet && selectedPet.imageUrl" :src="selectedPet.imageUrl" alt="Pet Image" class="pet-image" />
    </div>
</template>

<script>
import shelterService from '../services/ShelterService.js';

export default {
    data() {
        return {
            pets: [],
            parents: [],
            selectedPetId: "",
            selectedParentId: "",
            selectedPet: null
        };

    },
    created() {
        shelterService.getAllPets().then(
            (response) => {
                this.pets = response.data;
                if (this.$route.query.petId) {
                    this.selectedPetId = this.$route.query.petId;
                }
            }
        );
        shelterService.getAllParents().then(
            (response) => {
                this.parents = response.data;
            }
        )
    },
    watch: {
        // Watch for changes to selectedPetId and fetch full details for the selected pet
        selectedPetId(newVal) {
            if (newVal) {
                shelterService.getPet(newVal).then(response => {
                    this.selectedPet = response.data;

                    // Ensure the image URL is absolute by prepending the base URL if it's relative
                    if (this.selectedPet.imageUrl && !this.selectedPet.imageUrl.startsWith('http')) {
                        this.selectedPet.imageUrl = `http://localhost:8080${this.selectedPet.imageUrl}`;
                    }
                }).catch(error => {
                    console.error('Error fetching pet details:', error);
                });
            }
        }
    },
    methods: {
        adopt() {
            if (this.selectedPetId && this.selectedParentId) {

                const adoptionData = {
                    petId: this.selectedPetId,
                    parentId: this.selectedParentId
                };
                shelterService.adopt(adoptionData).then(
                    (response) => {
                        alert("Adoption Successful!");
                        this.selectedParentId = "";
                        this.selectedPetId = "";
                        this.selectedPet = null;
                        this.$router.push({ name: "home" });
                    }
                ).catch(
                    (error) => {
                        alert("Adoption Failed");
                    }
                )
            } else {
                alert("Please select both a pet and a parent");
            }
        }
    }

};
</script>


<style scoped>
.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px;
}

.adopt {
    background-color: rgba(255, 255, 255, 0.658);
    border-radius: 20px;
    padding: 20px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    width: 100%;
    max-width: 500px;
    text-align: center;
    font-family: 'Arial', sans-serif;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.buttons {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    width: 100%;
    gap: 20px;
    margin-top: 20px;
}

button {
    all: unset;
    text-decoration-style: solid;
    text-decoration-thickness: auto;
}

.btn {
    display: inline-block;
    box-sizing: border-box;
    height: 30px;
    width: 40%;
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
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    /* Ensure shadow is applied */
}

.router-link:hover {
    background-color: #0056b3;
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
    /* Enhance shadow on hover */
}


/* Add media queries for responsiveness */
@media (max-width: 768px) {
    .adopt {
        max-width: 90%;
        /* For tablets and smaller devices */
    }
}

@media (max-width: 480px) {
    .adopt {
        max-width: 100%;
        padding: 10px;
        /* Less padding on smaller devices */
    }
}

.pet-image {
    margin-top: 20px;
    max-width: 100%;
    max-height: 450px;
    object-fit: contain;
    border-radius: 10px;
}

.buttons {
    display: flex;

}

label {
    display: block;
    margin-top: 10px;
}

select,
button {
    width: 70%;
    /* Make dropdowns full width */
    margin-bottom: 20px;
    padding: 10px;
    box-sizing: border-box;
    /* Ensure padding is included in width */
}

/* Ensure buttons are full width on smaller screens */
button {
    width: 50%;
    background-color: #007bff;
    color: white;
    border: none;
    margin-top: 20px;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #0056b3;
}
</style>
