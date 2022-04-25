package br.com.arthurgaldino.apirest.Pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.arthurgaldino.apirest.Controller.Dto.EquipamentoDto;
import br.com.arthurgaldino.apirest.Model.Equipamento;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class UserPDFExporter {
    private List<Equipamento> listaEquipamento;

    public UserPDFExporter(List<Equipamento> listaEquipamento) {
        this.listaEquipamento = listaEquipamento;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


        cell.setPhrase(new Phrase("Nome do Equipamento", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Tombo", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Usuario", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Setor do Usuario", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Equipamento equipamento : listaEquipamento) {
            table.addCell(equipamento.getNome());
            table.addCell(equipamento.getTombo());
            table.addCell(equipamento.getStatus().toString());
            table.addCell(equipamento.getUsuario().getNomeUsuario());
            table.addCell(equipamento.getUsuario().getSetor().toString());


        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Relatorio De Equipamentos", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
