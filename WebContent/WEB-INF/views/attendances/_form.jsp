<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="attendance_date">日付</label><br />
<input type="date" name="attendance_date" value="<fmt:formatDate value='${attendance.attendance_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="clockIn_time">出社時刻</label><br />
<input type="text" name="clockIn_time" value="<fmt:formatNumber value='${attendance.clockIn_time}' pattern='0000' />" />
<br /><br />

<label for="clockOut_time">退社時刻</label><br />
<input type="text" name="clockOut_time" value="<fmt:formatNumber value='${attendance.clockOut_time}' pattern='0000' />" />
<br /><br />

<label for="break_hour">休憩時間</label><br />
<input type="text" name="break_hour" value="<fmt:formatNumber value='${attendance.break_hour}' pattern='0000' />" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>