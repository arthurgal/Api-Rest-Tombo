package br.com.arthurgaldino.apirest.Model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tombo") @Length(min = 6, max = 6)
    private String tombo;

    @Column(name = "setor") @Enumerated(EnumType.STRING)
    private SetorEquipamento setor = SetorEquipamento.NAO_ALOCADO;

    @Column (name = "status") @Enumerated(EnumType.STRING)
    private StatusEquipamento status = StatusEquipamento.FUNCIONANDO;

    public Equipamento(){

    }

    public Equipamento(String nome, String tombo, SetorEquipamento setor, StatusEquipamento status) {
        this.nome = nome;
        this.tombo = tombo;
        this.setor = setor;
        this.status = status;
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
