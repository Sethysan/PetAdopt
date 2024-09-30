<template>
    <div class="container">
        <div class="add-parent">
            <h1>Add Parent!</h1>
            <div v-if="valid === false" class="error-message">Invalid data, please try again</div>
            <form v-on:submit.prevent="addParent">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" v-model="newParent.name" id="name" required />
                </div>
                <div class="buttons">
                    <button type="submit" class="btn btn-save">Save!</button>
                    <router-link to="/" class="btn btn-return">Return Home</router-link>
                </div>
            </form>
        </div>
    </div>
</template>


<script>
import shelterService from "../services/ShelterService.js";
export default {
    data() {
        return {
            valid: true,
            newParent: {},
        };
    },
    methods: {
        addParent() {
            shelterService.addParent(this.newParent)
                .then((response) => {
                    if (response.status === 201) {
                        alert("Parent Added!");
                        this.newParent = {};
                        this.$router.push({ name: "home" });
                    }
                })
                .catch((error) => {
                    if (error.response) {
                        this.valid = false;
                    }
                });
        },
    },
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

.add-parent {
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
}

h1 {
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 20px;
    text-align: left;
}

label {
    display: block;
    margin-bottom: 5px;
}

input[type="text"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
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

.error-message {
    color: red;
    margin-bottom: 15px;
}

.btn {
    display: inline-block;
    box-sizing: border-box;
    height: 30px;
    width: 40%;
    padding: 10px 20px;
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

.btn-save {
    background-color: #28a745;
}

.btn-save:hover {
    background-color: #218838;
}

.btn-return {
    background-color: #007bff;
}

.btn-return:hover {
    background-color: #0056b3;
}


@media (max-width: 768px) {
    .add-parent {
        max-width: 90%;
    }
}

@media (max-width: 480px) {
    .add-parent {
        max-width: 100%;
        padding: 10px;
    }

    .buttons {
        flex-direction: column;
        
    }

    button,
    .router-link {
        width: 100%;
        
    }
}
</style>