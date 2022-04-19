package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.Usuario;
import br.com.arthurgaldino.apirest.Repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class AtualizaUsuario {

    private String nomeUsuario;
    private String matriculaUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(String matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public Usuario atualiza(String matricula, UsuarioRepository usuarioRepository){
        Usuario usuario = usuarioRepository.getByMatriculaUsuario(matricula);
        usuario.setNomeUsuario(this.nomeUsuario);
        usuario.setMatriculaUsuario(this.matriculaUsuario);

        return usuario;
    }
}
