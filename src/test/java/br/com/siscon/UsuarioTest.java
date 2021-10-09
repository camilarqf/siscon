package br.com.siscon;

import br.com.siscon.controller.usuario.UsuarioController;
import br.com.siscon.model.usuario.Role;
import br.com.siscon.model.usuario.Usuario;
import br.com.siscon.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UsuarioController.class
})
public class UsuarioTest {
    //URL base para acesso desse controlador
    private final String BASE_URL = "/usuario";

    //Instância do ObjectMapper para trabalhar com JSON
    private ObjectMapper objectMapper;

    //Controlador REST tratado por meio de injeção de dependências
    @Autowired
    private UsuarioController usuarioController;

    //Instância do MockMVC
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    //Instância do mock repository
    @MockBean
    private UsuarioRepository mockRepository;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(usuarioController)
                .build();
    }

    @Test
    public void buscar_id_200() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsuario("usuario teste");
        usuario.setMatricula("2223321");
        usuario.setRole(Collections.singleton(Role.ROLE_USUARIO));
        usuario.setData(new Date());
        usuario.setSenha(passwordEncoder.encode("123456"));

        when(mockRepository.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",is(1L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario",is("usuario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricula",is("2223321")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role",is("ROLE_USUARIO")))
                ;

        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    public void buscar_id_404() throws Exception {
        mockMvc.perform(get(BASE_URL + "/2")).andExpect(status().isNotFound());
    }

}