<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>

</head>
<body>
<div class="container">
    <h1>Bands</h1>
    <header class="header page-header">

        <div class="row">
            <div class="span10">
                <table class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Details</th>
                    </tr>
                    <c:forEach var="band" items="${bands}">
                        <tr>
                            <td>
                                <a href="?module=bands&action=detail&id=${band.id}">
                                ${band.name}
                                </a>
                            </td>
                            <td>${band.details}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </header>

</div>
</body>
</html>