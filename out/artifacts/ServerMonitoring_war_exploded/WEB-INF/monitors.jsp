<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 8/29/2016
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <tr style="table-layout: fixed; vertical-align: middle; font-size: 30px; text-align: left; width: 100%; height: 5%; background-color: #90A4AE;">
        <td colspan="4">Monitor</td>
    </tr>
    <tr style="table-layout: fixed; vertical-align: middle; font-size: 24px; text-align: left; width: 100%; height: 5%; background-color: #CFD8DC;">
        <td colspan="3">Monitored location</td>
        <td colspan="1">Location</td>
    </tr>
    <tr style="vertical-align: middle; font-size: 24px; text-align: left; width: 70%; height: 5%; background-color: #ECEFF1;">
        <td colspan="3">Time left for next density check</td>
        <td colspan="1">time</td>
    </tr>
    <tr style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; height: 5%; color: #311b92;">
        <td>Default server</td>
        <td>server name</td>
        <td>Current density</td>
        <td>density value</td>
    </tr>
    <tr style="font-size: 28px; text-align: center; table-layout: fixed; width: 33%; height: 5%; vertical-align: top; background-color: #f9a825;">
        <td colspan="4">Create Monitor</td>
    </tr>
    <tr style="table-layout: fixed; text-align: center; vertical-align: middle; height: 10px;" colspan="4">
        <td>
            <input style="color: #757575; width: 50%; position: relative; white-space: normal; background-color: #fff9c4; font-size: 18px;"
                   type="input" value="monitor name"/>
        </td>
    </tr>
    <tr style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 33%;">
        <td colspan="1">Choose location</td>
        <td>
            <select style="word-wrap: break-word; width: 25%; height: 20%; font-size: 18px;" name="userId" size="6">
                <option value="${user.getId()}">${user.getName()}</option>
            </select>
        </td>
    </tr>
    <tr style="table-layout: fixed; text-align: center; vertical-align: middle; height: 50px;" colspan="4">
        <td >
            <input style="width: 40%; position: relative; white-space: normal; background-color: #b0bec5; font-size: 24px;"
                type="button" value="Create monitor"/>
        </td>
    </tr>
    </tbody>
</table>