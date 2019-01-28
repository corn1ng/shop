package com.netease.shop;

import com.netease.shop.Entity.User;

import javax.servlet.http.HttpServletRequest;

public class CommonMethod {

    public static User getssion(HttpServletRequest httpServletRequest)
    {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return user;
    }
}
