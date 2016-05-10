/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nap.napmidtermapp.controller;

import edu.wctc.nap.napmidtermapp.model.Animes;
import edu.wctc.nap.napmidtermapp.service.AnimeService;
import edu.wctc.nap.napmidtermapp.service.StudioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Nicholas
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/ShoppingCartController"})
public class ShoppingCartController extends HttpServlet {
        List<Animes> cart = new ArrayList<>();
        String dest;
        double total;
        private AnimeService as;
    public ShoppingCartController() {
    }
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
             String taskType = request.getParameter("taskType");
             HttpSession session = request.getSession();
        try{
            switch (taskType) {
                case "AddToCart":
                    String id = (String)request.getParameter("id");
                    cart.add(as.findById(id));
                    total += as.findById(id).getProductPrice();
                    session.setAttribute("total", total);
                    session.setAttribute("cart",cart);
                    dest="Anime.jsp";
                    this.refreshList(request, as);
                    break;
                case "getCart":
                    dest = "cart.jsp";
                    this.refreshList(request, as);
                    break;
                case "Remove":
                    String id2 = (String)request.getParameter("id");
                    cart.remove(as.findById(id2));
                    total -= as.findById(id2).getProductPrice();
                    session.setAttribute("total", total);
                    session.setAttribute("cart",cart);
                    dest="cart.jsp";
                    this.refreshList(request, as);
                    break;
            }
            RequestDispatcher view = request.getRequestDispatcher(response.encodeURL(dest));
                    view.forward(request, response);
        }
            catch(Exception e){
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
 @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        as = (AnimeService) ctx.getBean("animeService");

    }
    private void refreshList(HttpServletRequest request,AnimeService animeService) throws Exception {
        List<Animes> animes = animeService.findAll();
        request.setAttribute("animes", animes);
    }
}
