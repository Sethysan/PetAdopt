<template>
    <div>
        {{ newParent }}
        <h1>Add Parent!</h1>
        <div v-if="valid === false">Invalid data, please try again</div>

        <form v-on:submit.prevent="addParent">
            Name: <input type="text" v-model="newParent.name" />
            <button>Save!</button>

        </form>
    </div>
</template>

<script>
import shelterService from "../services/ShelterService.js";
export default {
    data() {
        return {
            valid: true,
            newParent: {}
        }
    },
    methods: {
        addParent() {
            shelterService.addParent(this.newParent).then(
                (response) => {
                    if (response.status === 201) {
                        alert("Parent Added!");
                        this.newParent = {};
                        this.$router.push({ name: "home" });
                    }
                }
            ).catch(
                (error) => {
                    if (error.response) {
                        this.valid = false;
                    }
                }
            )
        }
    }

}
</script>

<style></style>