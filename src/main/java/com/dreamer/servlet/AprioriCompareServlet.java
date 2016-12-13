package com.dreamer.servlet;

import com.alibaba.fastjson.JSON;
import com.dreamer.aprioricompare.AprioriPerformServiceOnMushroom;
import com.dreamer.sort.PerformData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by linpeng123l on 2016/12/11.
 */
@WebServlet(name = "aprioriCompare",urlPatterns ="/aprioriCompare")
public class AprioriCompareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Writer writer = response.getWriter();
        PerformData performData = AprioriPerformServiceOnMushroom.getSortPerformData();
        writer.write(JSON.toJSON(performData).toString());
        writer.close();
    }
}
