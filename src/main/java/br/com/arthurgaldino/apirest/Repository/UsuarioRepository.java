package br.com.arthurgaldino.apirest.Repository;

import br.com.arthurgaldino.apirest.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByNomeUsuario(String setor, Pageable paginacao);


    Optional<Usuario> findByMatriculaUsuario(String matricula);

    void deleteByMatriculaUsuario(String matricula);

    Usuario getByMatriculaUsuario(String matricula);
}
