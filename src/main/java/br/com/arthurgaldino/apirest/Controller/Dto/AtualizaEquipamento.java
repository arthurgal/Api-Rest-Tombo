package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;

public class AtualizaEquipamento {

    private String nome;
    private String tombo;
    //não é boa pratica ter esse tipo de atributo em uma DTO
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

    public Equipamento atualiza(Long id, EquipamentoRepository equipamentoRepository) {
        Equipamento equipamento = equipamentoRepository.getById(id);
        equipamento.setNome(this.nome);
        equipamento.setTombo(this.tombo);
        equipamento.setSetor(this.setor);
        equipamento.setStatus(this.status);

        return equipamento;

    }
}
