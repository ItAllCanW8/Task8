<%--
  Created by IntelliJ IDEA.
  User: ya
  Date: 19.06.2021
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parse Result</title>
    <style>
        table, th, td {
            padding: 2px;
            border-collapse: collapse;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Parse Results</h1>
<table>
    <tr>
        <td>Name</td>
        <td>Origin</td>
        <td>Price/td>
        <td>Group Of Devices</td>
        <td>Power Consumption, W</td>
        <td>Is Peripheral?</td>
        <td>Is Cooler Present?</td>
        <td>Port</td>
        <td>Is Critical?</td>
    </tr>
    <c:if test="${! empty sessionScope.devices}">
        <c:forEach items="${sessionScope.devices}" var="device">
            <tr>
                <td>${device.name}</td>
                <td>${device.origin}</td>
                <td>${device.name}</td>
                <td>${device.depositType.name()}</td>
                <td>${device.amount}</td>
                <td>${device.interest}</td>
                <td>${device.timeConstraint.begin}</td>
                <td>${device.timeConstraint.end}</td>

            </tr>
        </c:forEach>
    </c:if>

</table>
</body>
</html>
