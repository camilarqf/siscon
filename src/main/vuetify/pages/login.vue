<template>
  <v-row justify="center" class="align-center">
    <v-col cols="12" lg="4"
      ><template>
        <validation-observer ref="observer">
          <form @submit.prevent="submit">
            <v-card outlined elevation="5" class="pa-4">
              <v-list-item three-line class="justify-center">
                <h1 class="cyan--text text--darken-3">Login</h1>
              </v-list-item>
              <validation-provider
                v-slot="{ errors }"
                name="Usuario"
                rules="required"
              >
                <v-list-item three-line class="justify-center">
                  <v-text-field
                    v-model="usuario"
                    prepend-icon="mdi-account"
                    :error-messages="errors"
                    placeholder="Usuário"
                    required
                  >
                  </v-text-field>
                </v-list-item>
              </validation-provider>
              <validation-provider
                v-slot="{ errors }"
                name="Senha"
                rules="required"
              >
                <v-list-item three-line class="justify-center">
                  <v-text-field
                    v-model="senha"
                    prepend-icon="mdi-lock"
                    placeholder="Senha"
                    :error-messages="errors"
                  >
                  </v-text-field>
                </v-list-item>
              </validation-provider>
              <v-card-actions class="pa-4">
                <v-btn
                  block
                  type="submit"
                  depressed
                  rounded
                  color="cyan darken-3"
                  class="white--text pa-4"
                >
                  Entrar
                </v-btn>
              </v-card-actions>
              <v-card-actions class="pa-4">
                <a href="">Registre-se</a>
              </v-card-actions>
            </v-card>
          </form>
        </validation-observer>
      </template>
    </v-col>
  </v-row>
</template>

<script>
import { required } from "vee-validate/dist/rules";
import { extend, ValidationObserver, ValidationProvider } from "vee-validate";

extend("required", {
  ...required,
  message: "O campo {_field_} não pode ser vazio"
});

export default {
  layout: "login",
  components: {
    ValidationProvider,
    ValidationObserver
  },
  data() {
    return {
      usuario: ""
    };
  },

  methods: {
    submit() {
      this.$refs.observer.validate();
    }
  }
};
</script>
