/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facturacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class factura {
    private int numerofactura;
    private Date fechafactura;
    private int total;

    public factura(int numerofactura, Date fechafactura, int total) {
        this.numerofactura = numerofactura;
        this.fechafactura = fechafactura;
        this.total = total;
    }

    public int getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(int numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    private void generarNumeroFactura() {
        try (Connection conn = conexionBD.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT ultimoNumero FROM FacturaNumero WHERE id = 1");
            if (rs.next()) {
                int ultimoNumero = rs.getInt("ultimoNumero");
                int nuevoNumero = ultimoNumero + 1;
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    
}
