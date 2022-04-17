package br.com.arthurgaldino.apirest.Service;

import br.com.arthurgaldino.apirest.Controller.Dto.UsuarioDto;
import br.com.arthurgaldino.apirest.Model.Usuario;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import br.com.arthurgaldino.apirest.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Usuario salvarUsuario(Usuario u) {
            Usuario u2 = usuarioRepository.save(u);
            u2.getEquipamentos().forEach(e -> e.setUsuario(u2));
            equipamentoRepository.saveAll(u2.getEquipamentos());
            return u2;
    }
}

