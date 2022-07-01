package j4.lesson12ex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/lesson12ex/Greeting")
public class Greeting extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        int hour = date.getHours();
        String time = format.format(date);
        String greeting = "";
        String imgSource = "";

        if (5 < hour && hour < 12) {
            greeting = "Good morning";
            imgSource = "../lesson12ex/morning.jpg";
        } else if (12 <= hour && hour < 18) {
            greeting = "Good afternoon";
            imgSource = "../lesson12ex/afternoon.jpg";
        } else {
            greeting = "Good evening";
            imgSource = "../lesson12ex/evening.jpg";
        }

        out.println("<html><head><title>Greeting</title></head>");
        out.println("<body>");
        out.println("<img src=\"" + imgSource + "\">");
        out.println("<p>" + time + "</p>");
        out.println("<h1>" + greeting + "</h1></body></html>");
    }
}
