<template>
    <div>
        <h1>Add Pet!</h1>
        <div v-if="valid === false">{{ error.response }}</div>
        <form v-on:submit.prevent="addPet">
            Name: <input type="text" v-model="newPet.name" /> <br>
            Weight: <input type="text" v-model="newPet.weight" /> <br>
            Species: <input type="text" v-model="newPet.species" /> <br>
            Paper Trained: <input type="checkbox" v-model="newPet.paperTrained" /> <br>

            <button>Save!</button>

        </form>
    </div>
</template>

<script>
import shelterService from '../services/ShelterService.js';


export default {
    data() {
        return {
            valid: true,
            newPet: {}
        }
    },
    methods: {
        addPet() {
            console.log("here I am !")
            shelterService.addPet(this.newPet).then(
                (response) => {
                    if (response.status === 201) {
                        alert("Pet Added!");
                        this.newPet = {};
                        this.$router.push({ name: "home" });
                    }
                }
            ).catch(
                (error) => {
                    if (error.response) {
                        this.valid = false;
                    }
                }
            );
        }
    }

}
</script>

<style></style>