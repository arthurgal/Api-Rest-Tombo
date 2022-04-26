package br.com.arthurgaldino.apirest.Controller.Dto.from;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import org.springframework.lang.Nullable;

public class EquipamentoFrom {

    @Nullable
    private String nome;
    @Nullable
    private String tombo;
    private StatusEquipamento status = StatusEquipamento.FUNCIONANDO;
    @Nullable
    private String nomeUsuario;
    @Nullable
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
