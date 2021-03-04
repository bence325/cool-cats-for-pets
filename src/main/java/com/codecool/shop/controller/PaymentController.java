package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//    public Customer(String country, String firstName, String lastName, String address, String city, String postCode, String phoneNumber, String email){
        String country = req.getParameter("country");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String zipCode = req.getParameter("zip_code");
        String phoneNumber = req.getParameter("phone_number");
        String email = req.getParameter("email_address");
        Customer newCustomer = new Customer(country,firstName,lastName,address,city,zipCode,phoneNumber,email);


        ProductDao productDataStore = ProductDaoMem.getInstance();

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Order order = orderDataStore.find((int)session.getAttribute("orderId"));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", productDataStore.getAll());

        engine.process("payment.html", context, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

}