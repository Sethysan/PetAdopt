import { createRouter as _createRouter, createWebHistory } from "vue-router";
import Home from "../components/Home.vue";
import AddPet from "../components/AddPet.vue";
import AddParent from "../components/AddParent.vue";
import Adopt from "../components/AdoptionForm.vue";
import PetDetails from "../components/PetDetails.vue";

const routes = [
  { path: "/", name: "home", component: Home },
  { path: "/add-Pet", name: "addPet", component: AddPet },
  { path: "/add-Parent", name: "addParent", component: AddParent },
  { path: "/adopt", name: "adopt", component: Adopt },
  { path: "/pet/:id", name: "petDetails", component: PetDetails },
];

export function createRouter() {
  return _createRouter({
    history: createWebHistory(),
    routes: routes,
  });
}
