<template>
    <div>
        <pets/>
        <parents />
    </div>
</template>

<script>

import parents from "./Parents.vue";
import pets from "./Pets.vue";

export default {
    
    
    
    components: {
        parents,
        pets
    },
};
</script>

<style>

</style>
