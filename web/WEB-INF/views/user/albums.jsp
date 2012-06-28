<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>User Dashboard</title></head>
<body>
<h1>Albums</h1>
<a class="btn btn-primary pull-right"  href="#newAlbumForm" onclick="showForm()">New Album</a>
<br/>
<table class="table ">
    <tr>
        <th>Name</th>
        <th>Details</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="song" items="${albums}">
        <tr>
            <td>${song.name}</td>
            <td>${song.details}</td>
            <td>
                <a href="?module=albums&action=delete&id=${song.id}">
                    <i class="icon-remove-sign"></i>
                </a>
            </td>
        </tr>
    </c:forEach>

</table>

<div id="newAlbumForm" class="modal hide">
    <form class="well">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>New Album</h3>
    </div>
    <div class="modal-body">



            <input type="hidden" name="module" value="albums"/>
            <input type="hidden" name="action" value="add"/>
            <input type="text" value="" name="name" placeholder="Album Name"/> <br/>
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

        $('#newAlbumForm').modal({
            keyboard: false
        })
    }
</script>
</body>
</html>