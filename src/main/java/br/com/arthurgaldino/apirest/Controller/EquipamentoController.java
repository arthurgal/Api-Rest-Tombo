package br.com.arthurgaldino.apirest.Controller;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoFrom;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping
    public List<EquipamentoDto> listaEquipamentos(){

        var equipamentos = equipamentoRepository.findAll();
        return EquipamentoDto.converte(equipamentos);
    }

    @PostMapping
    public void criaEquipamento(@RequestBody @Valid EquipamentoFrom form){
        var equipamento = form.converte();
        equipamentoRepository.save(equipamento);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EquipamentoDto> atualizaEquipamento(@PathVariable Long id, @RequestBody @Valid AtualizaEquipamento form){

        var equipamento = form.atualiza(id, equipamentoRepository);

        return ResponseEntity.ok(new EquipamentoDto(equipamento));

    }
    @GetMapping("/{id}")
    public EquipamentoDto detalharEquipamento(@PathVariable Long id){
        var equipamento = equipamentoRepository.getById(id);
        return new EquipamentoDto(equipamento);
    }
}
