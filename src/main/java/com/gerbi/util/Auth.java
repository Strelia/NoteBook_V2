package com.gerbi.util;


import com.gerbi.model.User;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Auth {


    public static boolean isLoged(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if ("true".equals(getValueCookie(request, "edocLoged"))) {
                return true;
            } else {
                return false;
            }
        } else {

            return false;
        }
    }

    public static void login(HttpServletResponse response, User user, Boolean saveMe) {
        System.out.println(user.getUserRole());
        System.out.println(user.getEmail());


        Cookie edocUserRole = new Cookie("edocUserRole", user.getUserRole());
        Cookie edocUserEmail = new Cookie("edocUserEmail", user.getEmail());
        Cookie edocLogged = new Cookie("edocLoged", "true");

        if (saveMe) {
            edocLogged.setMaxAge(24 * 60 * 60);
            edocUserEmail.setMaxAge(24 * 60 * 60);
            edocUserRole.setMaxAge(24 * 60 * 60);
        } else {
            edocLogged.setMaxAge(60 * 60);
            edocUserEmail.setMaxAge(60 * 60);
            edocUserRole.setMaxAge(60 * 60);
        }

        response.addCookie(edocUserEmail);
        response.addCookie(edocUserRole);
        response.addCookie(edocLogged);
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public static String getValueCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }


}
