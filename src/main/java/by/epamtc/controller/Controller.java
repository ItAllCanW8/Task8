package by.epamtc.controller;

import by.epamtc.entity.Device;
import by.epamtc.service.Director;
import by.epamtc.service.DomBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch(ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch(ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        PrintWriter pw = resp.getWriter();
//        pw.println("<html>");
//        pw.println("<h1>GELLO</h1>");
//        pw.println("</html>");

        String commandName = req.getParameter("command");
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

        parse(req);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/parse_result.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void parse(HttpServletRequest req){
        String fileName = (String)req.getServletContext().getAttribute("input-file");
        //req.getSession().getServletContext()
        File inputFile = new File(req.getServletContext().
                getInitParameter("file-upload") + "\\" + fileName);

        List<Device> devices = Director.createDevices(new DomBuilder(inputFile));
//        DomBuilder dom = new DomBuilder(req);
    }
}
