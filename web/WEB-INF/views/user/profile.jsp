
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>User Dashboard</title></head>
  <body>
  <h1>Band Profile</h1>
  <form method="post" class="well">
      <input type="hidden" name="module" value="profile"/>
      <input type="hidden" name="action" value="update"/>
      <input type="hidden" name="id" value="${band.id}"/>
      <input type="text" name="name" value="${band.name}" placeholder="Your band's name goes here"/> <br/>
      <textarea name="details" rows="6" cols="20"  placeholder="Write details of your band here">${band.details}</textarea> <br/>
      <input type="submit" value="Update Band Detail"/>
  </form>

  </body>
</html>