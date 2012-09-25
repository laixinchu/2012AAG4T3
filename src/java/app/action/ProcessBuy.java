/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.action;

import aa.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Kel4416
 */
public class ProcessBuy extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessBuy</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessBuy at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        HttpSession session = request.getSession();

        String userId = (String) session.getAttribute("userId");
        String stock = request.getParameter("stock").trim();
        session.setAttribute("stock", stock);
        String tempBidPrice = request.getParameter("bidprice").trim();
        session.setAttribute("bidprice", tempBidPrice);
        int bidPrice = Integer.parseInt(tempBidPrice);

        // submit the buy request
        Bid newBid = new Bid(stock, bidPrice, userId);
        ExchangeBean eb = new ExchangeBean();
        boolean bidIsAccepted = eb.placeNewBidAndAttemptMatch(newBid);

        // forward to either buySuccess or buyFail depending on returned result
        RequestDispatcher rd;
        if (bidIsAccepted) {
            rd = request.getRequestDispatcher("buySuccess.jsp");
            rd.forward(request, response);

        } else {
            rd = request.getRequestDispatcher("buyFail.jsp");
            rd.forward(request, response);
        }


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
