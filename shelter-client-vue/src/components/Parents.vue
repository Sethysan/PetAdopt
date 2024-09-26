<template>
    <div>
        <h1>Parents!</h1>

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

<style>
.parentCard {
    border-radius: 20px;
    padding: 5px;
    margin: 5px;
    border: 1px;
    width: 250px;
    background-color: rgb(53, 197, 233);
}
</style>