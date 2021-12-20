<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>User info</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <%@ taglib prefix="ex" uri="/WEB-INF/userListTag.tld" %>
</head>
<body>
<div class="container">
    <div style="text-align: right">
        ${sessionScope.user.firstName} ${sessionScope.user.lastName}(<a
            href="${pageContext.request.contextPath}/logout">Logout</a>)
    </div>
    <div class="mt-5 text-left ml-2">
        <a href="${pageContext.request.contextPath}/users/add">Add new user</a>
    </div>
    <div>
        <ex:users_list users="${users}"/>
    </div>
    <style>
        #users {
            width: 70%;
            margin: 0 auto;
        }
    </style>

</div>
</body>
</html>
