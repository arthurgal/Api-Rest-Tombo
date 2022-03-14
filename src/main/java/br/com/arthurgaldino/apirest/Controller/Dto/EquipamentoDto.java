package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;

import java.util.List;
import java.util.stream.Collectors;

public class EquipamentoDto {

    private Long id;
    private String nome;
    private String tombo;

    public EquipamentoDto(Equipamento e){
        this.id = e.getId();
        this.nome = e.getNome();
        this.tombo = e.getTombo();
    }

    public EquipamentoDto() {
    }

    public static List<EquipamentoDto> converte(List<Equipamento> equipamentos) {
        return equipamentos.stream().map(EquipamentoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
