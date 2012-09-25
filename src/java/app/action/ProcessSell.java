/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import aa.*;

        

/**
 *
 * @author hanxiang.ng.2010
 */
public class ProcessSell extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true);
            
            String userId = (String) session.getAttribute("userId");
            String stock = request.getParameter("stock").trim();
            session.setAttribute("stock",stock);
            String tempAskPrice = request.getParameter("askprice").trim();
            session.setAttribute("askprice", tempAskPrice);
            int askPrice = Integer.parseInt(tempAskPrice);

            // submit the sell request
            Ask newAsk = new Ask(stock, askPrice, userId);
            //exchangeBean.placeNewAskAndAttemptMatch(newAsk);
            
             response.sendRedirect("sellSuccess.jsp");
            /* TODO output your page here. You may use following sample code. 
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Sell</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessSell at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");  */
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
