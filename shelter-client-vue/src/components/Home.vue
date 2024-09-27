<template>
    <div class="container">
        
        <div class="section pets-section">
            <h1>Pets!</h1>
            <div class="pets-list">
                <pets />
            </div>
        </div>

        
        <div class="section parents-section">
            <button @click="toggleParents">{{ showParents ? 'Hide Parents' : 'Show Parents' }}</button>
            <div v-if="showParents">
                <h1>Parents!</h1>
                <div class="parents-list">
                    <parents />
                </div>
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

.pets-list, .parents-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 20px;
}
.petCard, .parentCard {
  flex: 1 1 200px; 
  max-width: 250px;
}

</style>