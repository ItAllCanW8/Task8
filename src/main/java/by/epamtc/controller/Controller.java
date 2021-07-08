package by.epamtc.controller;

import by.epamtc.service.builder.Director;
import by.epamtc.service.builder.DOMBuilder;
import by.epamtc.service.builder.SAXBuilder;
import by.epamtc.service.builder.StAXBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp);
        } catch(Exception e) {
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp);
        } catch(Exception e) {
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String commandName = req.getParameter("command");
        req.setCharacterEncoding("UTF-8");

        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        req.getServletContext().setAttribute("input-file", fileName);

        try(InputStream fileContent = filePart.getInputStream()){
            File uploads = new File(getServletContext().getInitParameter("file-upload"));
            File file = new File(uploads, fileName);

            if(!file.exists())
                Files.copy(fileContent, file.toPath());
        }
        parse(req, resp);

        req.getRequestDispatcher("WEB-INF/jsp/parse_result.jsp").forward(req,resp);
    }

    private void parse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = (String)req.getServletContext().getAttribute("input-file");

        File inputFile = new File(req.getServletContext().
                getInitParameter("file-upload") + "\\" + fileName);

        File xsd = new File(req.getServletContext().getInitParameter("xsd"));

        Optional<String> parserType = Optional.ofNullable(req.getParameter("parser-type"));

        if(!fileName.equals("") && parserType.isPresent() && xsd.exists()){
            try{
                if(parserType.get().equals("dom"))
                    req.getSession().setAttribute("devices", Director.createDevices(new DOMBuilder(), inputFile, xsd));
                else if (parserType.get().equals("sax"))
                    req.getSession().setAttribute("devices", Director.createDevices(new SAXBuilder(), inputFile, xsd));
                else
                    req.getSession().setAttribute("devices", Director.createDevices(new StAXBuilder(), inputFile, xsd));
            } catch (ServiceException ex) {
                req.getSession().setAttribute("error", ex.getMessage());
                req.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(req, resp);
            }
        } else{
            req.getSession().setAttribute("error", "Oops! XML file not chosen or XSD schema not present");
            req.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}
