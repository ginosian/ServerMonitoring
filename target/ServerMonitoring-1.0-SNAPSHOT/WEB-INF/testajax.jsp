<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lion
  Date: 9/5/16
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Ajax</title>
</head>
<body onload="timer()">

    <h1>Countdown Timer</h1>
    <h1 id="time" style="font-size: xx-large"></h1>


    <script>
        var defaultColor = document.getElementById("time").style.color;
        var initialTime = 30;
        function timer() {
            tick();
            setInterval(function() {
                tick();
                if (initialTime < -1) reset();
                if (initialTime < 10) paint("red")
            }, 1000)
        }
        function tick() {
            document.getElementById("time").innerHTML = initialTime.toString();
            --initialTime;
        }
        function reset() {
            initialTime = 30;
            tick();
            paint(defaultColor);
        }
        function paint(color) {
            document.getElementById("time").style.color = color;
        }
    </script>


    <h1 id="demo">Load info from server</h1>
    <button name="update" type="button" onclick="loadDoc()">Click to update!</button>
    <h2>User List:</h2>
    <div id="list">
        <table id="table" width="40%" border="1">
        </table>
    </div>

    <script>
        function loadDoc() {
            var json;
            var str;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    str = "";
                    json = JSON.parse(this.responseText);
                    for (i = 0; i < json.length; ++i) {
                        var tr = document.createElement("tr")
                        var name = document.createElement("th");
                        name.innerHTML = json[i].name;
                        var surname = document.createElement("th");
                        surname.innerHTML = json[i].surname;
                        var age = document.createElement("th");
                        age.innerHTML = json[i].age;
                        var checkTime = document.createElement("th");
                        tr.appendChild(name);
                        tr.appendChild(surname);
                        tr.appendChild(age);
                        tr.appendChild(checkTime);
                        document.getElementById("table").appendChild(tr);
                        timer(checkTime, json[i].checkTime);
                    }
                }
            };
            xhttp.open("GET", "ajax", true);
            xhttp.send();
        }


        function timer(item, time) {
            tick(item, time);
            var defaultColor = item.style.color;
            setInterval(function() {
                time = tick(item, time);
                if (time < 0) time = reset(item, time, defaultColor);
                if (time < 10) paint("red")
            }, 1000)
        }
        function tick(item, t) {
            item.innerHTML = t.toString();
            return --t;
        }
        function reset(item, defaultColor) {
            paint(item, defaultColor);
            var newTime = 0;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    newTime = this.responseText;
                }
            };
            xhttp.open("GET", "ajax/time", false);
            xhttp.send();
            return newTime;
        }
        function paint(item, color) {
            item.style.color = color;
        }


    </script>

























    <script>
        function start(initial_time, current_time, ele) {
            var initialTime;
            var currentTime;
            if (readCookie("timer") != undefined) {
                initialTime = readCookie("timer");
            } else {
                initialTime = parseInt(initial_time);
                currentTime = parseInt(current_time);
                createCookie("timer", initialTime, 365);
            }
            tick();
            setInterval(function () {
                tick();
                if (currentTime < -1) {
                    reset();
                }
                if (currentTime < 6) paint("red")
            }, 1000)

            function tick() {
                ele.innerHTML = currentTime.toString();
                --currentTime;
            }

            function reset() {
                currentTime = initialTime;
                tick();
                paint("red");
                eraseCookie("timer");
            }

            function paint(color) {
                ele.style.color = color;
            }
        }
        (function () {
            $("#mytable").find("div[data-timer]").each(function () {
                start($(this).data("timer"), $(this)[0]);
            });
        })();

        function createCookie(name, value, days) {
            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                var expires = "; expires=" + date.toGMTString();
            } else var expires = "";
            document.cookie = name + "=" + value + expires + "; path=/monitors";
        }

        function readCookie(name) {
            var nameEQ = name + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1, c.length);
                if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
            }
            return null;
        }

        function eraseCookie(name) {
            createCookie(name, "", -1);
        }
    </script>

    <script>
        function createLocation() {
            var xhttp = new XMLHttpRequest();
            var locationName = document.getElementById("location_name").innerHTML;
            var locationAddress = document.getElementById("location_addr").innerHTML;
            var params = "location=locationName&address=locationAddress";
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementsById("data").innerHTML = xhttp.;
                }
            };
            xhttp.open("POST", "locations", true);
            xhttp.send(params);
        }
    </script>


    <script>
        function loadDoc() {
            var json;
            var str;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    str = "";
                    json = JSON.parse(this.responseText);
                    for (i = 0; i < json.length; ++i) {
                        var tr = document.createElement("tr")
                        var name = document.createElement("th");
                        name.innerHTML = json[i].name;
                        var surname = document.createElement("th");
                        surname.innerHTML = json[i].surname;
                        var age = document.createElement("th");
                        age.innerHTML = json[i].age;
                        var checkTime = document.createElement("th");
                        tr.appendChild(name);
                        tr.appendChild(surname);
                        tr.appendChild(age);
                        tr.appendChild(checkTime);
                        document.getElementById("table").appendChild(tr);
                        timer(checkTime, json[i].checkTime);
                    }
                }
            };
            xhttp.open("GET", "ajax", true);
            xhttp.send();
        }


        function timer(item, time) {
            tick(item, time);
            var defaultColor = item.style.color;
            setInterval(function () {
                time = tick(item, time);
                if (time < 0) time = reset(item, time, defaultColor);
                if (time < 10) paint("red")
            }, 1000)
        }
        function tick(item, t) {
            item.innerHTML = t.toString();
            return --t;
        }
        function reset(item, defaultColor) {
            paint(item, defaultColor);
            var newTime = 0;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    newTime = this.responseText;
                }
            };
            xhttp.open("GET", "ajax/time", false);
            xhttp.send();
            return newTime;
        }
        function paint(item, color) {
            item.style.color = color;
        }


    </script>

</body>
</html>
