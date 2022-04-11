package br.com.arthurgaldino.apirest.Service;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoFrom;

import br.com.arthurgaldino.apirest.Model.Equipamento;

import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public Page<EquipamentoDto> listaEquipamentos(SetorEquipamento busca, int pagina, int qtd, String ordenacao){

        Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.ASC, ordenacao);


        if (busca == null){
            var equipamentos = equipamentoRepository.findAll(paginacao);
            return EquipamentoDto.converte(equipamentos);
        }else{
            var equipamentos = equipamentoRepository.findBySetor(busca, paginacao);
            return EquipamentoDto.converte(equipamentos);
        }

    }

    public ResponseEntity<EquipamentoDto> cadastraEquipamento(EquipamentoFrom form, UriComponentsBuilder uriBuilder){

        var equipamentoBusca = equipamentoRepository.findByTombo(form.getTombo());
        if (equipamentoBusca.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        var equipamento = form.converte();
        equipamentoRepository.save(equipamento);

        URI uri = uriBuilder.path("equipamentos/{id}").buildAndExpand(equipamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new EquipamentoDto(equipamento));
    }

    public ResponseEntity<EquipamentoDto> atualizaEquipamento(Long id, AtualizaEquipamento form){

        var equipamento = form.atualiza(id, equipamentoRepository);

        return ResponseEntity.ok(new EquipamentoDto(equipamento));

    }

/*    public ResponseEntity<EquipamentoDto> detalhaEquipamento(Long id){
        var equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            return ResponseEntity.ok(new EquipamentoDto(equipamento.get()));
        }
        return ResponseEntity.notFound().build();
    }*/

    public ResponseEntity<EquipamentoDto> detalhaEquipamentoTombo(String tombo){
        var equipamento = equipamentoRepository.findByTombo(tombo);
        if(equipamento.isPresent()){
            return ResponseEntity.ok(new EquipamentoDto(equipamento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletaEquipamento(Long id){

        //Esse tipo <Optional> pode ter ou não ter o equipamento com o Id procurado

        var equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            equipamentoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }

    public List<Equipamento> listAll() {
        return equipamentoRepository.findAll(Sort.by("Setor").ascending());
    }

}
