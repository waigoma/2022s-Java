<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>QuestionnaireInput</title>
</head>
<body>
<%
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    int hour = date.getHours();
    String greeting;

    if (5 < hour && hour < 12) {
        greeting = "Good Morning";
    } else if (12 <= hour && hour < 18) {
        greeting = "Good Afternoon";
    } else {
        greeting = "Good Evening";
    }
%>
<h1>Hello, Visitor! <%= greeting %></h1>
<center>
    <form action="http://localhost:8080/j4/lesson12ex/Register_web" method="GET">

        <table align="center">
            <td></td>
            <td align="left">
                <b>Register</b>
            </td>
            <tr >
                <td align="right"><label>Name:</label></td>
                <td  ><input type="text" name="username"></td>
            </tr>
            <br>
            <tr>
                <td align="right"><label>Password:</label></td>
                <td><input type="password" name="password"></td>
            </tr>
            <br>
            <tr>
                <td align="right"><label>Confirm:</label></td>
                <td><input type="password" name="confirm"></td>
            </tr>
            <br>

            <tr>
                <td align="right"><label>E-mail:</label></td>
                <td><input type="email" name="email"></td>
            </tr>
            <br>

            <tr>
                <td align="right"><label>Tel:</label></td>
                <td><input type="tel" name="tel"></td>
            </tr>
            <br>

            <p>
                <tr>
                    <td align="right"><label>Gender:</label></td>
                    <td><input type="radio" name="gender" value="male" checked />Male
                        <input type="radio" name="gender" value="female" />Female</td>
                </tr>
                <br>

                <tr>
                    <td align="right"><label>Hobby:</label></td>
                    <td><input type="checkbox" name="sports" />Sports
                        <input type="checkbox" name="music" />Music
                        <input type="checkbox" name="game" />Game</td>
                </tr>
                <br>

                <tr>
                    <td align="right"></td>
                    <td><input type="reset" name="reset" value="reset" />
                        <input type="submit" name="submit" value="submit" />
                    </td>
                </tr>
                <br>
        </table>
    </form>
</center>
</body>
</html>
