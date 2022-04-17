package br.com.arthurgaldino.apirest.Controller;

import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.UsuarioDto;
import br.com.arthurgaldino.apirest.Model.Usuario;
import br.com.arthurgaldino.apirest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioDto> lista(@RequestParam(required = false) String busca,
                                      @RequestParam(required = true) int pagina, @RequestParam(required = true) int qtd,
                                      @RequestParam String ordenacao){
        return usuarioService.listaUsuarios(busca, pagina, qtd, ordenacao);

    }

    @PostMapping
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario){
        Usuario u = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

}
