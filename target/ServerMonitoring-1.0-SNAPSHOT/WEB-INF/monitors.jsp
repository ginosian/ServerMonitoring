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
        }        </style>
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
<table style="width: 100%; height: 100%; table-layout: fixed;" align="center">
    <tbody>
    <c:forEach items="${data.getCards()}" var="card">
        <tr style="table-layout: fixed; vertical-align: middle; font-size: 30px; text-align: left; width: 100%; height: 5%; background-color: #90A4AE;">
            <td colspan="4">${card.getMonitorName()}</td>
        </tr>
        <tr style="table-layout: fixed; vertical-align: middle; font-size: 24px; text-align: left; width: 100%; height: 5%; background-color: #CFD8DC;">
            <td colspan="3">Monitored location</td>
            <td colspan="1">${card.getLocationName()}</td>
        </tr>
        <tr style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; height: 5%; color: #311b92;">
            <td>Default server</td>
            <td>${card.getDefaultServerName()}</td>
            <td>Current density</td>
            <td>${card.getDefaultServerDensityValue()}</td>
        </tr>
    </c:forEach>
    <tr>
        <td style="font-size: 24px; text-align: center; vertical-align: middle; table-layout: fixed; width: 100%; height: 3%; background-color: #F57F17;"
            colspan="4">CREATE MONITOR
        </td>
    </tr>
    <tr style="table-layout: fixed; text-align: center; vertical-align: middle;">
        <td style="height: 5%;" colspan="1">
            <input style="color: #757575; width: 70%; position: relative; white-space: normal; background-color: #fff9c4; font-size: 18px;"
                   type="input" value="monitor name" onfocus="value =  null"/></td>
        <td style="height: 5%;" colspan="2">
            <select style="word-wrap: break-word; width: 50%; height: 20%; font-size: 18px;" name="location" size="6">
                <option value="${user.getId()}">${user.getName()}</option>
            </select>
        </td>
        <td style="height: 5%;" colspan="1">
            <input style="width: 80%; position: relative; white-space: normal; background-color: #b0bec5; font-size: 24px;"
                   type="submit" value="Create monitor"/>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>

