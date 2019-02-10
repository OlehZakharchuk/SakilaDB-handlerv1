/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypack.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zakhar Here i use hibernate on Servlet to show some fields of Film
 * table, using HSQL
 *
 */
@WebServlet(name = "ShowingFilms", urlPatterns = {"/ShowingFilms"})
public class ShowingFilms extends HttpServlet {

    String str = null;

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
        response.setContentType("text/html;charset=UTF-8");

        /* TODO output your page here. You may use following sample code. */
        str += "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Servlet ShowingData</title>"
                +"<link rel =\"stylesheet\" type=\"text/css\" href=\"resources/css/myTableStyle.css\"/>"
                + "</head>"
                + "<body>"
                +"<form >"
                + "<p align =\"center\">" +
"           <a href=\"index.xhtml\">home</a>" +
"        </p>"
                +"</form>"
                + "<table>"
                + "<thead>"
                + "<tr>"
                + "<td>Id</td>"
                + "<td>Name</td>"
                + "<td>Language</td>"
                + "</tr>"
                + "</thead>";
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("SELECT f.filmId, f.title, f.languageByLanguageId.name "
                + "FROM Film f, Language l "
                + "WHERE f.languageByLanguageId.languageId=l.languageId");
        
        List<Object[]> li = q.list();
        
        for( Object [] arr : li){
        str+= "<tr>"
                + "<td>"+arr[0]+"</td>"
                + "<td>"+arr[1]+"</td>"
                + "<td>"+arr[2]+"</td>"
                + "</tr>";
        }
        tx.commit();
        session.close();

        str += "</table>"
                + "</body>"
                + "</html>";
        try (PrintWriter out = response.getWriter()) {
            out.println(str);
        } catch (Exception e) {
            System.err.println("Nie moge wypisac");
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
