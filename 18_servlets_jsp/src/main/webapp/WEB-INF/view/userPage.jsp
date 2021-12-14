<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>User info</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
<div class="container m-2 justify-content-center m-lg-2 m-md-2  col-xl-11">
    <div class="justify-content-center">
        <div class="mt-5 font-weight-bold">
            <h1 class="text-center">Hello, ${sessionScope.firstName}!</h1>
        </div>
        <div class="mt-3">
            <p class="text-center">Click <a href="/logout">here</a> to logout</p>
        </div>
    </div>
</div>
</body>
</html>