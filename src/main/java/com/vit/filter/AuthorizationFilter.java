package com.vit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vit.model.UserModel;
import com.vit.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();
        UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USER");
        if (url.endsWith("/home") || url.contains("/message")) {
            if (model != null) {
            	chain.doFilter(request, response);
            } else {
            	resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
            }
        } else if (url.endsWith("/dang-nhap")) {
        	if (model != null) {
        		resp.sendRedirect(req.getContextPath() + "/home");
        	} else {
        		chain.doFilter(request, response);
        	}
        } else {
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {}
	
}
