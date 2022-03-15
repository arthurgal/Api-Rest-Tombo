package br.com.arthurgaldino.apirest.Repository;

import br.com.arthurgaldino.apirest.Model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Optional<Equipamento> findByTombo(String tombo);
}
