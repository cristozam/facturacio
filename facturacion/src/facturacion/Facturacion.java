package facturacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Facturacion extends JFrame {

    private JTextField nombreClienteField;
    private JTextField cedulaClienteField;
    private JTextField direccionClienteField;
    private JTextField telefonoClienteField;
    private JTextField correoClienteField;
    private JTable table;
    private JTextField productoField;
    private DefaultTableModel tableModel;
    private JButton buscarclienteboton;
    private JButton buscarProducto;
    private cliente currentCliente;
    private Producto currentproduct;
    private JTextField cantidadField;

    public Facturacion() {
        setTitle("Sistema de facturación clientes");
        setSize(2000, 1500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        int cantidade = 0;

        
        Font font = new Font("Arial", Font.PLAIN, 14);

        
        JLabel nombreLabel = new JLabel("Nombre ");
        nombreLabel.setFont(font);
        nombreLabel.setBounds(20, 20, 200, 30);

        
        nombreClienteField = new JTextField();
        nombreClienteField.setFont(font);
        nombreClienteField.setBounds(220, 20, 200, 30); // Posición y tamaño en píxeles

        
        JLabel cedulaLabel = new JLabel("Cédula ");
        cedulaLabel.setFont(font);
        cedulaLabel.setBounds(20, 70, 200, 30);

        
        cedulaClienteField = new JTextField();
        cedulaClienteField.setFont(font);
        cedulaClienteField.setBounds(220, 70, 200, 30);

        
        JLabel direccionLabel = new JLabel("Dirección ");
        direccionLabel.setFont(font);
        direccionLabel.setBounds(20, 120, 200, 30);

        
        direccionClienteField = new JTextField();
        direccionClienteField.setFont(font);
        direccionClienteField.setBounds(220, 120, 200, 30);

        
        JLabel telefonoLabel = new JLabel("Teléfono ");
        telefonoLabel.setFont(font);
        telefonoLabel.setBounds(20, 170, 200, 30);

        
        telefonoClienteField = new JTextField();
        telefonoClienteField.setFont(font);
        telefonoClienteField.setBounds(220, 170, 200, 30);

        
        JLabel correoLabel = new JLabel("Correo Electrónico ");
        correoLabel.setFont(font);
        correoLabel.setBounds(20, 220, 200, 30);
        
        correoClienteField = new JTextField();
        correoClienteField.setFont(font);
        correoClienteField.setBounds(220, 220, 200, 30);
        

        JLabel productolabel = new JLabel("Producto:  ");
        productolabel.setFont(font);
        productolabel.setBounds(20, 270, 200, 30);

        
        productoField = new JTextField();
        productoField.setFont(font);
        productoField.setBounds(220, 270, 200, 30);
        
        
        

        JLabel cantidadLabel = new JLabel("cantidad");
        cantidadLabel.setFont(font);
        cantidadLabel.setBounds(20,320,200,30);
        
        
        cantidadField = new JTextField();
        cantidadField.setFont(font);
        cantidadField.setBounds(220, 320, 200, 30);
        

        
        String[] columnNames = {"Num Producto", "Producto", "Precio", "Iva", "Precio con Iva"};
        tableModel = new DefaultTableModel(columnNames, 0);

        
        table = new JTable(tableModel);
        table.setFont(font);
        table.setBounds(20, 370, 740, 200);
        table.getTableHeader().setFont(font);

        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(500, 50, 700, 500);
        
       
        buscarclienteboton = new JButton("Buscar cliente");
        buscarclienteboton.setFont(font);
        buscarclienteboton.setBounds(80, 370, 300, 30);

        buscarProducto = new JButton("Agregar Producto");
        buscarProducto.setFont(font);
        buscarProducto.setBounds(80, 420, 300, 30);
        

        
        

        buscarclienteboton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear_cliente();

                if (currentCliente.comprobarcliente() == true) {
                    JOptionPane.showMessageDialog(null, "Se encontró el cliente: \n" + currentCliente);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encuentra el cliente: \n");
                    
                }
            }
        });
        
        buscarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(cantidadField.getText()); 
                if(currentproduct.ComprobarProducto(cantidad) == true && currentCliente.comprobarcliente() == true){
                    Object[] rowdata = {tableModel.getRowCount()+ 1,currentproduct.getNumeroproducto(),currentproduct.getDescripcion(),currentproduct.getProPrecio(),currentproduct.getIva()};
                    tableModel.addRow(rowdata);
                }else {
                    JOptionPane.showMessageDialog(null, "No se encontro el producto o el cliente: \n" );
                }
                
            }
        });

        add(nombreLabel);
        add(nombreClienteField);
        add(cedulaLabel);
        add(cedulaClienteField);
        add(direccionLabel);
        add(direccionClienteField);
        add(telefonoLabel);
        add(telefonoClienteField);
        add(correoLabel);
        add(correoClienteField);
        add(productolabel);
        add(productoField);
        add(buscarclienteboton);
        add(buscarProducto);
        add(scrollPane);
        add(cantidadField);
        add(cantidadLabel);

        setVisible(true);
    }
    public void obtener_datos(){
        String codigo = null;
        String descripcion = null;
        int cantidad = 0;
        double precio = 0;
        int iva = 0;
        
        try (Connection conn = conexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            while (rs.next()) {

                codigo = rs.getString("PROCODIGO");
                descripcion = rs.getString("PRODESCRIPCION");
                cantidad = rs.getInt("PROSALDOFINAL");
                precio = rs.getDouble("PROPRECIOUM");
                iva = rs.getInt("IVA");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
    }
    

    
    public void crear_cliente() {
        String nombre = nombreClienteField.getText();
        String cedula = cedulaClienteField.getText();
        String direccion = direccionClienteField.getText();
        String telefono = telefonoClienteField.getText();
        String correo = correoClienteField.getText();

        currentCliente = new cliente(nombre, cedula, direccion, telefono, correo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JFactura().setVisible(true);
        });
    }
}
