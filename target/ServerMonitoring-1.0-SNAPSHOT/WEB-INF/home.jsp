<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 9/3/2016
  Time: 6:26 PM
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
      background-color:#FDD835;
    }		</style>
</head>
<ul>
  <li>
    <a class="active">HOME</a></li>
  <li>
    <a href="${pageContext.request.contextPath}/locations">LOCATIONS</a></li>
  <li>
    <a href="${pageContext.request.contextPath}/monitors">MONITORS</a></li>
</ul>

<table style="width: 100%; height: 50%;" align="center">
  <tbody >
  <tr style="text-align: center; vertical-align: middle; height: 5%; width: 100%;">
    <td  colspan="4"></td>
  </tr>
  <tr  style="text-align: center; height: 5%;  width: 100%; font-size: 24px; background-color: #777;">
    <td colspan="4"><div style="background: #FDD835; width: 100%">NAVIGATE TO</div></td>
  </tr>
  <tr style="text-align: center; vertical-align: middle; height: 5%; width: 100%;">
    <td colspan="2">
      <input style="width: 60%; position: relative; white-space: normal; background-color: #b0bec5; font-size: 24px;"
                           type="button" value="Locations" onclick="location.href='${pageContext.request.contextPath}/locations'"/>
    </td>
    <td colspan="2">
      <input style="width: 60%; position: relative; white-space: normal; background-color: #b0bec5; font-size: 24px;"
                           type="button" value="Monitors" onclick="location.href='${pageContext.request.contextPath}/monitors'"/>
    </td>
  </tr>
  </tbody>
</table>
</html>