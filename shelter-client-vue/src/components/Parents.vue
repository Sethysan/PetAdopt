<template>
    <div class="parents-list">
        <div v-for="parent in parentsList" v-bind:key="parent.id" class="parentCard">
            <h2>Name: {{ parent.name }}</h2>
            <p>Id: {{ parent.id }}</p>
        </div>
    </div>
</template>

<script>
import shelterService from "../services/ShelterService.js";

export default {
    data() {
        return {
            parentsList: []
        };
    },
    created() {
        shelterService.getAllParents().then((response) => {
            this.parentsList = response.data;
        });
    }
};
</script>

<style scoped>
.parents-list {
    display: flex; 
    flex-wrap: wrap; 
    justify-content: space-between; 
    gap: 20px; 
}
.parentCard {
    border-radius: 20px;
    text-align: center;
    padding: 5px;
    margin: 5px;
    border: 1px solid #000;
    width: 100%; 
    max-width: 250px; 
    background-color: rgba(53, 197, 233, 0.8);
}
p{
    font-weight: bold;
}
</style>