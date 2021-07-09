package br.org.generation.ProjetoBlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.ProjetoBlog.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
  
    @Autowired
    private TestRestTemplate testRestTemplate;

    private Usuario usuario;
    private Usuario usuarioUpdate;

    @BeforeAll
    public void start() throws ParseException {

        LocalDate dataPost = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        /**
	    * Esta linha gera o usuário admin. Descomente esta linha, 
	    * comente a próxima linha e rode o teste Cadastrar Usuário 
	    * sozinho para criar o usuário admin@email.com.br no Banco 
	    * de Dados. 
	    *
	    * Em seguida faça o processo inverso.
        * 
        * Este usuário será utilizado para logar
        * na API nos demais testes nos endpoints protegidos na API
        * 
        */
        
       //usuario = new Usuario(0L, "Administrador", "admin@email.com.br", "admin123", dataPost);
       
       usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278", dataPost);

        LocalDate dataPut = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        usuarioUpdate = new Usuario(1L, "João da Silva Souza", "joao@email.com.br", "joao123", dataPut);
  
    }

    @Disabled
    @Test
    @DisplayName("✔ Cadastrar Usuário!")
    public void deveRealizarPostUsuario() {

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    
    }
  
    @Test
    @DisplayName("👍 Listar todos os Usuários!")
    public void deveMostrarTodosUsuarios() {
        ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123").exchange("/usuarios/all", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

  }

    @Test
    @DisplayName("😳 Alterar Usuário!")
    public void deveRealizarPutUsuario() {
        
        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
        ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123").exchange("/usuarios/alterar", HttpMethod.PUT, request, Usuario.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

  }

}