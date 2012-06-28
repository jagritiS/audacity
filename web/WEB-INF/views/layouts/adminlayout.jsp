<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Audacity</title>
    <jsp:include page="_resources.jsp" flush="true"/>
</head>
<body >
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">

                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="brand" href="">Dashboard</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">

                        <li><a href="?module=users">Users</a></li>
                        <li><a href="?module=bands&action=list">Bands</a></li>
                        <li><a href="?module=reports">Reports</a></li>
                    </ul>
                </div>

                <div class="nav-collapse pull-right">
                    <ul class="nav">
                        <li>
                            <a href="index">
                                <i class="icon-th"></i>
                                Main View
                            </a>
                        </li>
                        <li>
                            <a href="login?action=logout">

                                <i class="icon-user"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <decorator:body />

        <footer class="footer">
             
        </footer>
    </div>


    

</body>
</html>