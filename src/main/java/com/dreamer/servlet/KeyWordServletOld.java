package com.dreamer.servlet;

import com.alibaba.fastjson.JSON;
import com.dreamer.domain.KeyWordCountOld;
import com.dreamer.serverold.KeyWordsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by hws on 16/8/26.
 */
public class KeyWordServletOld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        Writer writer = resp.getWriter();
        List<KeyWordCountOld> keyWordCounts = KeyWordsService.keywords();
        writer.write(JSON.toJSON(keyWordCounts).toString());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
