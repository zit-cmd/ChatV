package com.vit.controller.web;

import java.io.IOException;
import java.io.Serial;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vit.model.UserModel;
import com.vit.service.ICallAPI;
import com.vit.utils.FormUtil;
import com.vit.utils.SessionUtil;

@WebServlet(urlPatterns = {"/home", "/dang-nhap", "/dang-ky", "/dang-xuat"})
public class HomeController extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	@Inject
	private ICallAPI callApi;
	private final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String alert = req.getParameter("alert");
		String message = req.getParameter("message");
		if (message != null && alert != null) {
			req.setAttribute("message", resourceBundle.getString(message));
			req.setAttribute("alert", alert);
		}
		if (action != null && action.equals("login")) {
			RequestDispatcher rd = req.getRequestDispatcher("/view/login.jsp");
			rd.forward(req, resp);
		} else if (action != null && action.equals("reg")) {
			RequestDispatcher rd = req.getRequestDispatcher("/view/register.jsp");
			rd.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USER");
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/view/home.jsp");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String action = req.getParameter("action");
		UserModel user = FormUtil.toModel(UserModel.class, req);
		if (action != null && action.equals("login")) {
			user = callApi.login(user);
			if (user != null) {
				SessionUtil.getInstance().putValue(req, "USER", user);
				resp.sendRedirect(req.getContextPath() + "/home");
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=login_invalid&alert=danger");
			}
		} else if (action != null && action.equals("reg")) {
			user = callApi.register(user);
			if (user != null) {
				resp.sendRedirect(req.getContextPath() + "/dang-ky?action=reg&message=register_valid&alert=success");
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-ky?action=reg&message=register_invalid&alert=danger");
			}
		} else {
			
		}
	}
	
}
