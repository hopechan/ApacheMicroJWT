/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicroweb.frontend.ui;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ues.edu.sv.tpi135.apachemicroweb.frontend.ui.utils.SessionUtils;

/**
 *
 * @author esperanza
 */
@ManagedBean
@ViewScoped
public class LoginBean {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String doLogin() throws Exception {
        HttpServletRequest request = SessionUtils.getRequest();

        // do login
        try {
            request.logout();
            request.login(this.username, this.password);
        } catch (ServletException e) {
            System.out.println("Login failed.");
            return "error.jsf";
        }

        // to get remote user using getRemoteUser()
        String remoteUser = request.getRemoteUser();
        Set<String> roles = getRoles(request);


        // update session
        if (remoteUser != null && remoteUser.equals(username)){

            String jwt = buildJwt(username, roles);
            // get the current session
            HttpSession ses = request.getSession();
            if (ses == null) {
                System.out.println("Session is timeout.");
            } else {
                ses.setAttribute("jwt", jwt);
            }
        } else {
            System.out.println("Update Sessional JWT Failed.");
        }
        return "application.jsf";
    }

  private String buildJwt(String userName, Set<String> roles) throws Exception {
        return JwtBuilder.create("jwtFrontEndBuilder")
                         .claim(Claims.SUBJECT, userName)
                         .claim("upn", userName) // MP-JWT defined subject claim
                         .claim("groups", roles.toArray(new String[roles.size()])) // MP-JWT builds an array from this
                         .claim("customClaim", "customValue")
                         .buildJwt()
                         .compact();
    }

    private Set<String> getRoles(HttpServletRequest request) {
        Set<String> roles = new HashSet<String>();
        boolean isAdmin = request.isUserInRole("admin");
        boolean isUser = request.isUserInRole("user");
        if (isAdmin) { roles.add("admin");}
        if (isUser) { roles.add("user");}
        return roles;
    }
}
