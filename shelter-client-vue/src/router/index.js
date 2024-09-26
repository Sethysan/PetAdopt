import { createRouter as _createRouter, createWebHistory } from "vue-router";
import Home from "../components/Home.vue";
import AddPet from "../components/AddPet.vue";
import AddParent from "../components/AddParent.vue";


const routes = [
  { path: "/", name: "home", component: Home },
  { path: "/add-Pet", name: "addPet", component: AddPet },
  {path: "/add-Parent", name: "addParent", component: AddParent }
];

export function createRouter() {
  return _createRouter({
    history: createWebHistory(),
    routes: routes,
  });
}
