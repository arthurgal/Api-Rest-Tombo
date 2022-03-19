package br.com.arthurgaldino.apirest.Service;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoFrom;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public List<EquipamentoDto> listaEquipamentos(SetorEquipamento busca){
        if (busca == null){
            var equipamentos = equipamentoRepository.findAll();
            return EquipamentoDto.converte(equipamentos);
        }else{
            var equipamentos = equipamentoRepository.findBySetor(busca);
            return EquipamentoDto.converte(equipamentos);
        }

    }

    public ResponseEntity<EquipamentoDto> cadastraEquipamento(@RequestBody @Valid EquipamentoFrom form, UriComponentsBuilder uriBuilder){

        //Validando se o tombo já existe
        var equipamentoDto = equipamentoRepository.findByTombo(form.getTombo());
        if(equipamentoDto != null){
            return ResponseEntity.unprocessableEntity().build();
        }
        //tombo não existe, então prosseguir com o cadastro do equipamento
        var equipamento = form.converte();
        equipamentoRepository.save(equipamento);

        URI uri = uriBuilder.path("equipamentos/{id}").buildAndExpand(equipamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new EquipamentoDto(equipamento));
    }

    public ResponseEntity<EquipamentoDto> atualizaEquipamento(@PathVariable Long id, @RequestBody @Valid AtualizaEquipamento form){

        var equipamento = form.atualiza(id, equipamentoRepository);

        return ResponseEntity.ok(new EquipamentoDto(equipamento));

    }

    public ResponseEntity<EquipamentoDto> detalhaEquipamento(@PathVariable("id") Long id){
        var equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            return ResponseEntity.ok(new EquipamentoDto(equipamento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<EquipamentoDto> detalhaEquipamentoTombo(@PathVariable("tombo") String tombo){
        var equipamento = equipamentoRepository.findByTombo(tombo);
        if(equipamento.isPresent()){
            return ResponseEntity.ok(new EquipamentoDto(equipamento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletaEquipamento(@PathVariable Long id){
        //Esse tipo <Optional> pode ter ou não ter o equipamento com o Id procurado
        var equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            equipamentoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }

}
