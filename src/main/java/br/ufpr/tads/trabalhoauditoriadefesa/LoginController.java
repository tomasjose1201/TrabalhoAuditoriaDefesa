package br.ufpr.tads.trabalhoauditoria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tom
 */
@WebServlet(urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email"); 
        String senha = request.getParameter("senha");//    s'OR 1=1;-- 
        ArrayList<Usuario> result = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bem vindo!</h1>");
            out.println("<h2>Lista de usuários encontrados: </h2>");
            result = new ArrayList();
            result = buscarUsuario(email, senha);
            if (result.isEmpty()) {
                out.println("<h3>Nenhum usuário encontrado com os dados fornecidos!</h3>");
            } else {
                for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                    Usuario u = (Usuario) iterator.next();
                    out.println("<h3> > Email: " + u.getEmail()
                            + "<br> > Senha: " + u.getSenha() + "</h3><br>");
                }
            }
            out.println("<a href=\"index.jsp\">Voltar</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private ArrayList<Usuario> buscarUsuario(String email, String senha) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        //A diferença neste projeto é o uso de PreparedStatement
        PreparedStatement stmt = null;
        ArrayList<Usuario> users = null;
        try {
            String query = "select * from usuario where email = ? and senha = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            users = new ArrayList();
            while (rs.next()) {
                Usuario novo = new Usuario();
                novo.setEmail(rs.getString("email"));
                novo.setSenha(rs.getString("senha"));
                users.add(novo);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
