<template>
    <div class="pets-list">
        <div v-for="pet in petsList" v-bind:key="pet.id" class="petCard">
            <router-link :to="{ name: 'petDetails', params: { id: pet.id } }" class="pet-link">
                <h2>Name: {{ pet.name }}</h2>
                <p>Id: {{ pet.id }}</p>
                <p>Weight: {{ pet.weight }} lbs</p>
                <p>Species: {{ pet.species }}</p>
                <p>Paper Trained: {{ pet.paperTrained }}</p>
                <p v-if="pet.parentName !== 'Needs Adopted'">Parent Name: {{ pet.parentName }}</p>
                <p v-else>{{ pet.parentName }}</p>
                <div class="view-details">View Pet Details</div>
            </router-link>
        </div>
    </div>
</template>

<script>
import shelterService from "../services/ShelterService.js";

export default {
    data() {
        return {
            petsList: []
        };
    },
    created() {
        shelterService.getAllPets().then((response) => {
            this.petsList = response.data;
        });
    }
};
</script>

<style scoped>

.pets-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    gap: 20px;
}

.petCard {
    border-radius: 20px;
    text-align: center;
    padding: 5px;
    margin: 5px;
    border: 1px solid #000;
    width: 100%;
    max-width: 250px;
    background-color: rgba(100, 100, 177, 0.8);
    transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
    position: relative;
    overflow: hidden;
}

.petCard:hover {
    transform: scale(1.05);
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

.pet-link {
    text-decoration: none;
    color: inherit;
    display: block;
    position: relative;
}

.view-details {
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
    background-color: rgba(0, 0, 0, 0.7);
    color: white;
    padding: 5px 10px;
    border-radius: 5px;
    opacity: 0;
    transition: opacity 0.2s ease-in-out;
}

.petCard:hover .view-details {
    opacity: 1;
}

p {
    font-weight: bold;
}
</style>