import { createRouter as _createRouter, createWebHistory } from "vue-router";
import Home from "../components/Home.vue";
import Add from "../components/AddPet.vue";

const routes = [
  { path: "/", name: "home", component: Home },
  { path: "/add", name: "add", component: Add },
];

export function createRouter() {
  return _createRouter({
    history: createWebHistory(),
    routes: routes,
  });
}
