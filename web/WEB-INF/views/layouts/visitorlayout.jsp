<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Audacious</title>
    <jsp:include page="_resources.jsp" flush="true"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>
    <script type="text/javascript">
        
    </script>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand"></a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active">
                        <a href="index" title="audacious">
                            <i class="icon-home"></i>
                        </a>

                    </li>
                    <li>
                        <a href="?action=play" title="Play random song">
                            <i class="icon-random"></i>
                            Randomize
                        </a>
                    </li>
                    <li>
                        <a href="?action=playlist">
                            <i class="icon-play"></i>
                            Playlist
                        </a>
                    </li>
                    <li>
                        <a href="?action=browse" title="Browse music library">
                            <i class="icon-folder-open"></i>
                            Browse
                        </a>
                    </li>
                </ul>
            </div>

            <div class="nav-collapse pull-right">
                <ul class="nav">
                    <li>
                        <a href="login?action=login">
                            <i class="icon-user"></i>
                            Login
                        </a>
                    </li>
                </ul>
            </div>

            <form action="?action=search" class="navbar-search pull-right" method="get">
                <i class="icon-search"></i>
                <input type="text" class="search-query span2" placeholder="Search"/>
            </form>

        </div>
    </div>
</div>
<decorator:body/>

<footer class="footer">
</footer>


</body>
</html>