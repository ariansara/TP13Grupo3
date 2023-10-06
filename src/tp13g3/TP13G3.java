/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp13g3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pollo
 */
public class TP13G3 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws SQLException {
        try {
            
            //Cargo Driver de conexión
            Class.forName("org.mariadb.jdbc.Driver");
           
            //Establecer la conexión
            String URL="jdbc:mariadb://localhost:3306/univesidadg3";
            String usuario="root";
            String password="";
            Connection con=DriverManager.getConnection(URL,usuario,password);
//-----------------------------------------------------------------------------------------------------            
            
            String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaDeNacimiento, estado)"
                    
                    + "VALUES (12344, 'Lopez', 'Juan', '2000-01-20', true),"
                    + "       (35488, 'Sosa', 'Maria', '2003-01-18', true),"
                      + "       (85655, 'Ponce', 'Roberto', '1999-08-7', true)";
            
            PreparedStatement ps=con.prepareStatement(sql);
           int filas= ps.executeUpdate();
            if(filas>0){
            
                JOptionPane.showMessageDialog(null, "Alumno Agregado");
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error al cargar Driver");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión");
        
        int error =ex.getErrorCode();
        
            if(error==1146){
                JOptionPane.showMessageDialog(null,"Tabla inexistente");
            }else if(error==1062){
            
                JOptionPane.showMessageDialog(null,"Dni duplicado");
            }else if(error==1049){
            
                JOptionPane.showMessageDialog(null,"BD inexistente");
            }else {
            
                JOptionPane.showMessageDialog(null,"Error SQL");
                
            }
    }
}
}
