package j4.lesson12ex;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lesson12ex/SessionFigure")
public class SessionFigureDisplay extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession(true);
        int count = session.getAttribute("count") == null ? 0 : (int) session.getAttribute("count");
        long time = session.getAttribute("time") == null ? 0 : (long) session.getAttribute("time");
        PrintWriter out = response.getWriter();

        String res = "";

        if (count != 0) {
            long diff = System.currentTimeMillis() - time;
            if (diff < 1000) {
                res += "<p>Refresh too fast!</p>";
            } else {
                long sec = diff / 1000;
                long milli = diff % 1000;
                res += "<p>(Refresh time: " + sec + " second " + milli + " millisecond)</p>";
            }
        }

        String[] imgSources = {"../lesson12ex/sakura.jpg", "../lesson12ex/sakura1.jpg", "../lesson12ex/sakura2.jpg", "../lesson12ex/sakura3.png"};

        String html =
                "<html>" +
                "<head>" +
                    "<title>Register_web</title>" +
                    "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                    "<h1>Refresh to Change Figure</h1>" +
                    "<p>Count(s): " + count++ + "</p>" +
                    res +
                    "<img src=\"" + imgSources[count % 4] + "\"width=\"40%\"height=\"40%\"/>" +
                    "<br>" +
                    "<img src=\"" + imgSources[1] + "\"width=\"20%\"height=\"20%\"/>" +
                    "<img src=\"" + imgSources[2] + "\"width=\"20%\"height=\"20%\"/>" +
                    "<img src=\"" + imgSources[3] + "\"width=\"20%\"height=\"20%\"/>" +
                "</body>" +
                "</html>";

        out.println(html);
        session.setAttribute("count", count);
        session.setAttribute("time", System.currentTimeMillis());
    }
}
