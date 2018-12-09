package com.isa.web;


import com.isa.freemaker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/infoShareAcademy")
public class infoShareAcademyServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "infoshareacademy";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");
        String name = "Karol Bemka";
        String team = "jjdd5 - JBusters";

        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        model.put("team", team);
        model.put("time", LocalDateTime.now());

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");

        Map<String, String[]> allMap = req.getParameterMap();
        for (String key : allMap.keySet()) {
            String[] strArr = allMap.get(key);
            for (String val : strArr) {
                out.write(key + "=" + val);
                out.write("\n");
            }
        }
    }
}
