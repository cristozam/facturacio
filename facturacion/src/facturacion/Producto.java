/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facturacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class Producto {
    private String numeroproducto;
    private String Descripcion;
    private String Unidadmedida;
    private int proSaldoIni;
    private int proingreso;
    private int proegreso;
    private int proajuste;
    private int prosaldofinal;
    private double procosto;
    private double proPrecio;
    private String proestatus;
    private double iva;

    public Producto(String numeroproducto) {
        this.numeroproducto = numeroproducto;
    }



    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public int getProSaldoIni() {
        return proSaldoIni;
    }

    public void setProSaldoIni(int proSaldoIni) {
        this.proSaldoIni = proSaldoIni;
    }

    public int getProegreso() {
        return proegreso;
    }

    public void setProegreso(int proegreso) {
        this.proegreso = proegreso;
    }

    public int getProajuste() {
        return proajuste;
    }

    public void setProajuste(int proajuste) {
        this.proajuste = proajuste;
    }

    public int getProsaldofinal() {
        return prosaldofinal;
    }

    public void setProsaldofinal(int prosaldofinal) {
        this.prosaldofinal = prosaldofinal;
    }

    public double getProcosto() {
        return procosto;
    }

    public void setProcosto(double procosto) {
        this.procosto = procosto;
    }


    public String getNumeroproducto() {
        return numeroproducto;
    }

    public void setNumeroproducto(String numeroproducto) {
        this.numeroproducto = numeroproducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getProPrecio() {
        return proPrecio;
    }

    public void setProPrecio(double proPrecio) {
        this.proPrecio = proPrecio;
    }
    

    public void setear(){
        String codigo = null;
        int cantidad = 0;
        
        try (Connection conn = conexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {
               
                codigo = rs.getString("PROCODIGO");
                cantidad = rs.getInt("PROSALDOFINAL");
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        
    }
    public boolean ComprobarProducto(int cantidadne){
        String codigo = null;
        int cantidad = 0;
        try (Connection conn = conexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {
               
                codigo = rs.getString("PROCODIGO");
                cantidad = rs.getInt("PROSALDOFINAL");
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        
       
        if (this.numeroproducto.equals(codigo) && cantidad >= cantidadne) {
            return true;
        } else {
            return false;
        }
    }
    public double sumador(double precio,double iva){
        double total;
        total = precio + (precio*iva);
        return total;
                
    }
  
    
    

    
    
}
