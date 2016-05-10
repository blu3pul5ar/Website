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
@WebServlet(name = "StudioController", urlPatterns = {"/StudioController"})
public class StudioController extends HttpServlet {
private String dbJndiName;
    private static final String STUDIOS = "studios.jsp";
    private static final String STUDIO_EDIT_VIEW = "edit_Studio.jsp";
    private static final String STUDIO_ADD_VIEW = "add_Studio.jsp";
    private static final String HOME = "index.jsp";
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private StudioService ss;
    private AnimeService as;
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
                case "view":
                    request.setAttribute("studios", ss.findAll());
                    dest = STUDIOS;
                    break;
                case "delete":
                    {
                        String Id = (String)request.getParameter("id");
                        Studios studio = ss.findById(Id);
                        ss.remove(studio);
                        this.refreshList(request, ss);
                        dest = STUDIOS;
                        break;
                    }
                case "edit":
                    {
                        String id = (String)request.getParameter("id");
                        Studios studio = ss.findById(id);
                        request.setAttribute("studio", studio);
                        dest = STUDIO_EDIT_VIEW;
                        break;
                    }
                case "add":
                    dest = STUDIO_ADD_VIEW;
                    break;
                case "save":
                    {
                        String studioName = request.getParameter("name");
                        String id = request.getParameter("studioId");
                        Studios studio = ss.findById(id);
                        studio.setStudioName(studioName);
                        ss.edit(studio);
                        this.refreshList(request, ss);
                        dest = STUDIOS;
                        break;
                    }
                case "new":
                    {
                        String studioName = request.getParameter("name");
                        String id = null;
                        Studios studio = new Studios();
                        studio.setStudioName(studioName);
                        studio.setStudioId(null);
                        ss.edit(studio);
                        dest = STUDIOS;
                        this.refreshList(request, ss);
                        break;
                    }
                case "cancel":
                    this.refreshList(request, ss);
                    dest = STUDIOS;
                    break;
                case "color":
                    String table = request.getParameter("showPaletteOnly");
                    String text = request.getParameter("showPaletteOnly1");
                    HttpSession session = request.getSession();
                    session.setAttribute("table",table);
                    session.setAttribute("text",text);
                    request.setAttribute("studios", ss.findAll());
                    dest = STUDIOS;
                    break;
                default:
                    dest = HOME;
                    break;
            }
           }catch(Exception e){
                request.setAttribute(STUDIOS, e);
           }
                RequestDispatcher view = request.getRequestDispatcher(response.encodeURL(dest));
                view.forward(request, response);   
    }
    private void refreshList(HttpServletRequest request,StudioService service) throws Exception {
        List<Studios> studios = service.findAll();
        request.setAttribute("studios", studios);
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
        ss = (StudioService) ctx.getBean("studioService");
        as = (AnimeService) ctx.getBean("animeService");


    }
}
