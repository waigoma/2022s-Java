package j4.lesson12ex;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/lesson12ex/Register_web")
public class Register_web extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String gender = request.getParameter("gender");
        String sports = request.getParameter("sports");
        sports = sports != null ? "sports " : "";
        String music = request.getParameter("music");
        music = music != null ? "music " : "";
        String game = request.getParameter("game");
        game = game != null ? "game " : "";

        String res = "";
        boolean isError = false;

        if (isNumeric(username)) {
            res += "<strong>User Name can't be numeric</strong><br>";
            isError = true;
        }
        if (!isSame(password, confirm)) {
            res += "<strong>Password don't match!</strong><br>";
            isError = true;
        }

        if (!isError) {
            res = "<strong>Register Succeed!</strong><br>";
            res += "<strong>Name: " + username + "</strong><br>";
            res += "<strong>Email: " + email + "</strong><br>";
            res += "<strong>Gender: " + gender + "</strong><br>";
            res += "<strong>Tel: " + tel + "</strong><br>";
            res += "<strong>Hobby: " + sports + music + game + "</strong><br>";
        }

        String html =
                "<html>" +
                "<head>" +
                    "<title>Register_web</title>" +
                    "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                    "<h1>Register Result</h1>" +
                    res +
                "</body>" +
                "</html>";

        out.println(html);
    }

    // 数字だけかどうか
    private boolean isNumeric(String str) {
        Pattern p = Pattern.compile("[0-9]*");
        return p.matcher(str).matches();
    }

    // 文字列と文字列が同じかどうか
    private boolean isSame(String str1, String str2) {
        return str1.equals(str2);
    }
}
