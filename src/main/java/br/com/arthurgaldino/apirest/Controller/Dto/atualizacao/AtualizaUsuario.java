package br.com.arthurgaldino.apirest.Controller.Dto.atualizacao;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.Usuario;
import br.com.arthurgaldino.apirest.Repository.UsuarioRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AtualizaUsuario {

    @NotEmpty @NotNull
    private String nomeUsuario;
    @NotEmpty @NotNull
    private String matriculaUsuario;

    private SetorEquipamento setorEquipamento;


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

    public SetorEquipamento getSetorEquipamento() {
        return setorEquipamento;
    }

    public void setSetorEquipamento(SetorEquipamento setorEquipamento) {
        this.setorEquipamento = setorEquipamento;
    }

    public Usuario atualiza(String matricula, UsuarioRepository usuarioRepository){
        Usuario usuario = usuarioRepository.getByMatriculaUsuario(matricula);
        usuario.setNomeUsuario(this.nomeUsuario);
        usuario.setMatriculaUsuario(this.matriculaUsuario);
        usuario.setSetor(this.setorEquipamento);

        return usuario;
    }
}
