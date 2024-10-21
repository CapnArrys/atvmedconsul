package br.com.arrys.medicalconsult.usuario.services;

import br.com.arrys.medicalconsult.usuario.domain.Usuario;
import br.com.arrys.medicalconsult.usuario.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void cadastrarUsuario(){
                Usuario usuario = new Usuario();
                usuario.setNomeUsuario("Teste");

                when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

                var resulto = usuarioService.cadastrarUsuario(usuario);

                assertNotNull(usuario);

                assertEquals("Teste",resulto.getNomeUsuario());

                verify(usuarioRepository, times(1)).save(usuario);

    }

    @Test
    void listarUsuarios(){

        Usuario usuario1 = new Usuario();
        usuario1.setNomeUsuario("Teste");
        Usuario usuario2 = new Usuario();
        usuario2.setNomeUsuario("Teste2");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        var resultado = usuarioService.listarUsuarios();

        assertAll(

                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Teste2", resultado.get(1).getNomeUsuario(), "O nome do usuario 2 está errado"),
                () -> assertEquals("Teste", resultado.get(0).getNomeUsuario(), "O nome do usuario 1 está errado")

        );
    }




}