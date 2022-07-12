<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>FigureVote</title>
</head>
<body>
    <%@include file="FigureDisplay.jsp"%>
    <%
        String favorite = request.getParameter("favorite");
        switch (favorite) {
            case "sakura1.jpg":
                pict1++;
                break;
            case "sakura2.jpg":
                pict2++;
                break;
            case "sakura3.png":
                pict3++;
                break;
        }
        if (pict1 > pict2 && pict1 > pict3) {
            mostPict = "sakura1.jpg";
        } else if (pict2 > pict1 && pict2 > pict3) {
            mostPict = "sakura2.jpg";
        } else if (pict3 > pict1 && pict3 > pict2) {
            mostPict = "sakura3.png";
        }
    %>
    <div style="display: flex">
        <h4>Your favorite picture is:
            <%= favorite %>
        </h4>
        <img src="<%= favorite %>" alt="<%= favorite %>" height="10%" width="10%">
    </div>

    <div style="display: flex">
        <h4>The most selected flower by all clients is:
            <%= favorite %>
        </h4>
        <img src="<%= mostPict %>" alt="<%= mostPict %>" height="10%" width="10%">
    </div>
    <h4>The selected times:
            <%= Math.max(pict1, Math.max(pict2, pict3)) %>
</body>
</html>
