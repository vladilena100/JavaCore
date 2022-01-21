<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body style="min-width: 100px">
<div class="container col-xl-11">
    <div class="m-2 flex-content">
        <div class="text-right">
            ${auth_user.firstName} ${auth_user.lastName} (<a href="${pageContext.request.contextPath}/logout">Logout</a>)
        </div>
        <div class="text-left">
            <h1 class="text-dark" id="${action}">${action} user</h1>
        </div>
    </div>
    <div class="text-left p-3 clearfix col-xl-5 col-8 col-sm-11">
        <form:form action="${request}" method="post" id="userForm" modelAttribute="user">
            <div class="form-group row">
                <div class="col-8 col-xl-9">
                    <div class="col-8 col-xl-9">
                        <input type="text" class="form-control" id="id" placeholder="user" name="id" hidden
                        <c:choose>
                        <c:when test="${action == 'Edit'}">
                               value="${id}"
                        </c:when>
                        </c:choose>
                        >
                    </div>
                </div>
            </div>
            <div class="form-group row">

                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="login" class="col-xl-3 col-4 col-form-label-sm">Login</label>
                            <input type="text" class="form-control" id="login" placeholder="user" name="login"
                                   min="2" maxlength="64" tabindex="1" readonly value="${user.login}">
                            <small id="invalidLogin" class="output"></small>
                        </c:when>
                        <c:otherwise>
                            <label for="login" class="col-xl-3 col-4 col-form-label-sm">Login</label>
                            <input type="text" class="form-control" id="login" placeholder="user" name="login"
                                   min="2" maxlength="64" value="${param.login}" tabindex="1" required>
                            <small id="invalidLogin" class="output"></small>
                            <form:errors path="login" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="password" class="col-xl-3 col-4 col-form-label-sm">Password</label>
                            <input type="password" class="form-control" id="password" aria-describedby="pass"
                                   placeholder="4657512" min="5" maxlength="64" tabindex="2"
                                   name="password">
                            <form:errors path="password" cssClass="alert-warning"/>
                        </c:when>
                        <c:otherwise>
                            <label for="password" class="col-xl-3 col-4 col-form-label-sm">Password</label>
                            <input type="password" class="form-control" id="password" aria-describedby="pass"
                                   placeholder="4657512" min="5" maxlength="64" tabindex="2"
                                   name="password" required>
                            <form:errors path="password" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="passwordAgain">Confirm password</label>
                            <input  type="password" class="form-control" id="passwordAgain"
                                   aria-describedby="passwordAgain"
                                   placeholder="4657512" min="5" maxlength="64" tabindex="2"
                                   name="passwordAgain"/>
                            <form:errors path="passwordAgain" cssClass="alert-warning"/>
                        </c:when>
                        <c:otherwise>
                            <label for="passwordAgain">Confirm password</label>
                            <input type="password" class="form-control" id="passwordAgain"
                                   aria-describedby="passwordAgain"
                                   placeholder="4657512" min="5" maxlength="64" tabindex="2"
                                   name="passwordAgain" required>
                            <form:errors path="passwordAgain" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="email" class="col-xl-3 col-4 col-form-label-sm">Email</label>
                            <input type="email" class="form-control" id="email" placeholder="some@email.com"
                                   name="email" value="${user.email}" tabindex="4" required>
                            <form:errors path="email" cssClass="alert-warning"/>
                        </c:when>
                        <c:otherwise>
                            <label for="email" class="col-xl-3 col-4 col-form-label-sm">Email</label>
                            <input type="email" class="form-control" id="email" placeholder="some@email.com"
                                   name="email" value="${param.email}" tabindex="4" required>
                            <form:errors path="email" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="firstName" class="col-xl-3 col-4 col-form-label-sm">First Name</label>
                            <input type="text" class="form-control" min="2" maxlength="64" id="firstName" tabindex="5"
                                   placeholder="firstName"
                                   name="firstName" required value="${user.firstName}">
                            <form:errors path="firstName" cssClass="alert-warning"/>
                        </c:when>
                        <c:otherwise>
                            <label for="firstName" class="col-xl-3 col-4 col-form-label-sm">First Name</label>
                            <form:input path="firstName" type="text" class="form-control" min="2" maxlength="64" id="firstName" tabindex="5"
                                   placeholder="FirstName" name="firstName" value="${param.firstName}" required="required"/>
                            <form:errors path="firstName" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="lastname" class="col-xl-3 col-4 col-form-label-sm">Last Name</label>
                            <input type="text" class="form-control" id="lastName" placeholder="LastName" name="lastName"
                                   required value="${user.lastName}" min="2" maxlength="64" tabindex="6">
                            <form:errors path="lastName" cssClass="alert-warning"/>
                        </c:when>
                        <c:otherwise>
                            <label for="lastname" class="col-xl-3 col-4 col-form-label-sm">Last Name</label>
                            <input type="text" class="form-control" id="lastName" placeholder="LastName"
                                   name="lastName" value="${param.lastName}" required min="2" maxlength="64" data-class="invalid-input">
                            <form:errors path="lastName" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <label for="birthday" class="col-xl-3 col-4 col-form-label-sm">Birthday</label>
                            <input type="date" class="form-control" id="birthday"
                                   name="birthday" required data-class="invalid-input" tabindex="7"
                                   min="1900-01-01" value="${birthday}">
                            <form:errors path="birthday" cssClass="alert-warning"/>
                        </c:when>
                        <c:otherwise>
                            <label for="birthday" class="col-xl-3 col-4 col-form-label-sm">Birthday</label>
                            <input type="date" class="form-control" id="birthday"
                                   name="birthday" value="${param.birthday}" required data-class="invalid-input"
                                   min="1900-01-01" max="${birthday.max}" tabindex="7">
                            <form:errors path="birthday" cssClass="alert-warning"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-7 px-3 col-8 col-xl-9 required-field-block">
                    <label for="select" class="col-xl-3 col-4 col-form-label-sm">Role</label>
                    <select id="select" class="form-control" name="role" value="${user.role.name}" required>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.name}"
                                    <c:if test="${role.name eq selectedRoleId}">selected="selected"</c:if>>
                                    ${role.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <form:errors path="role" cssClass="alert-warning"/>
            </div>
            <br>
            <div>
                <a class="btn btn-danger float-right text-center"
                   href="${pageContext.request.contextPath}/users">Cancel</a>
                <input type="submit" class="btn btn-primary float-right mr-1 text-center" value="Ok">
            </div>
            </form:form>
    </div>
</div>
<script>
    birthday.max = new Date().toLocaleDateString('en-ca');
</script>
</body>
</html>