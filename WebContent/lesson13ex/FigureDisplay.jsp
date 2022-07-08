<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Session started at
        <%= new Date() %>.
        Passed time: <%= (System.currentTimeMillis() - session.getCreationTime()) / 1000 %> seconds
    </h4>

    <img src="sakura1.jpg" alt="" width="20%" height="20%">
    <img src="sakura2.jpg" alt="" width="20%" height="20%">
    <img src="sakura3.png" alt="" width="20%" height="20%">

    <form action="FigureVote.jsp" method="post">
        <input type="submit" name="favorite" value="sakura1.jpg" >
        <input type="submit" name="favorite" value="sakura2.jpg" >
        <input type="submit" name="favorite" value="sakura3.png" >
    </form>
</body>
</html>
