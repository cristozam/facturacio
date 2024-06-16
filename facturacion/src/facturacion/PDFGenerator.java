/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facturacion;

/**
 *
 * @author User
 */

import facturacion.PDFGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.table.DefaultTableModel;

public class PDFGenerator {

    public static void generarPDF(String numeroFactura, cliente currentCliente, DefaultTableModel modelo, double acumuladorbruto, double acumuladoriva, double acumulador, String formaPago) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("factura_" + numeroFactura + ".pdf"));
            document.open();
            document.add(new Paragraph("Factura Número: " + numeroFactura));
            document.add(new Paragraph("Cliente: " + currentCliente.getNombre()));
            document.add(new Paragraph("Cédula: " + currentCliente.getCedula()));
            document.add(new Paragraph("Dirección: " + currentCliente.getDireccion()));
            document.add(new Paragraph("Teléfono: " + currentCliente.getTelefono()));
            document.add(new Paragraph("Correo: " + currentCliente.getCorreo()));
            document.add(new Paragraph("Forma de Pago: " + formaPago));
            document.add(new Paragraph("Fecha: " + new java.util.Date()));

            PdfPTable table = new PdfPTable(5);
            table.addCell("Num Producto");
            table.addCell("Producto");
            table.addCell("Precio");
            table.addCell("Iva");
            table.addCell("Precio con Iva");

            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    table.addCell(modelo.getValueAt(i, j).toString());
                }
            }

            document.add(table);
            document.add(new Paragraph("Subtotal: " + acumuladorbruto));
            document.add(new Paragraph("IVA: " + acumuladoriva));
            document.add(new Paragraph("Total: " + acumulador));

            document.close();
            JOptionPane.showMessageDialog(null, "PDF generado exitosamente");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }
}

