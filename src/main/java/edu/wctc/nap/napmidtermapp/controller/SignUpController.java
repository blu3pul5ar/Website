/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nap.napmidtermapp.controller;

import edu.wctc.nap.napmidtermapp.model.Authorities;
import edu.wctc.nap.napmidtermapp.model.Users;
import edu.wctc.nap.napmidtermapp.service.AnimeService;
import edu.wctc.nap.napmidtermapp.service.AuthoritiesService;
import edu.wctc.nap.napmidtermapp.service.UsersService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import edu.wctc.nap.napmidtermapp.util.simpleRegistrationService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Nicholas
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {
    String dest;
    
    private AuthoritiesService as;
    private UsersService us;
    simpleRegistrationService mailer;
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
                case "newUser":
                    String username = request.getParameter("email");
                    String pass = request.getParameter("password");
                    Users user = new Users();
                    user.setUsername(username);
                    user.setPassword(this.sha512(pass,username));
                    user.setLastUpdate(new Date());
                    user.setVersion(1);
                    user.setEnabled(Boolean.FALSE);
                    us.edit(user);
                    Authorities auth = new Authorities();
                    auth.setAuthoritiesId(null);
                    auth.setUsername(username);
                    auth.setAuthority("ROLE_USER");
                    as.edit(auth);
                    request.setAttribute("msg", "Before you can log in, you must first activate yuor account, please check your email.");
                    mailer.sendMail(username, "Account Activation", "<a href=https://localhost:8181/napMidTermApp/SignUpController?taskType=activate&id="+ username+">Click here to activate your account!</a><br>If you were not the one that requested this account, please disregard this email.");
                    dest = "index.jsp";
                    break;
                case "SignUp":
                    dest = "signUp.jsp";
                    break;
                case "activate":
                    String email = request.getParameter("id");
                    Users user2 = us.findById(email);
                    user2.setEnabled(Boolean.TRUE);
                    us.edit(user2);
                    dest="index.jsp";
                    request.setAttribute("msg", "Your account has been activated, You may now log in.");
                    break;
            }
            RequestDispatcher view = request.getRequestDispatcher(response.encodeURL(dest));
                    view.forward(request, response);
        }
            catch(Exception e){
                System.out.println(e.getMessage());
                    }
                    
    }
@Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        as = (AuthoritiesService) ctx.getBean("authoritiesService");
        us = (UsersService) ctx.getBean("usersService");
        mailer = (simpleRegistrationService) ctx.getBean("mailService");

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
    private String sha512(String pwd, String salt) {

            ShaPasswordEncoder pe = new ShaPasswordEncoder(512);
            pe.setIterations(1024);
            String hash = pe.encodePassword(pwd, salt);

            return hash;
     
    }
}
