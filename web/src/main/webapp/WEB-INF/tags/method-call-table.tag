<%@taglib prefix="am" uri="http://nyerel.eu/appmonitor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="calls" type="java.util.List<eu.nyerel.appmonitor.model.MethodCall>" required="true" %>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="cssClass" type="java.lang.String" required="false"%>

<script type="text/javascript">

    $(function() {
        $("#${id}").treetable({
            clickableNodeNames: true,
            expandable: true
        });
    });

</script>

<table id="${id}" <c:if test="${!empty cssClass}">class="${cssClass}"</c:if>>

    <thead>
        <tr>
            <th>
                Name
            </th>
            <th>
                Count
            </th>
            <th>
                Self time
            </th>
            <th>
                Total time
            </th>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${calls}" var="call">
            <am:method-call-row call="${call}"/>
        </c:forEach>
    </tbody>

</table>