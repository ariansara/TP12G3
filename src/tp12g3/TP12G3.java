/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp12g3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author pollo
 */
public class TP12G3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Cargo Driver de conexión
            Class.forName("org.mariadb.jdbc.Driver");

            //Establecer la conexión
            String URL = "jdbc:mariadb://localhost:3306/construirsa";
            String usuario = "root";
            String password = "";

            Connection con = DriverManager.getConnection(URL, usuario, password);
            
//---------------------------------------------------------------------------------------------------------------  
            
//            String sql ="INSERT INTO empleado(id_empleado, dni, apellido, nombre, acceso, estado) "
//                    + " VALUES             ('4',          '42858741','Salas','Ramon','5','1'),"
//                    + "                    ('5',          '43568741','Uchiha','Sasuke','3','1'),"
//                    + "                    ('6',          '42566481','Monky','Lufy','2','1')";
 
//-----------------------------------------------------------------------------------------------------------------

//            String sql = "INSERT INTO `herramienta`(`id_herramienta`, `nombre`, `descripcion`, `stock`, `estado`) "
//                       + " VALUES                  ('6',             'Motosierra','Oregon','8','1'),"
//                       + "                         ('7',             'Martillo demoledor','Makita','6','1')";
            
//--------------------------------------------------------------------------------------------------------------------  
//
//            String sql = "SELECT id_herramienta, nombre, descripcion, stock, estado "
//                        +" FROM herramienta "
//                        +" WHERE stock > 10";
//             PreparedStatement ps = con.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                
//                String nombre = rs.getString("nombre");
//                String descripcion = rs.getString("descripcion");
//                int stock = rs.getInt("stock");
//                boolean estado = rs.getBoolean("estado");
//
//                System.out.println("---------------------------------------------------------------------------");
//                System.out.println("Nombre: " + nombre );
//                System.out.println("Descripcion: " + descripcion);
//                System.out.println("stock: " + stock);
//                System.out.println("estado: " + estado);
//                System.out.println("---------------------------------------------------------------------------");
//            }
//-------------------------------------------------------------------------------------------------------------------------

               String sql = "  UPDATE `empleado` SET `estado`='0'"
                       + "WHERE empleado.id_empleado = 1";

            PreparedStatement ps=con.prepareStatement(sql);
            int filas = ps.executeUpdate();
            if (filas > 0) {

                JOptionPane.showMessageDialog(null, "instruccion aprobada");
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar Driver");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión");

            int error = ex.getErrorCode();

            if (error == 1146) {
                JOptionPane.showMessageDialog(null, "Tabla inexistente");
            } else if (error == 1062) {

                JOptionPane.showMessageDialog(null, "Dni duplicado");
            } else if (error == 1049) {

                JOptionPane.showMessageDialog(null, "BD inexistente");
            } else {

                JOptionPane.showMessageDialog(null, "Error SQL");

            }
        }
    }
}
