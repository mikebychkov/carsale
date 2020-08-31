package carsale.filter;

import carsale.model.Author;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (uri.endsWith("auth.do") || uri.endsWith("reg.do")) {
            chain.doFilter(sreq, sresp);
            return;
        }
        Author author = (Author) req.getSession().getAttribute("author");
        if (author == null || author.getId() == 0) {
            resp.sendRedirect(req.getContextPath() + "/auth.do");
            return;
        }
        chain.doFilter(sreq, sresp);
    }

    @Override
    public void destroy() {
    }
}

