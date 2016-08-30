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
      background-color:#FDD835;
    }		</style>
</head>
<ul>
  <li>
    <a href="${root}/${home}">LOCATIONS</a></li>
  <li>
    <a class="active">MONITORS</a></li>
</ul>
<table style="width: 100%; height: 100%; table-layout: fixed;" align="center">
  <tbody>
  <tr>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 30px; text-align: left; width: 100%; background-color: #90A4AE;" colspan="4">Monitor</td>
  </tr>
  <tr>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 24px; text-align: left; width: 100%; background-color: #CFD8DC;" colspan="3">Monitored location</td>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 24px; text-align: left; width: 100%; background-color: #CFD8DC;" colspan="1">Location</td>
  </tr>
  <tr>
    <td style="vertical-align: middle; font-size: 24px; text-align: left; width: 70%; background-color: #ECEFF1;" colspan="3">Time left for next density check</td>
    <td style="vertical-align: middle; font-size: 22px; text-align: left; width: 30%; background-color: #ECEFF1;" colspan="1">time</td>
  </tr>
  <tr>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; color: #311b92; ">Default server</td>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; ">server name</td>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%; color: #311b92;">Current density</td>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 25%;">density value</td>
  </tr>
  <tr>
    <td style="font-size: 28px; text-align: center; table-layout: fixed; width: 33%; height: 100%; vertical-align: top; background-color: #F9A825;" colspan="4">Create Monitor</td>
  </tr>
  <tr>
    <td style="table-layout: fixed; text-align: center; vertical-align: middle; height: 50px;" colspan="4"><input style="color: #757575; width: 50%; position: relative; white-space: normal; background-color: #fff9c4; font-size: 18px;" type="input" value="monitor name" /></td>
  </tr>
  <tr>
    <td style="table-layout: fixed; vertical-align: middle; font-size: 22px; text-align: left; width: 33%;" colspan="1">Choose location</td>
    <td style="table-layout: fixed; text-align: center;"><select style="word-wrap: break-word; width: 25%; font-size: 18px;" name="userId" size="6">
      <option value="${user.getId()}">${user.getName()}</option>
    </select></td>
  </tr>
  <tr>
    <td style="table-layout: fixed; text-align: center; vertical-align: middle; height: 50px;" colspan="4"><input style="width: 40%; position: relative; white-space: normal; background-color: #b0bec5; font-size: 24px;" type="button" value="Create monitor" /></td>
  </tr>
  </tbody>
</table>