package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquipamentoFrom {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty @Length(min = 6, max = 6)
    private String tombo;
    private SetorEquipamento setor = SetorEquipamento.NAO_ALOCADO;
    private StatusEquipamento status = StatusEquipamento.FUNCIONANDO;

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

    public SetorEquipamento getSetor() {
        return setor;
    }

    public void setSetor(SetorEquipamento setor) {
        this.setor = setor;
    }

    public StatusEquipamento getStatus() {
        return status;
    }

    public void setStatus(StatusEquipamento status) {
        this.status = status;
    }

    public Equipamento converte() {
        return new Equipamento(nome, tombo, setor, status);
    }
}
