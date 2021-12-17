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
</head>
<body>
<div class="container m-2 justify-content-center m-lg-2 m-md-2  col-xl-11">
    <div class="justify-content-center">
        <div class="mt-5 font-weight-bold">
            <h1 class="text-center">Hello, ${sessionScope.user.firstName}!</h1>
        </div>
        <div class="mt-3">
            <p class="text-center">Click <a href="${pageContext.request.contextPath}/logout">here</a> to logout</p>
        </div>
    </div>
</div>
</body>
</html>