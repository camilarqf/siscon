import axios from "axios";
import authHeader from "./auth.header";

const API_URL = "http://localhost:8080/api/test/";

class UsuarioServico {
  getPublicContent() {
    return axios.get(API_URL + "all");
  }

  getUsuarioBoard() {
    return axios.get(API_URL + "usuario", { headers: authHeader() });
  }

  getCaixaBoard() {
    return axios.get(API_URL + "caixa", { headers: authHeader() });
  }

  getModeradorBoard() {
    return axios.get(API_URL + "moderador", { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + "admin", { headers: authHeader() });
  }
}

export default new UsuarioServico();
