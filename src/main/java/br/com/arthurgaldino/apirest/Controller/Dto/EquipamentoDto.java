package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import org.springframework.data.domain.Page;

import java.util.Optional;

public class EquipamentoDto {

    private String nome;

    private String tombo;
    /*private String nomeUsuario;
    private String matriculaUsuario;*/
    private StatusEquipamento status;

    public EquipamentoDto(Equipamento e){
        this.nome = e.getNome();
        this.tombo = e.getTombo();
        /*this.nomeUsuario = e.getUsuario().getNomeUsuario();
        this.matriculaUsuario = e.getUsuario().getMatriculaUsuario();*/
        this.status = e.getStatus();

    }

    public EquipamentoDto(Optional<Equipamento> equipamento) {
    }

    public static Page<EquipamentoDto> converte(Page<Equipamento> equipamentos) {
        return equipamentos.map(EquipamentoDto::new);
    }

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

    /*public String getNomeUsuario() {
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
    }*/

    public StatusEquipamento getStatus() {
        return status;
    }

    public void setStatus(StatusEquipamento status) {
        this.status = status;
    }
}
