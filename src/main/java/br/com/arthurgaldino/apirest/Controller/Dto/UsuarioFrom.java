package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFrom {

    @NotEmpty @NotNull
    private String nomeUsuario;

    @NotEmpty @NotNull
    private String matriculaUsuario;

    private SetorEquipamento setor = SetorEquipamento.NAO_ALOCADO;

    private List<Equipamento> equipamentos = new ArrayList<Equipamento>();

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

    public SetorEquipamento getSetor() {
        return setor;
    }

    public void setSetor(SetorEquipamento setor) {
        this.setor = setor;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
