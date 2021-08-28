package com.imooc.servlet;

import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AutoReplyServlet
 * <p>
 * 自动回复功能控制层
 *
 * @author Arsenal
 * created on 2019/8/6 9:46
 */
public class AutoReplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置 content-type
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        QueryService queryService = new QueryService();
        // 把结果写到页面
        out.write(queryService.queryByCommand(req.getParameter("content")));
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
