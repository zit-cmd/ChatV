package com.vit.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vit.model.UserModel;
import com.vit.service.IUserService;
import com.vit.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-login"})
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject
	private IUserService userService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login");
		login(req, resp);
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
//			convert json to model
			UserModel model = HttpUtil.of(req.getReader()).toModel(UserModel.class);
			model = userService.findAccount(model);
//			response
			mapper.writeValue(resp.getOutputStream(), model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
