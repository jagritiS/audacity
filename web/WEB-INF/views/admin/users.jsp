<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>

</head>
<body>
<div class="container">
    <h1>Users</h1>
    <header class="header page-header">

        <div class="row">
            <div class="span10">
                <table class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>User Type</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.userType}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </header>

</div>
</body>
</html>