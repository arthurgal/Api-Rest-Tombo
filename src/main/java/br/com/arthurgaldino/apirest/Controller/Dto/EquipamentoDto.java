package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Model.StatusEquipamento;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipamentoDto {

    private Long id;
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty @Length(min = 6, max = 6)
    private String tombo;
    private SetorEquipamento setor;
    private StatusEquipamento status;
    private String nomeUsuario;
    private String matriculaUsuario;

    public EquipamentoDto(Equipamento e){
        this.id = e.getId();
        this.nome = e.getNome();
        this.tombo = e.getTombo();
        this.setor = e.getSetor();
        this.status = e.getStatus();
        this.nomeUsuario = e.getUsuario().getNomeUsuario();
        this.matriculaUsuario = e.getUsuario().getMatriculaUsuario();


    }

    public EquipamentoDto(Optional<Equipamento> equipamento) {
    }

    public static Page<EquipamentoDto> converte(Page<Equipamento> equipamentos) {
        return equipamentos.map(EquipamentoDto::new);
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
}
