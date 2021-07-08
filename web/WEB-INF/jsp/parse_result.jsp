<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<h1>Parse Result</h1>
<table>
    <tr>
        <td>Device id</td>
        <td>Market Launch Date</td>
        <td>Name</td>
        <td>Origin</td>
        <td>Price, BYN</td>
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
                    <td>${device.id}</td>
                    <td>${device.dateTime}</td>
                    <td>${device.name}</td>
                    <td>${device.origin}</td>
                    <td>${device.price}</td>
                    <td>${device.type.groupOfDevices}</td>
                    <td>${device.type.powerConsumption}</td>
                    <td>${device.type.peripheral}</td>
                    <td>${device.type.coolerPresent}</td>
                    <td>${device.type.port}</td>
                    <td>${device.critical}</td>
                </tr>
            </c:forEach>
        </c:if>
</table>
</body>
</html>
