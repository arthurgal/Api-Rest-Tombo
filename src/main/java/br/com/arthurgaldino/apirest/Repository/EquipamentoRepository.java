package br.com.arthurgaldino.apirest.Repository;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Optional<Equipamento> findByTombo(String tombo);
    Page<Equipamento> findByTombo(String tombo, Pageable pageable);


    List<Equipamento> findByUsuarioNomeUsuario(String filtro);

    Equipamento getByTombo(String tombo);

}
