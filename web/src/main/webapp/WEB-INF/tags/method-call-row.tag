<%@taglib prefix="am" uri="http://nyerel.eu/appmonitor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="call" type="eu.nyerel.appmonitor.model.MethodCall" required="true" %>

<tr data-tt-id="${call.id}" <c:if test="${!empty call.parent}">data-tt-parent-id="${call.parent.id}"</c:if>>
    <td><c:out value="${call.method}"/></td>
    <td></td>
    <td><c:out value="${call.selfDuration}"/>ms</td>
    <td><c:out value="${call.duration}"/>ms</td>
</tr>
<c:forEach items="${call.children}" var="child">
    <am:method-call-row call="${child}"/>
</c:forEach>