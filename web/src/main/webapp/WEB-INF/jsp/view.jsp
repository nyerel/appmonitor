<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="am" uri="http://nyerel.eu/appmonitor" %>

<html>
    <head>
        <title>Monitoring Results</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.treetable.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.treetable.theme.default.css"/>"/>

        <script type="text/javascript" src="<c:url value="/js/jquery-1.10.1.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery.treetable.js"/>"></script>

    </head>
    <body>
        <h1>Monitoring Results</h1>

        <div>
            <am:method-call-table id="myTable" calls="${methodCalls}"/>
        </div>
    </body>
</html>