
package facturacion;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class cliente {
    private String nombre;
    private String cedula;
    private String telefono; 
    private String direccion;
    private String Correoelectronico;

    public cliente(String nombre, String cedula, String telefono, String direccion, String Correoelectronico) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.Correoelectronico = Correoelectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoelectronico() {
        return Correoelectronico;
    }

    public void setCorreoelectronico(String Correoelectronico) {
        this.Correoelectronico = Correoelectronico;
    }
    
    public boolean comprobarcliente() {
        String nombrec = null;
        try (Connection conn = conexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {
                String codigo = rs.getString("CLICODIGO");
                nombrec = rs.getString("CLINOMBRE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        if (this.nombre.equals(nombrec)) {
            return true;
        } else {
            return false;
        }
    }


    
    
}
