/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp13g3;

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
public class TP13G3 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws SQLException {
        try {

            //Cargo Driver de conexión
            Class.forName("org.mariadb.jdbc.Driver");

            //Establecer la conexión
            String URL = "jdbc:mariadb://localhost:3306/univesidadg3";
            String usuario = "root";
            String password = "";
            Connection con = DriverManager.getConnection(URL, usuario, password);
//-----------------------------------------------------------------------------------------------------            

//            String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaDeNacimiento, estado)"
//                    
//                    + "VALUES (12344, 'Lopez', 'Juan', '2000-01-20', true),"
//                    + "       (35488, 'Sosa', 'Maria', '2003-01-18', true),"
//                      + "       (85655, 'Ponce', 'Roberto', '1999-08-7', true)";
//               String sql = "INSERT INTO materia (idmateria,nombre,anio,estado)"
//                         +"VALUES (4,'naturales',2,true)";
//                       +"         (1,'Lengua',1,true),"
//                       +"         (2,'matematicas',3,true),"
//                       +"         (3,'ingles',2,true)";
//                       
//                String sql = "INSERT INTO inscripcion(idInscripto, nota, idAlumno, idMateria)"
//                        +  "VALUES (1,9,14,4),"
//                        +  "       (2,8,15,1),"
//                       +  "       (3,7,16,3),"
//                         +  "       (4,7,14,1),"
//                         +  "       (5,9,15,4),"
//                         +  "       (6,5,16,2)";
//            PreparedStatement ps = con.prepareStatement(sql);

            String sql ="SELECT DISTINCT alumno. *,materia.nombre FROM alumno JOIN inscripcion on (alumno.idAlumno = inscripcion.idAlumno)"
            + "JOIN materia ON(materia.idMateria = inscripcion.idMateria)"
           + "where inscripcion.nota > 8";
           PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

           while (rs.next()){
                int id = rs.getInt("idAlumno");
                int dni =rs.getInt("dni");
                String apellido=rs.getString("apellido");
                String nombre =rs.getString("nombre");
                LocalDate fechN =rs.getDate("fechaDeNacimiento").toLocalDate();
                boolean estado = rs.getBoolean("estado");
                System.out.println("Nombre: "+nombre+apellido);
                System.out.println("id: "+id);
                System.out.println("dni: "+dni);
                System.out.println("fecha; : "+fechN.toString());
                System.out.println("estado: "+estado);
           }
            
            
//            int filas = ps.executeUpdate();
//                if (filas > 0) {
//                    JOptionPane.showMessageDialog(null, "datos Agregados");
//                }
            }catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar Driver");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión");

            int error = ex.getErrorCode();
ex.printStackTrace();
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
