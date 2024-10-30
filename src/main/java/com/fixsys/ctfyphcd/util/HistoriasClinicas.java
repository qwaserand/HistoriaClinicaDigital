package com.fixsys.ctfyphcd.util;

import com.fixsys.ctfyphcd.model.Paciente;
import com.fixsys.ctfyphcd.model.Profesional;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.Map;

public class HistoriasClinicas extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {


        Paciente paciente = (Paciente) model.get("paciente");
        Profesional profesional = (Profesional) model.get("profesional");
        String anotaciones = (String) model.get("anotaciones");

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,14, Color.DARK_GRAY);
        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celdaTitulo = null;
        celdaTitulo = new PdfPCell(new Phrase("HISTORIA CLINICA", fuenteTitulo));
        celdaTitulo.setBorder(0);
        celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
        celdaTitulo.setPadding(20);

        tablaTitulo.addCell(celdaTitulo);
        tablaTitulo.setSpacingAfter(20);

        document.add(tablaTitulo);

        Font fuenteDatos = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

        // Información del paciente
        document.add(new Paragraph("Datos del Paciente"));
        document.add(new Paragraph("Nombre: " + paciente.getName(), fuenteDatos));
        document.add(new Paragraph("Apellido: " + paciente.getLastname()));
        document.add(new Paragraph("DNI:" + paciente.getDni()));
        document.add(new Paragraph("Fecha de Nacimiento: " + paciente.getBirthdate()));
        document.add(new Paragraph("Email: " + paciente.getEmail()));
        document.add(new Paragraph("Género: " + paciente.getGender()));
        document.add(new Paragraph("Dirección: " + paciente.getAddress()));
        document.add(new Paragraph("Teléfono: " + paciente.getPhone()));
        document.add(new Paragraph("Contacto de Emergencia: " + paciente.getEmergencyContactInformation()));
        document.add(new Paragraph("Número de Seguro Médico: " + paciente.getHealthInsuranceNumber()));

        document.add(new Paragraph("\n"));

        // Textbox anotaciones
        document.add(new Paragraph("\nAnotaciones\n"));

        document.add(new Paragraph("\n"));

        PdfPCell anotacionesCelda = new PdfPCell(new Phrase(anotaciones, fuenteDatos));
        anotacionesCelda.setPadding(10);
        anotacionesCelda.setBorderWidth(1);
        anotacionesCelda.setBorderColor(Color.BLACK);
        anotacionesCelda.setMinimumHeight(100);

        PdfPTable tablaAnotaciones = new PdfPTable(1);
        tablaAnotaciones.addCell(anotacionesCelda);

        document.add(tablaAnotaciones);

        response.setHeader("Content-Disposition", "attachment; filename=\"historia_clinica.pdf\"");

    }
}
