package br.com.arthurgaldino.apirest.Controller;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaUsuario;
import br.com.arthurgaldino.apirest.Controller.Dto.UsuarioDto;
import br.com.arthurgaldino.apirest.Controller.Dto.UsuarioFrom;
import br.com.arthurgaldino.apirest.Model.Usuario;
import br.com.arthurgaldino.apirest.Service.UsuarioService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @Transactional
    public ResponseEntity<UsuarioDto> cadastraUsuario(@RequestBody @Valid UsuarioFrom usuario, UriComponentsBuilder uriBuilder){
        return usuarioService.cadastraUsuario(usuario, uriBuilder);

    }

    @PutMapping("/{matricula}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizaUsuario(@PathVariable String matricula, @RequestBody AtualizaUsuario form){
        return usuarioService.atualizaUsuario(matricula, form);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UsuarioDto> detalhaUsuario(@PathVariable String matricula){
        return usuarioService.detalhaUsuario(matricula);
    }

    @DeleteMapping("/{matricula}")
    @Transactional
    public ResponseEntity<?> deletaUsuario(@PathVariable String matricula){
        return usuarioService.deletaUsuario(matricula);
    }

}
