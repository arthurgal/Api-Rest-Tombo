package br.com.arthurgaldino.apirest.Controller.Dto.atualizacao;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import org.springframework.lang.Nullable;

public class AtualizaEquipamento {

    @Nullable
    private String nome;
    @Nullable
    private String tombo;
    private StatusEquipamento status;

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

    public Equipamento atualiza(Long id, EquipamentoRepository equipamentoRepository) {
        Equipamento equipamento = equipamentoRepository.getById(id);
        equipamento.setNome(this.nome);
        equipamento.setTombo(this.tombo);
        equipamento.setStatus(this.status);

        return equipamento;

    }
}
