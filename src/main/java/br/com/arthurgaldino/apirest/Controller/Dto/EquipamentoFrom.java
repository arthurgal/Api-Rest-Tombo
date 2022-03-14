package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquipamentoFrom {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty @Length(min = 6, max = 6)
    private String tombo;

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

    public Equipamento converte() {
        return new Equipamento(nome, tombo);
    }
}
