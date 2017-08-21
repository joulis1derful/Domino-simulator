<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Domino</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/script.js"></script>
    <script src="js/jquery-3.2.1.js"></script>
</head>
<body>
    <div id="container">
        <div class="menu">
            <h1>Welcome to the Domino simulator</h1>
            <div class="options">
                <button class="btn btn-primary btn1">ROLL & GET BARS</button>
                <button class="btn btn-primary btn2">Show bar traces</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(".btn1").click(function fadeOut() {
            $("button").fadeOut(3000);
            setInterval(redirect1, 3000);
        });

        $(".btn2").click(function fadeOut() {
            $("button").fadeOut(3000);
            setInterval(redirect2, 3000);
        });

        function redirect1() {
            $(location).attr('href', '/generate')
        }

        function redirect2() {
            $(location).attr('href', '/results')
        }

    </script>
</body>
</html>
