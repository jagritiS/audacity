<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>

</head>

<body>

    <div class="container">
		<h1>${album.name }</h1>
		by <a href="?action=band&id=${album.band.id}"> ${album.band.name}</a>
		<table class="table">
		<tr>
			<th>Title</th>
			<th>Play</th>
		</tr>
		<c:forEach var="song" items="${songs }">
			<tr>
				<td>
                    <a href="?action=play&id=${song.id}">
				    ${song.name }
                    </a>
                </td>
				<td>
					<a href="?action=play&id=${song.id }" >
						<i class="icon-play"></i>
					</a> 
				</td>
			</tr>
		</c:forEach> 
		</table>
    
    </div>
</body>
</html>