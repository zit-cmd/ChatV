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

@WebServlet(urlPatterns = {"/api-register"})
public class Register extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IUserService userService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("reg");
		register(req, resp);
	}
	
	private void register(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
//			convert json to model
			UserModel insertModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
			insertModel = userService.saveAccount(insertModel);
//			response
			mapper.writeValue(resp.getOutputStream(), insertModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
