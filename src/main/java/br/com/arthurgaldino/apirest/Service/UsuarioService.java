package br.com.arthurgaldino.apirest.Service;

import br.com.arthurgaldino.apirest.Controller.Dto.atualizacao.AtualizaUsuario;
import br.com.arthurgaldino.apirest.Controller.Dto.UsuarioDto;
import br.com.arthurgaldino.apirest.Controller.Dto.from.UsuarioFrom;
import br.com.arthurgaldino.apirest.Model.Usuario;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import br.com.arthurgaldino.apirest.Repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    public ResponseEntity<UsuarioDto> cadastraUsuario(UsuarioFrom u, UriComponentsBuilder uriBuilder) {
            var usuarioFinal = new Usuario();
            var usuarioBusca = usuarioRepository.findByMatriculaUsuario(u.getMatriculaUsuario());
            if(usuarioBusca.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
            }
            BeanUtils.copyProperties(u, usuarioFinal);

            Usuario u2 = usuarioRepository.save(usuarioFinal);
            u2.getEquipamentos().forEach(e -> e.setUsuario(u2));
            equipamentoRepository.saveAll(u2.getEquipamentos());
            URI uri = uriBuilder.path("/{id}").buildAndExpand(u2.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDto(u2));
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

