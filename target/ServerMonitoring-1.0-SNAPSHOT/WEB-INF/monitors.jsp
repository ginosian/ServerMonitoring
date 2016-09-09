<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 8/29/2016
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
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
        #monitorName {
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

        #createMonitorTitle {
            font-size: 24px;
            text-align: center;
            vertical-align: middle;
            table-layout: fixed;
            width: 100%;
            height: 3%;
            background-color: #F57F17;
        }

        #createMonitorBlock {
            table-layout: fixed;
            text-align: center;
            vertical-align: middle;
        }

        #monitorErrorPanel {
            color: #c51202;
        }

        #newMonitorName {
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

        #createMonitorButton {
            width: 80%;
            position: relative;
            white-space: normal;
            background-color: #b0bec5;
            font-size: 24px;
        }
    </style>
</head>
<body>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/home">HOME</a></li>
    <li>
        <a href="${pageContext.request.contextPath}/locations">LOCATIONS</a></li>
    <li>
        <a class="active">MONITORS</a></li>
</ul>
<form method="post" action="${pageContext.request.contextPath}/monitors">
    <c:set var="data" value="${data}"/>
    <table id="mytable">
        <tbody>

    <c:forEach items="${data.getCards()}" var="card">
        <tr>
            <td id="monitorName" colspan="4">${card.getLocationName()}</td>
        </tr>
        <tr>
            <td id="densityCheck" colspan="3">Time left for next density check</td>
            <td id="densityCheckValue" colspan="1">
                <div id="data-timer" data-timer="${card.getDefaultServerDensityValue()}"></div>
            </td>
        </tr>
        <tr>
            <td id="defaultServer">Default server</td>
            <td id="defaultServerValue">${card.getDefaultServerName()}</td>
            <td id="currentDensity">Current density</td>
            <td id="currentDensityValue">${card.getDefaultServerDensityValue()}</td>
        </tr>
    </c:forEach>



    <tr>
        <td id="createMonitorTitle" colspan="4">CREATE MONITOR</td>
    </tr>
    <tr id="createMonitorBlock">
        <td colspan="1">
            <p id="monitorErrorPanel">${data.getMonitor_exist_error_message()}</p>
            <input id="newMonitorName" name="newMonitorName" type="text" placeholder="server name"/></td>
        <td colspan="2">
            <select id="chosenLocation" name="chosen_location" size="6">
                <c:forEach items="${data.getLocationsNames()}" var="location">
                    <option id="aLocation" value="${location.toString()}">${location.toString()}</option>
                </c:forEach>
            </select>
        </td>
        <td colspan="1">
            <input id="createMonitorButton" name="act" type="submit" value="Create monitor"/>
        </td>
    </tr>
    </tbody>
</table>
</form>
</body>
</html>

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

