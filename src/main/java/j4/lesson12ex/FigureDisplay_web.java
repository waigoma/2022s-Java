package j4.lesson12ex;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lesson12ex/FigureDisplay_web")
public class FigureDisplay_web extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String figure = request.getParameter("Figure");
        String imgSource = "../lesson12ex/" + figure;

        out.println("<html><head><title>FigureDisplay_web</title><meta charset=\"UTF-8\"></head>");
        out.println("<body>");
        out.println("<h1>Selected " + figure + "</h1>");
        out.println("<img src=\"" + imgSource + "\">");
        out.println("<form action=\"../lesson12ex/FigureDisplay_web.html\" method=\"GET\">");
        out.println("<input type=\"submit\" value=\"再び選ぶ\">");
        out.println("</body></html>");
    }
}
