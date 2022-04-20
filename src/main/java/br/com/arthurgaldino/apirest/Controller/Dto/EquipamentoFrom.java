package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquipamentoFrom {

    private String nome;
    private String tombo;
    private StatusEquipamento status = StatusEquipamento.FUNCIONANDO;
    private String nomeUsuario;
    private String matriculaUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }


    public StatusEquipamento getStatus() {
        return status;
    }

    public void setStatus(StatusEquipamento status) {
        this.status = status;
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

    public Equipamento converte() {
        return new Equipamento(nome, tombo, nomeUsuario, matriculaUsuario, status);
    }
}
