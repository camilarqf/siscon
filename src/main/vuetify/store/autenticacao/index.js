import authService from "../../src/auth.service";

export const state = () => ({
  status: {
    logado: false
  },
  usuario: null
});

export const actions = {
  login({ commit }, usuario) {
    return authService.login(usuario).then(
      usuario => {
        commit("loginSucesso", usuario);
        return Promise.resolve(usuario);
      },
      error => {
        commit("loginFalhou");
        return Promise.reject(error);
      }
    );
  },

  logout({ commit }) {
    authService.logout();
    commit("logout");
  },

  registrar({ commit }, usuario) {
    return authService.registrar(usuario).then(
      response => {
        commit("registrarSucesso");
        return Promise.resolve(response.data);
      },
      error => {
        commit("registrarFalhou");
        return Promise.reject(error);
      }
    );
  }
};
export const mutations = {
  loginSucesso(state, usuario) {
    state.usuario.logado = true;
    state.usuario = usuario;
  },
  loginFalhou(state) {
    state.usuario.logado = false;
    state.usuario = null;
  },
  logout(state) {
    state.usuario.logado = false;
    state.usuario = null;
  },
  registrarSucesso(state) {
    state.usuario.logado = false;
  },
  registrarFalhou(state) {
    state.usuario.logado = false;
  }
};
