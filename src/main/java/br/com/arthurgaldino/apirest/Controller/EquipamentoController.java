package br.com.arthurgaldino.apirest.Controller;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoFrom;
import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import br.com.arthurgaldino.apirest.Service.EquipamentoService;
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
    private EquipamentoService equipamentoService;

    @GetMapping
    public List<EquipamentoDto> listaEquipamentos(SetorEquipamento busca){
        return equipamentoService.listaEquipamentos(busca);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<EquipamentoDto> cadastraEquipamento(@RequestBody @Valid EquipamentoFrom form, UriComponentsBuilder uriBuilder){
        return equipamentoService.cadastraEquipamento(form, uriBuilder);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EquipamentoDto> atualizaEquipamento(@PathVariable Long id, @RequestBody @Valid AtualizaEquipamento form){
        return equipamentoService.atualizaEquipamento(id, form);

    }


    @GetMapping("/id/{id}")
    public ResponseEntity<EquipamentoDto> detalhaEquipamento(@PathVariable Long id){
        return equipamentoService.detalhaEquipamento(id);
    }

    @GetMapping("/tombo/{tombo}")
    public ResponseEntity<EquipamentoDto> detalhaEquipamentoTombo(@PathVariable String tombo){
        return equipamentoService.detalhaEquipamentoTombo(tombo);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletaEquipamento(@PathVariable Long id){
        return equipamentoService.deletaEquipamento(id);
    }
}
