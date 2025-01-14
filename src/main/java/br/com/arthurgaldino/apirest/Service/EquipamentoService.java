package br.com.arthurgaldino.apirest.Service;

import br.com.arthurgaldino.apirest.Controller.Dto.atualizacao.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.from.EquipamentoFrom;
import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import br.com.arthurgaldino.apirest.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<EquipamentoDto> listaEquipamentos(String busca, int pagina, int qtd, String ordenacao){

        Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.ASC, ordenacao);


        if (busca == null){
            var equipamentos = equipamentoRepository.findAll(paginacao);
            return EquipamentoDto.converte(equipamentos);
        }else{
            var equipamentos = equipamentoRepository.findByTombo(busca, paginacao);
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

        URI uri = uriBuilder.path("/{id}").buildAndExpand(equipamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new EquipamentoDto(equipamento));
    }

    public ResponseEntity<EquipamentoDto> atualizaEquipamento(String tombo, AtualizaEquipamento form){

        var equipamento = form.atualiza(tombo, equipamentoRepository);

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

    public ResponseEntity<?> deletaEquipamento(String tombo){

        //Esse tipo <Optional> pode ter ou não ter o equipamento com o Id procurado

        var equipamento = equipamentoRepository.findByTombo(tombo);
        if(equipamento.isPresent()){
            equipamentoRepository.deleteById(equipamento.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }

    public List<Equipamento> listAll(String usuario) {
        if (usuario == null){
            return equipamentoRepository.findAll(Sort.by("usuarioSetor").ascending());
        }
        else{
            return equipamentoRepository.findByUsuarioNomeUsuario(usuario);
        }

    }

}
