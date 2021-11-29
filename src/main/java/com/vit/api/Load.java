package com.vit.api;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vit.model.FriendModel;
import com.vit.service.IFriendService;
import com.vit.service.IMessageService;

@WebServlet(urlPatterns = {"/api-load"})
public class Load extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject
	private IFriendService friendService;
	@Inject
	private IMessageService messageService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strUser = req.getParameter("sender");
		Long user = null, friend = null;
		if (strUser != null) {
			user = Long.valueOf(strUser);
		}
		String strFriend = req.getParameter("receiver");
		if (strFriend != null) {
			friend = Long.valueOf(strFriend);
		}
		ObjectMapper mapper = new ObjectMapper();
		if (user != null && friend == null) {
			List<FriendModel> friends = friendService.findListFriends(user);
//			response
			mapper.writeValue(resp.getOutputStream(), friends);
		} else if (user != null && friend != null) {
			FriendModel fr = new FriendModel();
//			tìm thông tin của friend
			fr = friendService.findOne(user, friend);
			fr.setMessage(messageService.findTwelveMessage(user, friend));
			mapper.writeValue(resp.getOutputStream(), fr);
		}
		
	}
	
}
