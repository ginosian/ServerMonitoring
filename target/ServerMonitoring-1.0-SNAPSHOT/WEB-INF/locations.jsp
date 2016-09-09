<%@ page import="controller.LocationsServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 8/29/2016
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <style type="text/css">
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #777;
        }

        li {
            float: left;
        }

        li a {
            font-size: 180%;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        a:hover:not(.active) {
            background-color: #F57F17;
        }

        .active {
            background-color: #FDD835;
        }
    </style>
    <style>
        #locationName {
            table-layout: fixed;
            vertical-align: middle;
            font-size: 30px;
            text-align: left;
            width: 100%;
            background-color: #78909C;
        }

        #mytable {
            width: 100%;
            height: 100%;
            table-layout: fixed;
        }

        #densityCheck {
            vertical-align: middle;
            font-size: 24px;
            text-align: left;
            width: 70%;
            background-color: #b0bec5;
        }

        #densityCheckValue {
            vertical-align: middle;
            font-size: 22px;
            text-align: left;
            width: 30%;
            background-color: #b0bec5;
        }

        #defaultServer {
            table-layout: fixed;
            vertical-align: middle;
            font-size: 22px;
            text-align: left;
            width: 25%;
            background-color: #cfd8dc;
            color: #311B92;
        }

        #defaultServerValue {
            table-layout: fixed;
            vertical-align: middle;
            font-size: 22px;
            text-align: left;
            width: 25%;
            background-color: #cfd8dc;
        }

        #currentDensity {
            table-layout: fixed;
            vertical-align: middle;
            font-size: 22px;
            text-align: left;
            width: 25%;
            background-color: #cfd8dc;
            color: #311B92;
        }

        #currentDensityValue {
            table-layout: fixed;
            vertical-align: middle;
            font-size: 22px;
            text-align: left;
            width: 25%;
            background-color: #cfd8dc;
        }

        #allServers {
            font-size: 28px;
            text-align: left;
            table-layout: fixed;
            width: 33%;
            height: 100%;
            vertical-align: top;
            background-color: #eceff1;
        }

        #eachServerRow {
            font-size: 28px;
            text-align: left;
            table-layout: fixed;
            width: 33%;
            height: 100%;
            vertical-align: top;
        }

        #eachServerDiv {
            word-wrap: break-word;
            text-align: left;
            font-size: 24px;
        }

        #createMonitorLabel {
            font-size: 24px;
            text-align: center;
            vertical-align: middle;
            table-layout: fixed;
            width: 100%;
            height: 3%;
            background-color: #F57F17;
        }

        #createLocationBlock {
            table-layout: fixed;
            text-align: center;
            vertical-align: middle;
        }

        #location_name {
            color: #757575;
            width: 70%;
            position: relative;
            white-space: normal;
            background-color: #fff9c4;
            font-size: 18px;
        }

        #locationInputsColumn {
            height: 5%;
        }

        #locationErrorPanel {
            color: #c51202;
        }

        #location_name {
            color: #757575;
            width: 70%;
            position: relative;
            white-space: normal;
            background-color: #fff9c4;
            font-size: 18px;
        }

        #location_addr {
            color: #757575;
            width: 70%;
            position: relative;
            white-space: normal;
            background-color: #fff9c4;
            font-size: 18px;
        }

        #LocationSubmitButton {
            width: 80%;
            position: relative;
            white-space: normal;
            background-color: #b0bec5;
            font-size: 24px;
        }

        #createLocationTitle {
            font-size: 24px;
            text-align: center;
            vertical-align: middle;
            table-layout: fixed;
            width: 100%;
            height: 3%;
            background-color: #F57F17;
        }

        #createLocationBlock {
            table-layout: fixed;
            text-align: center;
            vertical-align: middle;
        }

        #serverErrorPanel {
            color: #c51202;
        }

        #newServerName {
            color: #757575;
            width: 70%;
            position: relative;
            white-space: normal;
            background-color: #fff9c4;
            font-size: 18px;
        }

        #chosenLocation {
            word-wrap: break-word;
            width: 50%;
            font-size: 18px;
        }

        #createServerButton {
            width: 80%;
            position: relative;
            white-space: normal;
            background-color: #b0bec5;
            font-size: 24px;
        }
    </style>
</head>
<script src="http://code.jquery.com/jquery-1.10.2.js"
        type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>
<body>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/home">HOME</a></li>
    <li>
        <a class="active">LOCATIONS</a></li>
    <li>
        <a href="${pageContext.request.contextPath}/monitors">MONITORS</a></li>
</ul>

<form method="post" action="${pageContext.request.contextPath}/locations">
    <c:set var="data" value="${data}"/>
    <table id="mytable">
        <tbody>

        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${data.getCards()}" var="card">
            <tr>
                <td id="locationName" colspan="4">${card.getLocationName()}</td>
            </tr>
            <tr>
                <td id="densityCheck" colspan="3">Time left for next density check</td>
                <td id="densityCheckValue" colspan="1">
                    <div id="${count}" data-timer="${card.getDefaultServerDensityValue()}"></div>
                </td>
            </tr>
            <tr>
                <td id="defaultServer">Default server</td>
                <td>
                    <div server-name="${card.getDefaultServerName()}"></div>
                </td>
                <td id="currentDensity">Current density</td>
                <td id="currentDensityValue">${card.getDefaultServerDensityValue()}</td>
            </tr>
            <tr>
                <td id="allServers" colspan="4">All servers</td>
            </tr>
            <tr>
                <td id="eachServerRow" colspan="4">
                    <c:forEach items="${card.getServersNames()}" var="server_name">
                        <div id="eachServerDiv">${server_name}</div>
                    </c:forEach>
                </td>
            </tr>

            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>

        </tbody>
    </table>

    <table id="submitTable" style="width: 100%">
        <tbody>
        <tr>
            <td id="createMonitorLabel" colspan="4">CREATE LOCATION</td>
        </tr>
        <tr id="createLocationBlock">
            <td id="locationInputsColumn" colspan="1">
                <p id="locationErrorPanel">${data.getLocation_exist_error_message()}</p>
                <input id="location_name" name="location_name" type="text" placeholder="location name"/>
                <input id="location_addr" name="location_address" type="text" placeholder="location addr"/>
            </td>
            <td style="height: 5%;" colspan="2"></td>
            <td style="height: 5%;" colspan="1">
                <input id="LocationSubmitButton" name="act" type="submit" value="Create location"/>
            </td>
        </tr>
        <tr>
            <td id="createLocationTitle" colspan="4">CREATE SERVER</td>
        </tr>
        <tr id="createServerBlock">
            <td colspan="1">
                <p id="serverErrorPanel">${data.getServer_exist_error_message()}</p>
                <input id="newServerName" name="newServerName" type="text" placeholder="server name"/></td>
            <td colspan="2">
                <select id="chosenLocation" name="chosen_location" size="6">
                    <c:forEach items="${data.getLocationsNames()}" var="location">
                        <option id="aLocation" value="${location.toString()}">${location.toString()}</option>
                    </c:forEach>
                </select>
            </td>
            <td colspan="1">
                <input id="createServerButton" name="act" type="submit" value="Create server"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>

</body>

<script>
    function start(initial_time, ele, serverNameTD) {
//        debugger;
        var initialTime;
        var isFrozen;
        if (readCookie("timer") != undefined) {
            initialTime = readCookie("timer");
        } else {
            initialTime = parseInt(initial_time);
            createCookie("timer", initialTime, 365);
        }
        tick();
        setInterval(function () {
            if (isFrozen) return;
            tick();
            if (initialTime < 0) {
                reset();
            }
            if (initialTime < 6) paint("red")
        }, 1000)
        function tick() {
            ele.innerHTML = initialTime.toString();
            --initialTime;
        }
        function reset() {
            debugger;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
//                alert("readyState: " + this.readyState + " status: " + this.status + " " +
//                        this.statusText);

                if (this.readyState == 4 && this.status == 200) {
                    var json = JSON.parse(this.responseText);
                    serverNameTD.innerHTML = json.serverName;
                    initial_time = json.density;
                    initialTime = json.density;
                    isFrozen = false;
                }
            };
            xhttp.open("PUT", "locations", true);
            isFrozen = true;
            xhttp.send();


            paint("red");
            eraseCookie("timer");
        }
        function paint(color) {
            ele.style.color = color;
        }
    }
    (function () {
        var currentDensityDiv = $("#mytable").find("div[data-timer]");
        var currentServerNameTD = $("#mytable").find("div[server-name]");

//        currentDensityDiv.each(function () {
//            start($(this).data("timer"), );
//        });



        $("#mytable").find("div[data-timer]").each(function () {
            debugger;
            start($(this).data("timer"), $(this)[0], currentServerNameTD[$(this)[0].id]);
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

















<%--<script>--%>
    <%--function loadDoc() {--%>
        <%--var json;--%>
        <%--var xhttp = new XMLHttpRequest();--%>
        <%--xhttp.onreadystatechange = function() {--%>
            <%--if (this.readyState == 4 && this.status == 200) {--%>
                <%--json = JSON.parse(this.responseText);--%>

                <%--document.getElementById("test").innerHTML = json;--%>
                <%--for (i = 0; i < json.length; ++i) {--%>
<%--//                    var tr = document.createElement("tr")--%>
<%--//                    var name = document.createElement("th");--%>
<%--//                    name.innerHTML = json[i].name;--%>
<%--//                    var surname = document.createElement("th");--%>
<%--//                    surname.innerHTML = json[i].surname;--%>
<%--//                    var age = document.createElement("th");--%>
<%--//                    age.innerHTML = json[i].age;--%>
<%--//                    var checkTime = document.createElement("th");--%>
<%--//                    tr.appendChild(name);--%>
<%--//                    tr.appendChild(surname);--%>
<%--//                    tr.appendChild(age);--%>
<%--//                    tr.appendChild(checkTime);--%>
<%--//                    document.getElementById("table").appendChild(tr);--%>
<%--//                    timer(checkTime, json[i].checkTime);--%>
                <%--}--%>
            <%--}--%>
        <%--};--%>
        <%--xhttp.open("GET", "locations", true);--%>
        <%--xhttp.send();--%>
    <%--}--%>
    <%--function timer(item, time) {--%>
        <%--tick(item, time);--%>
        <%--var defaultColor = item.style.color;--%>
        <%--setInterval(function() {--%>
            <%--time = tick(item, time);--%>
            <%--if (time < 0) time = reset(item, time, defaultColor);--%>
            <%--if (time < 10) paint("red")--%>
        <%--}, 1000)--%>
    <%--}--%>
    <%--function tick(item, t) {--%>
        <%--item.innerHTML = t.toString();--%>
        <%--return --t;--%>
    <%--}--%>
    <%--function reset(item, defaultColor) {--%>
        <%--paint(item, defaultColor);--%>
        <%--var newTime = 0;--%>
        <%--var xhttp = new XMLHttpRequest();--%>
        <%--xhttp.onreadystatechange = function() {--%>
            <%--if (this.readyState == 4 && this.status == 200) {--%>
                <%--newTime = this.responseText;--%>
            <%--}--%>
        <%--};--%>
        <%--xhttp.open("GET", "ajax/time", false);--%>
        <%--xhttp.send();--%>
        <%--return newTime;--%>
    <%--}--%>
    <%--function paint(item, color) {--%>
        <%--item.style.color = color;--%>
    <%--}--%>
<%--</script>--%>

</html>
