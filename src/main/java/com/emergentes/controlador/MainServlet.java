package com.emergentes.controlador;

import com.emergentes.modelo.Contacto;
import com.mysql.jdbc.Driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Driver es la ruta de la clase Driver*/
        String driver = "com.mysql.jdbc.Driver";

        /*Para hacer la conexion*/
        String url = "jdbc:mysql://localhost:3306/bd_agenda";
        String usuario = "root";
        String password = "";

        String sql = "SELECT * FROM CONTACTOS";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<Contacto> listaDeContactos = new ArrayList<>();
        
        try {
            
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, password);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Contacto contactoNuevo = new Contacto();
                
                contactoNuevo.setId(resultSet.getInt("id"));
                contactoNuevo.setCorreo(resultSet.getString("correo"));
                contactoNuevo.setNombre(resultSet.getString("nombre"));
                contactoNuevo.setTelefono(resultSet.getString("telefono"));
                
                listaDeContactos.add(contactoNuevo);
                
            }
            
            request.setAttribute("lista", listaDeContactos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
            
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.getMessage());

        } catch (ClassNotFoundException e) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
