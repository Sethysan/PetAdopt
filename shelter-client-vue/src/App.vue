<template>
  <div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
body {
  background-image: url('./assets/pics/Animals.jpg');
  background-color: rgb(56, 32, 16);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  background-attachment: fixed;
  height: 100vh;
  margin: 0;
  padding: 0;
}
</style>