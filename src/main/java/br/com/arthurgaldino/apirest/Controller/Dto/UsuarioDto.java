package br.com.arthurgaldino.apirest.Controller.Dto;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.Usuario;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDto {

    private Long id;

    private String nomeUsuario;

    private String matriculaUsuario;

    private List<Equipamento> equipamentos = new ArrayList<Equipamento>();

    public UsuarioDto(Usuario u) {
        this.id = u.getId();
        this.nomeUsuario = u.getNomeUsuario();
        this.matriculaUsuario = u.getMatriculaUsuario();
        this.equipamentos = u.getEquipamentos();
    }

    public static Page<UsuarioDto> converte(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

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

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
