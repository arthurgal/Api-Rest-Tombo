package br.com.arthurgaldino.apirest.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "servidor", nullable = false)
    private String nomeUsuario;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matriculaUsuario;

    @Column(name = "setor") @Enumerated(EnumType.STRING)
    private SetorEquipamento setor = SetorEquipamento.NAO_ALOCADO;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("usuario")
    private List<Equipamento> equipamentos = new ArrayList<Equipamento>();


    public Usuario(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SetorEquipamento getSetor() {
        return setor;
    }

    public void setSetor(SetorEquipamento setor) {
        this.setor = setor;
    }


    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
