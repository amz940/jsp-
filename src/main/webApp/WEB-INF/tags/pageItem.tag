<%@ attribute name="pageNumber" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8" %>

<c:url value="/list" var="pageLink">
    <c:param name="page" value="${pageNumber}"/>
    <c:if test="${not empty param.search}">
        <c:param name="search" value="${param.search}"/>
    </c:if>
    <c:if test="${not empty param.category}">
        <c:param name="category" value="${param.category}"/>
    </c:if>
</c:url>
<li class="page-item">
    <a class="page-link ${pageNumber eq pageInfo.currentPageNum ? 'active' : ''}"
       href="${pageLink}">
        <jsp:doBody></jsp:doBody>
    </a>
</li>