<template>
    <div class="pet-form">
        <h1>Add Pet!</h1>
        <div v-if="valid === false">Invalid data, please try again</div>
        <form v-on:submit.prevent="addPet">
            <div class="form-input-group">
                <label for="username">Name </label>
                <input type="text" v-model="newPet.name" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="username">Weight </label>
                <input type="text" v-model="newPet.weight" required />
            </div>
            <div class="form-input-group">
                <label for="username">Species </label>
                <input type="text" v-model="newPet.species" />
            </div>
            <div class="form-input-group">
                <label for="username">Paper Trained? </label>
                <input type="checkbox" v-model="newPet.paperTrained" />
            </div>
            <button type="save">Save!</button>
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

<style scoped>
.pet-form {
    width: 30%;
    padding-bottom: 3%;
    padding-top: 1%;
    font-weight: bold;
    color: rgb(61, 59, 59);
    background-color: rgb(82, 191, 191);
    margin-bottom: 75rem;
    margin-top: 5rem;
    margin-left: 35%;
    border: solid 3px;
    border-color: rgb(0, 0, 0);
    border-radius: 15px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.338);
}

h1 {
    margin-left: 32%;
    color: black;
}

.form-input-group {
    display: flex;
    flex-direction: column;
    margin-left: 20%;
}

button[type="save"] {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 70%;
    max-width: 100%;
    padding: 0.75rem;
    box-sizing: border-box;
    margin-top: 1rem;
    margin-left: 13.5%;
    cursor: pointer;
    font-size: 1rem;
}
input[type="checkbox"]:hover,
button[type="save"]:hover {
    background-color: rgb(207, 207, 204);
    cursor: pointer;
}

input[type="text"],
input[type="password"],
input[type="checkbox"] {
    width: 70%;
    max-width: 100%;
    max-height: 1rem;
    padding: 0.75rem;
    box-sizing: border-box;
    cursor: text;
}
</style>