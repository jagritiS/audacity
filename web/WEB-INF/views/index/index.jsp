<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<script type="text/javascript">
        $('.carousel').carousel({
            interval: 1000
        })
    </script>
<div class="container">
    
<header class="header page-header ">
    <div class="row">
        <div class="span12">

            <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="static/img/featured-banners/nepathya.jpg" height="300" width="1200"/>
                        <div class="carousel-caption">
                            <h4>Band Name</h4>
                            <p>"The details of the band band and the band"</p>
                        </div>
                    </div>
                    <div class="item">
                        <img src="static/img/featured-banners/uglyz.jpg" height="300" width="1200"/>
                        <div class="carousel-caption">
                            <h4>Band Name</h4>
                            <p>"The details of the band band and the band"</p>
                        </div>
                    </div>
                    <div class="item">
                        <img src="static/img/featured-banners/1974.jpg" height="300" width="1200"/>
                        <div class="carousel-caption">
                            <h4>Band Name</h4>
                            <p>"The details of the band band and the band"</p>
                        </div>
                    </div>
                    <div class="item">
                        <img src="static/img/featured-banners/kutumba.jpg" height="300" width="1200"/>
                        <div class="carousel-caption">
                            <h4>Band Name</h4>
                            <p>"The details of the band band and the band"</p>
                        </div>
                    </div>

                </div>
                <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>

            </div>


        </div>

    </div>
</header>

<section id="browse">
    <div class="container">
        <div class="row">

            <div class="span3">

                    <ul class="nav nav-tabs">
                        <li class="active ">
                            <a href="#topsongs" data-toggle="tab">
                                <i class="icon-headphones"></i>
                                TOP SONGS
                            </a>

                        </li>
                        <li>
                            <a href="#topalbums" data-toggle="tab">
                                <i class="icon-play"></i>
                                TOP ALBUMS
                            </a>

                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="topsongs">
                            <div class="container-fluid">
                                <c:forEach var="song" items="${topSongs}">

                                    <c:set var="img" value="static/img/50x50.gif"/>
                                    <c:if test="${song.album.fileName != null}">
                                        <c:set var="img" value="albumcovers/${song.album.fileName}"/>
                                    </c:if>

                                    <img src="${img}" width="50" height="50" alt="" class="pull-right" />
                                    <div>
                                        <a href="?action=play&id=${song.id}"><h3>${song.name}</h3></a>

                                        <div>
                                            <a href="?action=album&id=${song.albumId}">${song.album.name}</a>
                                        </div>
                                    </div>
                                    <hr/>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="topalbums">
                            <div class="container-fluid">
                                <c:forEach var="album" items="${topAlbums}">
                                    <c:set var="img" value="static/img/50x50.gif"/>
                                    <c:if test="${album.fileName != null}">
                                        <c:set var="img" value="albumcovers/${album.fileName}"/>
                                    </c:if>
                                    <img src="${img}" width="50" height="50" alt="alt" class="pull-right" />
                                    <div>
                                        <a href="?action=album&id=${album.id}"><h3>${album.name}</h3></a>

                                        <div>
                                            <a href="">${album.name}</a>
                                        </div>
                                    </div>
                                    <hr/>
                                </c:forEach>
                            </div>

                        </div>

                </div>
            </div>
            <div class="span9">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#editorsPick" data-toggle="tab">
                            <i class="icon-certificate"></i>
                            EDITOR'S PICK
                        </a>
                    </li>
                    <li>
                        <a href="#latestSongs" data-toggle="tab">
                            <i class="icon-exclamation-sign"></i>
                            JUST OUT OF STUDIO
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="editorsPick">
                        <div class="container-fluid">
                            <div class="row">
                                <c:forEach var="song" items="${featuredSongs}">
                                    <c:set var="img" value="static/img/50x50.gif"/>
                                    <c:if test="${song.album.fileName != null}">
                                        <c:set var="img" value="albumcovers/${song.album.fileName}"/>
                                    </c:if>


                                    <div class="span2" style="height:100px; ">
                                        <img src="${img}" width="50" height="50" alt="" class="pull-right" />
                                        <a href="?action=play&id=${song.id}">
                                            <i class="icon-play"></i>
                                            <strong>${song.name}</strong> <br/>
                                        </a>
                                            ${song.album.name}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade " id="latestSongs">
                        <div class="container-fluid">
                            <div class="row">
                                <c:forEach var="song" items="${latestSongs}">
                                    <c:set var="img" value="static/img/50x50.gif"/>
                                    <c:if test="${song.album.fileName != null}">
                                        <c:set var="img" value="albumcovers/${song.album.fileName}"/>
                                    </c:if>


                                    <div class="span2" style="height:100px; ">
                                        <img src="${img}" width="50" height="50" alt="" class="pull-right" />
                                        <a href="?action=play&id=${song.id}">
                                            <i class="icon-play"></i>
                                            <strong>${song.name}</strong> <br/>
                                        </a>
                                            ${song.album.name}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</div>
</body>
</html>