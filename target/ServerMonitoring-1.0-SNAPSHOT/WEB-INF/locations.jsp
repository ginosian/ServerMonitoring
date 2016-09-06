<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 8/29/2016
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>
<%--<jsp:include page='${root} + "/hello"'/>--%>
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

<table id="mytable" style="width: 100%; height: 100%; table-layout: fixed;" align="center">
    <tbody >
    <c:forEach items="${data.getCards()}" var="card">
        <tr>
            <td style="table-layout: fixed; vertical-align: middle; font-size: 30px; text-align: left; width: 100%; background-color: #78909C;"
                colspan="4">${card.getLocationName()}</td>
        </tr>
        <tr>
            <td style="vertical-align: middle; font-size: 24px; text-align: left; width: 70%; background-color: #b0bec5;"
                colspan="3">Time left for next density check
            </td>
            <td style="vertical-align: middle; font-size: 22px; text-align: left; width: 30%; background-color: #b0bec5;"
                colspan="1">
                <div data-timer="${card.getDefaultServerDensityValue()}"></div>
                <input hidden="hidden" id="density" value="${card.getDefaultServerDensityValue()}"/>
            </td>
        </tr>
        <tr>
            <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; background-color: #cfd8dc; color:#311B92">
                Default server
            </td>
            <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%;
                background-color: #cfd8dc;">${card.getDefaultServerName()}</td>
            <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; background-color: #cfd8dc;color:#311B92">
                Current density
            </td>
            <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%;
                background-color: #cfd8dc;">${card.getDefaultServerDensityValue()}</td>
        </tr>
        <tr>
            <td style="font-size: 28px; text-align: left; table-layout: fixed; width: 33%; height: 100%; vertical-align: top; background-color: #eceff1;"
                colspan="4">All servers
            </td>
        </tr>
        <tr>
            <td style="font-size: 28px; text-align: left; table-layout: fixed; width: 33%; height: 100%; vertical-align: top;"
                colspan="4">
                <c:forEach items="${card.getServersNames()}" var="server_name">
                    <div style="word-wrap:break-word; text-align: left; font-size:24px">${server_name}</div>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td style="font-size: 28px; text-align: left; table-layout: fixed; width: 33%; height: 100%; vertical-align: top; background-color: #F57F17;"
            colspan="4">CREATE SERVER
        </td>
    </tr>
    <tr>
        <td style="table-layout: fixed; text-align: center; vertical-align: middle; height: 50px;" colspan="2">
            <input style="color: #757575; width: 60%; position: relative; white-space: normal; background-color: #fff9c4; font-size: 18px;"
                   type="input" value="server name"/></td>
        <td style="table-layout: fixed; text-align: center; vertical-align: middle; height: 50px;" colspan="2"><input
                style="width: 60%; position: relative; white-space: normal; background-color: #b0bec5; font-size: 24px;"
                type="button" value="Create server"/></td>
    </tr>
    </tbody>
</table>


<script>
    function start(initial_time, ele) {
        var initialTime = parseInt(initial_time);

        tick();
        setInterval(function () {
            tick();
            if (initialTime < -1) reset();
            if (initialTime < 6) paint("red")
        }, 1000)

        function tick() {
            ele.innerHTML = initialTime.toString();
            --initialTime;
        }

        function reset() {
            initialTime = initial_time;
            tick();
            paint("red");
        }

        function paint(color) {
            ele.style.color = color;
        }
    }

    (function() {

        $("#mytable").find("div[data-timer]").each(function(){
            start($(this).data("timer"),$(this)[0]);
        });
    })();

</script>




</body>

</html>
