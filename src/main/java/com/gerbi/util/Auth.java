package com.gerbi.util;


import com.gerbi.model.User;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Auth {


    public static boolean isLoged(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Boolean checkLoger = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("edocLoged")) {
                checkLoger = Boolean.valueOf(cookie.getValue());
                break;
            }
        }
        return checkLoger;
    }

    public static void login(HttpServletResponse response, User user, Boolean saveMe) {
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
