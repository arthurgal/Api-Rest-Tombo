package br.com.arthurgaldino.apirest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tombo", nullable = false, unique = true) @Length(min = 6, max = 6)
    private String tombo;

    @Column (name = "status") @Enumerated(EnumType.STRING)
    private StatusEquipamento status = StatusEquipamento.FUNCIONANDO;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    public Equipamento(){

    }

    public Equipamento(String nome, String tombo, String  nomeUsuario, String  matriculaUsuario, StatusEquipamento status) {
        this.nome = nome;
        this.tombo = tombo;
        this.usuario.setNomeUsuario(nomeUsuario);
        this.usuario.setMatriculaUsuario(matriculaUsuario);
        this.status = status;

    }
    /*
    public Equipamento(String nome, String tombo, SetorEquipamento setor, StatusEquipamento status, String nomeUsuario) {
        nome = this.getNome();
        tombo = this.getTombo();
        setor = this.getSetor();
        status = this.getStatus();
        nomeUsuario = this.usuario.getNome();
    }*/

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

    public Usuario getUsuario() {
        return usuario;
    }

    public StatusEquipamento getStatus() {
        return status;
    }

    public void setStatus(StatusEquipamento status) {
        this.status = status;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
