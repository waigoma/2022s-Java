<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online Questionnaire</title>
</head>

<body>
	<center>
		<h2>
		Hello, Visitor!
<!--
  Add jsp codes to display Good Morning, Good Afternoon, Good Evening.
-->
		</h2>
		<form
			action="http://localhost:8080/j4/lesson13ex/QuestionnaireResult.jsp"
			method="GET">
			<table align="top" border="2" width=40%>
				<td></td>
				<td align="left">
					<h1>Questionnaire</h1>
				</td>
				<tr>
					<td align="right"><label>Name:</label></td>
					<td><input type="text" name="username"></td>
				</tr>

				<tr>
					<td align="right"><label>E-mail:</label></td>
					<td><input type="email" name="email" ></td>
				</tr>
				<tr>
					<td align="right"><label>Tel:</label></td>
					<td><input type="tel" name="tel" ></td>
				</tr>
				<tr>
					<td align="right"><label>Gender:</label></td>
					<td><input type="radio" name="gender" value="male" checked/>Male
						<input type="radio" name="gender" value="female" />Female</td>
				</tr>
				<tr>
					<td align="right"><label>Color:</label></td>
					<td><input type="radio" name="color" value="black" checked />Black
					    <input type="radio" name="color" value="red" />Red
						<input type="radio" name="color" value="blue" />Blue</td>
				</tr>

				<tr>
					<td align="right"><label>Hobby:</label></td>
					<td><input type="checkbox" name="sports"/>Sports
						<input type="checkbox" name="music" />Music
						<input type="checkbox" name="game"  />Game</td>
				</tr>

				<tr>
					<td align="right"></td>
					<td><input type="reset" name="reset" value="reset" />
					<input type="submit" name="submit" value="submit" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>