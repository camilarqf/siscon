import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  login(usuario) {
    return axios
      .post(API_URL + "signin", {
        usuario: usuario.usuario,
        senha: usuario.senha
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem("usuario", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("usuario");
  }

  registrar(usuario) {
    return axios.post(API_URL + "signup", {
      usuario: usuario.usuario,
      matricula: usuario.matricula,
      senha: usuario.senha
    });
  }
}
export default new AuthService();
