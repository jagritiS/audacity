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
            </div>
            <div class="span8">
                <hr/>
                <h1>${song.name}</h1>
                ${song.album.name}
                ${song.details}

            </div>

        </div>
        <div class="row">
            <hr/>
            <div class="span12">
                <h1>Band Name</h1>
            </div>
        </div>
    </div>
</div>
</body>
</html>