<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
	<title>YoilTellerMVC</title>
</head>
<body>
<h1>year=<%=request.getParameter("year") %></h1>
<%-- <h1>${year}년 ${month}월 ${day}일은 ${yoil}요일입니다.</h1> --%> <!-- 이건 매개변수를 myDate로 합치기 전 -->
<h1>${myDate.year}년 ${month}월 ${day}일은 ${yoil}요일입니다.</h1> <!-- year,month,day를 myDate로 합쳤을 때 -->
</body>
</html>