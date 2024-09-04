package br.com.arrys.medicalconsult.usuario.services;

import br.com.arrys.medicalconsult.usuario.domain.Usuario;
import br.com.arrys.medicalconsult.usuario.repositories.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado: ", id));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        usuarioExistente.setNomeUsuario(usuario.getNomeUsuario());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setDataNascimento(usuario.getDataNascimento());

        return usuarioRepository.save(usuarioExistente);

    }

    public void excluirUsuario(Long id) {
        Usuario usuarioParaSerDeletado = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        usuarioRepository.delete(usuarioParaSerDeletado);

    }
}
