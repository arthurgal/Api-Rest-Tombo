package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.Usuario;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDto {

    private String nomeUsuario;

    private String matriculaUsuario;

    private SetorEquipamento setorEquipamento = SetorEquipamento.NAO_ALOCADO;

    private List<Equipamento> equipamentos = new ArrayList<Equipamento>();

    public UsuarioDto(Usuario u) {
        this.nomeUsuario = u.getNomeUsuario();
        this.matriculaUsuario = u.getMatriculaUsuario();
        this.equipamentos = u.getEquipamentos();
        this.setorEquipamento = u.getSetor();
    }

    public static Page<UsuarioDto> converte(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

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

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public SetorEquipamento getSetorEquipamento() {
        return setorEquipamento;
    }

    public void setSetorEquipamento(SetorEquipamento setorEquipamento) {
        this.setorEquipamento = setorEquipamento;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}

