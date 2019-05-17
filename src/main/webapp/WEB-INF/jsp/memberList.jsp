<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Welcome</title>
</head>

<body>
<h2>UserList</h2>
<table>
<tr>
	<th>name</th>
	<th>phone</th>
	<th>email</th>
</tr>
	<c:forEach items="${members}" var="member">
	    <tr>
	        <td>${member.name}</td>
	        <td>${member.phone}</td>
	        <td>${member.email}</td>
	    </tr>
	</c:forEach>
</table>

<form mehtod="GET" action="add">
	<input type="text" name="name"/>
	<input type="text" name="phone"/>
	<input type="text" name="email"/>
	<input type="submit" value="ADD">
</form>
<form mehtod="GET" action="remove">
	<input type="text" name="name"/>
	<input type="submit" value="REMOVE">
</form>
</body>
</html>
