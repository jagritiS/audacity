<html>
<head>

</head>

<body>
<div class="container">
    <header >
        <div class="row">
            <div class="span8">
                <h1>Register, Get Famous!</h1>
                <div class="container" style="padding-top:20px">
                <ul >
                    <li>Free registration</li>
                    <li>No hidden cost </li>
                    <li>Unlimited uploads</li>
                    <li>Reach to thousands of audience</li>
                </ul>
                    </div>
            </div>
            <div class="span3">
                <ul class="nav nav-tabs">
                    <li>
                        <a href="#register" data-toggle="tab">
                            <i class="icon-user"></i>
                            Register
                        </a>
                    </li>
                    <li class="active">
                        <a href="#login" data-toggle="tab">
                            <i class="icon-asterisk"></i>
                            Login
                        </a>
                    </li>
                </ul>

                <div id="forms" class="tab-content">
                    <div class="tab-pane fade" id="register">
                        <form method="post" class="well" id="registerForm" action="">
                            <h2>Register</h2>
                            <input type="text" name="fullname" placeholder="Name"/>
                            <input type="text" name="email" placeholder="Email"/>
                            <input type="text" name="username" placeholder="Username"/>
                            <input type="password" name="password" placeholder="Password"/>
                            <input type="password" name="repassword" placeholder="Re-Password"/>
                            <botton id="registerBtn" type="submit" class="btn">Register</botton>
                        </form>
                    </div>
                    <div class="tab-pane fade active in" id="login">
                         <form action="login" method="post" class="well" id="loginForm">
                             <h2>Login</h2>
                             <input type="text" name="username" placeholder="Username"/>
                             <input type="password" name="password" placeholder="Password"/>
                             <input type="hidden" name="action" value="authenticate"/>
                             <label>
                             <input type="checkbox"> Remember Me
                                 </label>
                             <button type="submit" class="btn" >Login</button>
                             
                         </form>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>
<script type="text/javascript">
    $("#registerBtn").click(function() {

        var username = document.forms.registerForm.username.value;
        var password = document.forms.registerForm.password.value;
        var repassword = document.forms.registerForm.repassword.value;

        if(username == ""){
            alert("Your username can not be blank");
            return;
        }

        if(password == ""){
            alert("You can not have a blank password");
            return;
        }

        if(password != repassword){
            alert("Password do not match");
            return;
        }

        var url = "?action=validateUsername&username="+username;

        $.ajax ({
            type: "GET",
            url: url,
//            data: $("#registerForm").serialize(),
            success: function(data){
                if(data != "available"){
                    alert("This username is already in use, please user different one");
                    return;
                } else {
                    //registration
                    url = "?action=register"

                    $.ajax ({
                        type: "POST",
                        url: url,
                        data: $("#registerForm").serialize()+"&action=register",
                        success: function(data){
                            if(data == "failed"){
                                alert("Could not register a user. Please try again later");
                            } else {
                                $('#registerForm').get(0).reset();
                                alert("Your user is register, Please login with your username and password");
                            }
                        }
                    });
                }
            }
        });
        return false;
    });

</script>

</body>
</html>