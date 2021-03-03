package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductId;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
public class AddToCart extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductId obj = new Gson().fromJson(req.getReader(), ProductId.class);

        HttpSession session = req.getSession();

        if (session == null) {
            List<int> productIds = new ArrayList<String>();
        }

        productIds.add("Test1");
        productIds.add("Test2");

        HttpSession session = req.getSession();
        session.setAtrribute("Avr", names);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("id"));
    }
}
