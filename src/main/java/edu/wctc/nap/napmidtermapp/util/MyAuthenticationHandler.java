/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nap.napmidtermapp.util;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * * * @author jlombardo
 */
public class MyAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        final String USER_URL = request.getServletContext().getContextPath() + "/index.jsp";
        final String ADMIN_URL = request.getServletContext().getContextPath() + "/index.jsp";
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_MGR")) {
            getRedirectStrategy().sendRedirect(request, response, ADMIN_URL);
        } else if (roles.contains("ROLE_USER")) {
            getRedirectStrategy().sendRedirect(request, response, USER_URL);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
    }
}
