<template>
    <div class="container">

        <div class="section pets-section">
            <div>
                <h1>Pets!</h1>
            </div>
            <div class="pets-list">
                <pets />
            </div>
            <router-link :to="{ name: 'addPet' }">
                <button class="add-pet">Add New Pet</button>
            </router-link>
        </div>


        <div class="section parents-section">
            <button @click="toggleParents" class="parents-view">{{ showParents ? 'Hide Parents' : 'Show Parents'
                }}</button>
            <div v-if="showParents">
                <div class="parents-list"></div>
                <h1>Parents!</h1>
                <div>
                    <router-link :to="{ name: 'addParent' }" class="parent-link">
                        <button class="add-parent">Add New Parent</button>
                    </router-link>
                </div>
                <parents />

            </div>
        </div>
    </div>
</template>

<script>
import parents from "./Parents.vue";
import pets from "./Pets.vue";

export default {
    data() {
        return {
            showParents: false
        };
    },
    methods: {
        toggleParents() {
            this.showParents = !this.showParents;
        }
    },
    components: {
        parents,
        pets
    }
};
</script>

<style scoped>
.section {
    width: 100%;
    padding: 20px;
}

.pets-list,
.parents-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    gap: 20px;
}

.petCard,
.parentCard {
    flex: 1 1 200px;
    max-width: 250px;
}

.parents-view {
    width: auto;
    font-weight: bold;
    border-radius: 7px;
    border: 1px solid #000;
    padding: 10px 20px;
    background-color: rgba(248, 222, 182, 0.8);
    color: black;
    text-decoration: none;
    margin-left: 10px;
    margin-bottom: 15px;
    transition: background-color 0.4s ease-in-out;
}
.add-pet:hover ,
.parents-view:hover {
    background-color:  rgba(247, 200, 131, 0.8);
    transform: scale(1.02);
}

.parent-link {
    display: inline-block;
    text-decoration: none;
    color: inherit;
    position: relative;
}
.add-pet ,
.add-parent {
    width: auto;
    font-weight: bold;
    border-radius: 7px;
    border: 1px solid #000;
    padding: 10px 20px;
    background-color: rgba(248, 222, 182, 0.8);
    color: black;
    text-decoration: none;
    margin-left: 10px;
    margin-bottom: 15px;
    transition: background-color 0.4s ease-in-out;
    
}

.add-parent:hover {
    background-color: rgba(53, 197, 233, 0.8);
    transform: scale(1.02);
}

h1 {

    display: inline-block;
    justify-content: center;
    margin-left: 10px;

}
</style>