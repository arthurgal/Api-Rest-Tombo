package br.com.arthurgaldino.apirest.Controller;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoFrom;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<EquipamentoDto> cadastraEquipamento(@RequestBody @Valid EquipamentoFrom form, UriComponentsBuilder uriBuilder){
        var equipamento = form.converte();
        equipamentoRepository.save(equipamento);

        URI uri = uriBuilder.path("topico/{id}").buildAndExpand(equipamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new EquipamentoDto(equipamento));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EquipamentoDto> atualizaEquipamento(@PathVariable Long id, @RequestBody @Valid AtualizaEquipamento form){

        var equipamento = form.atualiza(id, equipamentoRepository);

        return ResponseEntity.ok(new EquipamentoDto(equipamento));

    }
    @GetMapping("/{id}")
    public EquipamentoDto detalhaEquipamento(@PathVariable Long id){
        var equipamento = equipamentoRepository.getById(id);
        return new EquipamentoDto(equipamento);
    }
}
