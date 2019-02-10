/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypack.HibernateUtil;
import mypack.pojos.City;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zakhar
 */
@WebServlet(name = "SearchingCity", urlPatterns = {"/SearchingCity"})
public class SearchingCity extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    private String name;
    private String city;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            name = request.getParameter("iname");
            city = request.getParameter("orcity");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            if (city.equals("CITY")) {
                Query q = session.createQuery(""
                        + "FROM City c WHERE c.city=:nameOfCity").setParameter("nameOfCity", name);
                if (q.list().isEmpty() == false) {
                    City myCity = (City) q.list().get(0);

                    out.println(name + " is in: ");
                    out.print(myCity.getCountry().getCountry());
                } else {
                    out.println(name + " doesn't exist in DB");
                }
            } else if (city.equals("COUNTRY")) {
                Query q = session.createQuery("FROM City c WHERE c.country.country=:nameOfCountry")
                        .setParameter("nameOfCountry", name);
                ListIterator<City> listIterator = q.list().listIterator();
                while (listIterator.hasNext()) {
                    out.print(listIterator.next().getCity() + ", ");
                }
                if(!listIterator.hasNext())
                {
                    out.print(name+" doesn't exist in DB");
                }
            }
            tx.commit();
            session.close();

            out.print("<p align =\"center\">"
                    + "           <a href=\"index.xhtml\">home</a>"
                    + "        </p>");
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
