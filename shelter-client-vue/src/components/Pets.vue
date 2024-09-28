<template>
    <div class="pets-list">
        <div v-for="pet in petsList" v-bind:key="pet.id" class="petCard">
            <h2>Name: {{ pet.name }}</h2>
            <p>Id: {{ pet.id }}</p>
            <p>Weight: {{ pet.weight }} lbs</p>
            <p>Species: {{ pet.species }}</p>
            <p>Paper Trained: {{ pet.paperTrained }}</p>
            <p v-if="pet.parentName !== 'Needs Adopted'">Parent Name: {{ pet.parentName }}</p>
            <p v-else>{{ pet.parentName }}</p>
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
}

p {
    font-weight: bold;
}
</style>