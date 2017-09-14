<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${requestScope.random == 1}">
		<c:import url="contentFeed.jsp"></c:import>
	</c:when>
	<c:when test="${requestScope.random == 2}">
		<c:import url="play_and_earn.jsp"></c:import>
	</c:when>
</c:choose>
