<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Your favorite picture is:
        <%= request.getAttribute("favorite") %>
    </h4>
    <img src="<%= request.getAttribute("favorite") %>" alt="<%= request.getAttribute("favorite") %>" height="10%" width="10%">
</body>
</html>
