<template>
    <div>
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
            <button type="submit">Adopt</button>
        </form>
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
            selectedParentId: ""
        };
        
    },
    created() {
        shelterService.getAllPets().then(
            (response) => {
                this.pets = response.data;
            }
        );
        shelterService.getAllParents().then(
            (response) => {
                this.parents = response.data;
            }
        )
    },
    methods: {
        adopt() {
            if(this.selectedPetId && this.selectedParentId ) {
                const adoptionData = {
                    petId: this.selectedPetId,
                    parentId: this.selectedParentId
                };
                shelterService.adopt(adoptionData).then(
                    (response) => {
                        alert("Adoption Successful!");
                        this.selectedParentId ="";
                        this.selectedPetId = "";
                        this.$router.push( {name: "home"});
                    }
                ).catch(
                    (error) => {
                        alert( "Adoption Failed");
                    }
                )
            }else{
                alert( "Please select both a pet and a parent");
            }
        }
    }

};
</script>

<style>
label {
  display: block;
  margin-top: 10px;
}

select, button {
  margin-bottom: 20px;
  padding: 5px;
}
</style>