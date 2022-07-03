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
        String hobby = request.getParameter("hobby");

        String res = "";
        boolean isError = false;

        if (isNumeric(username)) {
            res += "User Name can't be numeric\n";
            isError = true;
        }
        if (isSame(password, confirm)) {
            res += "Password don't match!\n";
            isError = true;
        }

        if (!isError) {
            res = "Register Succeed!\n\n";
            res += "Name: " + username + "\n";
            res += "Email: " + email + "\n";
            res += "Gender: " + gender + "\n";
            res += "Tel: " + tel + "\n";
        }

        String html =
                "<html>" +
                "<head>" +
                    "<title>Register_web</title>" +
                    "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                    "<h1>Register Result</h1>" +
                    "<h2>" + res + "</h2>" +
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
