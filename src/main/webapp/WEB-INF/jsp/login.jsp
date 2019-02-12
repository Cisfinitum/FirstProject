<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form name="loginForm" method="POST" action="<c:url value="/j_spring_security_check" />">
    <table>
        <tr>
            <td>
                <label>Имя<input  type="text" name="j_username" /></label>
            </td>
        </tr>
        <tr>
            <td>
                <label>Пароль<input type="password" name="j_password" /></label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Login" />
                <input type="reset" value="Reset" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
