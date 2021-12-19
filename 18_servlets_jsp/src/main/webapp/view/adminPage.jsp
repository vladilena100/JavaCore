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
    <div class="text-right mt-3">
        ${sessionScope.user.firstName} ${sessionScope.user.lastName}(<a
            href="${pageContext.request.contextPath}/logout">Logout</a>)
    </div>
    <div class="mt-5 text-left ml-2">
        <a href="${pageContext.request.contextPath}/users/add">Add new user</a>
    </div>
    <table class="table table-striped mt-3 border border-dark">
        <thead class="thead-dark text-left">
        <tr>
            <th scope="col">Login</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Age</th>
            <th scope="col">Role</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody class="border border-dark">
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.role.name}</td>
                <td style="max-width: 100px">
                    <div class="row justify-content-center">
                        <div class="mr-3">
                            <a href="users/edit/${user.id}">Edit</a>
                        </div>
                        <c:choose>
                            <c:when test="${sessionScope.user.id != user.id}">
                                <div class="ml-3">
                                    <a href="users/delete/${user.id}"
                                       onclick="return confirm('Are you sure?')">Delete</a>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
