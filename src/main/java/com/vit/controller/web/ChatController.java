package com.vit.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/message"})
public class ChatController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String strId = req.getParameter("id");
		String name = req.getParameter("name");
		if (strId != null && type.equals("m")) {
			req.setAttribute("FRIEND", strId);
			req.setAttribute("NAMEF", name);
			RequestDispatcher rd = req.getRequestDispatcher("/view/chat.jsp");
			rd.forward(req, resp);
		}
	}
	
}
