<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>

</head>
<body>
<div class="container">


    <div class="">
        <div class="row">
            <div class="span4">
                <hr/>
                <audio controls="controls" >
                    <source src="songs/${song.fileName}" type="audio/mp3"/>
                    You need to upgrade your browser.
                </audio>
                <br/><br/>
                <c:if test="${sessionScope.user.userType == 'admin'}">
                    <c:if test="${song.featured}">
                        <span class="label label-info">Featured on ${song.featuredDate} </span>
                    </c:if>
                    <c:if test="${!song.featured}">
                        <form action="admin" method="post">
                            <input type="hidden" name="module" value="songs"/>
                            <input type="hidden" name="action" value="feature"/>
                            <input type="hidden" name="id" value="${song.id}"/>
                            <input type="submit" class="btn btn-large btn-primary" value="Feature this song"/>
                        </form>
                    </c:if>
                </c:if>

            </div>
            <div class="span8">
                <hr/>
                <h1>${song.name}</h1>
                from <a href="index?action=album&id=${song.album.id }">${song.album.name}</a><br/>
                by <a href="index?action=band&id=${song.album.band.id }">${song.album.band.name }</a><br/>
                ${song.details}

            </div>

        </div>
       
    </div>
</div>
</body>
</html>