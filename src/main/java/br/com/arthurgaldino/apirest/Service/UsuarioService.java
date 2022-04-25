package br.com.arthurgaldino.apirest.Service;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaUsuario;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.UsuarioDto;
import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.Usuario;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

        public Page<UsuarioDto> listaUsuarios(String busca, int pagina, int qtd, String ordenacao){

            Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.ASC, ordenacao);


            if (busca == null){
                var usuario = usuarioRepository.findAll(paginacao);
                return UsuarioDto.converte(usuario);
            }else{
                var usuario = usuarioRepository.findByNomeUsuario(busca, paginacao);
                return UsuarioDto.converte(usuario);
            }

        }

    public ResponseEntity<Usuario> cadastraUsuario(Usuario u, UriComponentsBuilder uriBuilder) {

            var usuarioBusca = usuarioRepository.findByMatriculaUsuario(u.getMatriculaUsuario());
            if(usuarioBusca.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
            }
            Usuario u2 = usuarioRepository.save(u);
            u2.getEquipamentos().forEach(e -> e.setUsuario(u2));
            equipamentoRepository.saveAll(u2.getEquipamentos());
            URI uri = uriBuilder.path("/{id}").buildAndExpand(u2.getId()).toUri();

        return ResponseEntity.created(uri).body(u2);
    }

    public ResponseEntity<?> deletaUsuario(String matricula){
            var usuario = usuarioRepository.findByMatriculaUsuario(matricula);
            if(usuario.isPresent()){
                usuarioRepository.deleteByMatriculaUsuario(matricula);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UsuarioDto> atualizaUsuario(String matricula, AtualizaUsuario form) {
            var usuario = form.atualiza(matricula, usuarioRepository);

            return ResponseEntity.ok(new UsuarioDto(usuario));
    }

    public ResponseEntity<UsuarioDto> detalhaUsuario(String matricula) {
            var usuario = usuarioRepository.findByMatriculaUsuario(matricula);
            if(usuario.isPresent()){
                return ResponseEntity.ok(new UsuarioDto(usuario.get()));
            }
            return ResponseEntity.notFound().build();

    }
}

