<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>User Dashboard</title></head>
<body>
<h1>Songs</h1>
<a class="btn btn-primary pull-right"  href="#newAlbumForm" onclick="showForm()">New Song</a>
<br/>
<table class="table ">
    <tr>
        <th>Name</th>
        <th>Details</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="song" items="${songs}">
        <tr>
            <td>${song.name}</td>
            <td>${song.details}</td>
            <td>
            	<a href="index?action=play&id=${song.id }">
            		<i class="icon-play"></i>
            	</a>
                <a href="?module=songs&action=delete&id=${song.id}">
                    <i class="icon-remove-sign"></i>
                </a>
            </td>
        </tr>
    </c:forEach>

</table>

<div id="newSongForm" class="modal hide">
    <form class="well" enctype="multipart/form-data" method="post" action="">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>New Song</h3>
    </div>
    <div class="modal-body">

            <input type="hidden" name="module" value="songs"/>
            <input type="hidden" name="action" value="add"/>
            <input type="text" value="" name="name" placeholder="Song Title"/> <br/>
            <input type="file" name="file"/><br/>
            <select name="albumId">
                <c:forEach var="album" items="${albums}">
                    <option value="${album.id}">${album.name}</option>
                </c:forEach>
            </select> <br/>
            <textarea name="details" rows="8"  placeholder="Details" ></textarea>


    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">Close</a>
        <input type="submit" class="btn btn-primary" value="Save"/>
    </div>
    </form>
</div>
<script type="text/javascript">
    function showForm(){

        $('#newSongForm').modal({
            keyboard: false
        })
    }
</script>
</body>
</html>