package br.com.arthurgaldino.apirest.Controller;

import br.com.arthurgaldino.apirest.Controller.Dto.AtualizaEquipamento;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoFrom;
import br.com.arthurgaldino.apirest.Model.Equipamento;
import br.com.arthurgaldino.apirest.Model.SetorEquipamento;
import br.com.arthurgaldino.apirest.Pdf.UserPDFExporter;
import br.com.arthurgaldino.apirest.Repository.EquipamentoRepository;
import br.com.arthurgaldino.apirest.Service.EquipamentoService;
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
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public Page<EquipamentoDto> lista(@RequestParam(required = false) SetorEquipamento busca,
                                                  @RequestParam(required = true) int pagina, @RequestParam(required = true) int qtd,
                                                  @RequestParam String ordenacao){
        return equipamentoService.listaEquipamentos(busca, pagina, qtd, ordenacao);

    }

    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Equipamento> listUsers = equipamentoService.listAll();

        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<EquipamentoDto> cadastra(@RequestBody @Valid EquipamentoFrom form, UriComponentsBuilder uriBuilder){
        return equipamentoService.cadastraEquipamento(form, uriBuilder);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EquipamentoDto> atualiza(@PathVariable Long id, @RequestBody @Valid AtualizaEquipamento form){
        return equipamentoService.atualizaEquipamento(id, form);

    }

    @GetMapping("/tombo")

    public ResponseEntity<EquipamentoDto> detalhaTombo(@RequestParam(required = true) String tombo){
        return equipamentoService.detalhaEquipamentoTombo(tombo);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleta(@PathVariable Long id){
        return equipamentoService.deletaEquipamento(id);
    }
}
