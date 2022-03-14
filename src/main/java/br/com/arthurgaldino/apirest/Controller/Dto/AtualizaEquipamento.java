package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;

public class AtualizaEquipamento {

    private String nome;
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

    public Equipamento atualiza(Long id, EquipamentoRepository equipamentoRepository) {
        Equipamento equipamento = equipamentoRepository.getById(id);
        equipamento.setNome(this.nome);
        equipamento.setTombo(this.tombo);

        return equipamento;

    }
}
