/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nap.napmidtermapp.controller;


import edu.wctc.nap.napmidtermapp.model.Animes;
import edu.wctc.nap.napmidtermapp.model.Studios;
import edu.wctc.nap.napmidtermapp.service.AnimeService;
import edu.wctc.nap.napmidtermapp.service.StudioService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Nicholas
 */
@WebServlet(name = "AnimeController", urlPatterns = {"/AnimeController"})
public class AnimeController extends HttpServlet {
private String dbJndiName;
    private static final String ANIMES = "Anime.jsp";
    private static final String ANIME_EDIT_VIEW = "edit.jsp";
    private static final String ANIME_ADD_VIEW = "add.jsp";
    private static final String HOME = "index.jsp";
    private static final String ANIMEBYSTUDIO = "AnimeByStudio.jsp";
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private AnimeService as;
    private StudioService ss;
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
      String dest = "";
            String taskType = request.getParameter("taskType");
        try{
            switch (taskType) {
                case "viewAnime":
                    request.setAttribute("animes", as.findAll());
                    dest = ANIMES;
                    break;
                case "deleteAnime":
                    {
                        String animeId = (String)request.getParameter("id");
                        Animes anime = as.findById(animeId);
                        as.remove(anime);
                        this.refreshList(request, as);
                        dest = ANIMES;
                        break;
                    }
                case "edit":
                    {
                        String animeId = (String)request.getParameter("id");
                        Animes anime = as.findById(animeId);
                        request.setAttribute("anime", anime);
                        request.setAttribute("dropDownStudios", ss.findAll());
                        this.refreshtudioList(request, ss);
                        this.refreshList(request, as);
                        dest = ANIME_EDIT_VIEW;
                        break;
                    }
                case "add":
                    dest = ANIME_ADD_VIEW;
                    request.setAttribute("dropDownStudios", ss.findAll());
                    this.refreshtudioList(request, ss);
                    this.refreshList(request, as);
                    break;
                case "save":
                    {
                        String animeName = request.getParameter("animeName");
                        String animeId = request.getParameter("animeId");
                        String animeCategory = request.getParameter("animeCategory");
                        String price = request.getParameter("price");
                        String image = request.getParameter("image");
                        String stock = request.getParameter("stock");
                        String studioId = request.getParameter("studioId");
                        Studios studio = ss.findById(studioId);
                        double price2 = Double.parseDouble(price);
                        Animes anime = as.findById(animeId);
                        anime.setProductCategory(animeCategory);
                        anime.setProductImage(image);
                        anime.setProductPrice(price2);
                        anime.setProductName(animeName);
                        anime.setProductStock(Integer.parseInt(stock));
                        anime.setProductStudio(studio);
                        as.edit(anime);
                        this.refreshList(request, as);
                        dest = ANIMES;
                        break;
                    }
                case "new":
                    {
                       String animeName = request.getParameter("animeName");
                        String animeCategory = request.getParameter("animeCategory");
                        String price = request.getParameter("price");
                        String image = request.getParameter("image");
                        String stock = request.getParameter("stock");
                        String studioId = request.getParameter("studioId");
                        Studios studio = ss.findById(studioId);
                        double price2 = Double.parseDouble(price);
                        Animes anime = new Animes();
                        anime.setProductName(animeName);
                        anime.setProductImage(image);
                        anime.setProductPrice(price2);
                        anime.setProductId(null);
                        anime.setProductStock(10);
                        anime.setProductStudio(studio);
                        anime.setProductCategory(animeCategory);
                        as.edit(anime);
                        dest = ANIMES;
                        this.refreshList(request, as);
                        break;
                    }
                case "cancel":
                    this.refreshList(request, as);
                    dest = ANIMES;
                    break;
                case "color":
                    String table = request.getParameter("showPaletteOnly");
                    String text = request.getParameter("showPaletteOnly1");
                    HttpSession session = request.getSession();
                    session.setAttribute("table",table);
                    session.setAttribute("text",text);
                    request.setAttribute("animes", as.findAll());
                    dest = ANIMES;
                    break;
                case "viewByStudio":
                    String studioId = (String)request.getParameter("id");
                    request.setAttribute("animes", as.findAllByStudio(Integer.parseInt(studioId)));
                    dest = ANIMEBYSTUDIO;
                    break;
                default:
                    dest = HOME;
                    break;
            }
           }catch(Exception e){
                request.setAttribute(ANIMES, e);
           }
                RequestDispatcher view = request.getRequestDispatcher(response.encodeURL(dest));
                view.forward(request, response);   
    }
    private void refreshList(HttpServletRequest request,AnimeService animeService) throws Exception {
        List<Animes> animes = animeService.findAll();
        request.setAttribute("animes", animes);
    }
    
    private void refreshtudioList(HttpServletRequest request,StudioService StudioService) throws Exception {
        List<Studios> studio = StudioService.findAll();
        request.setAttribute("studios", studio);
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
        ss = (StudioService) ctx.getBean("studioService");

    }
}
