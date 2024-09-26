import axios from "axios";
const http = axios.create({
  baseURL: "http://localhost:8080",
});
export default {
  getAllPets() {
    return http.get("/pets");
  },


  getPet(id){
    return http.get(`/pet/${id}`);
  },

  addPet(pet){
    return http.post("add-pet", pet);
  },
  getAllParents() {
    return http.get("/parents");
  }
};
