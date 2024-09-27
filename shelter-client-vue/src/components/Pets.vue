<template>
    <div>
        <h1>Pets!</h1>

        <div v-for="pet in petsList" v-bind:key="pet.id" class="petCard">
            <h2>Name: {{ pet.name }}</h2>
            <p>Id: {{ pet.id }}</p>
            <p>Weight: {{ pet.weight }}lbs</p>
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
}
</script>

<style>
.petCard {
    border-radius: 20px;
    padding: 5px;
    margin: 5px;
    border: 1px;
    width: 250px;
    background-color: rgb(100, 100, 177);
}
</style>