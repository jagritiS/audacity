<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>

</head>
<body>
<div class="container">
    <h1>${band.name}</h1>
    ${band.details}
    <header class="header page-header">
        <h3>Songs</h3>
        <div class="row">
            <div class="span10">
                <table class="table">
                    <tr>
                        <th>Title</th>
                        <th>Band</th>
                        <th>Album</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="song" items="${songs}">
                        <tr>
                            <td>
                                <a href="index?action=play&id=${song.id}">
                                ${song.name}
                                </a>
                            </td>
                            <td>${song.album.name}</td>
                            <td>${song.album.band.name}</td>
                            <td>
                                <a href="?module=bands&action=deleteSong&id=${song.id}" title="Remove Song">
                                    <i class="icon-remove-circle"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </header>

</div>
</body>
</html>
