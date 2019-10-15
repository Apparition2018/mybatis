package com.imooc.servlet;

import com.imooc.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteBatchServlet
 * <p>
 * 批量删除控制层
 *
 * @author Arsenal
 * created on 2019/8/6 8:48
 */
public class DeleteBatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("UTF-8");
        // 接收页面的值
        String[] ids = req.getParameterValues("id");
        // 查询消息列表并传给页面
        MaintainService maintainService = new MaintainService();
        maintainService.deleteBatch(ids);
        // 向页面跳转
        req.getRequestDispatcher("/List.action").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
