<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/required.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body style="min-width: 100px">
<div class="container col-xl-11">
    <div class="m-2">
        <div class="text-right">
            ${sessionScope.firstName} ${sessionScope.lastName} (<a href="/logout">Logout</a>)
        </div>
        <div class="text-left">
            <h1 class="text-dark" id="${action}">${action} user</h1>
        </div>
    </div>
    <div class="text-left p-3 clearfix col-xl-5 col-8 col-sm-11">
        <form action="${request}" method="post" id="userForm">
            <div class="form-group row">
                <div class="col-8 col-xl-9">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <input type="text" class="form-control" id="id" placeholder="user" name="id" hidden
                                   value="${user.id}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="id" placeholder="user" name="id" hidden>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label for="login" class="col-xl-3 col-4 col-form-label-sm">Login</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <input type="text" class="form-control" id="login" placeholder="user" name="login"
                                   min="2" maxlength="64" tabindex="1" readonly value="${user.login}"
                                   data-invalid-message="Login is not correct" data-valid-message="Login is correct"
                                   data-id="invalidLogin" data-class="invalid-input">
                            <small id="invalidLogin" class="output"></small>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="login" placeholder="user" name="login"
                                   min="2" maxlength="64" tabindex="1"
                                   data-invalid-message="Login is not correct" data-valid-message="Login is correct"
                                   data-id="invalidLogin" data-class="invalid-input">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidLogin" class="output"></small>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-xl-3 col-4 col-form-label-sm">Password</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <input type="password" class="form-control" id="password" aria-describedby="pass"
                           placeholder="4657512" min="5" maxlength="64" tabindex="2"
                           name="password" data-invalid-message="Password length must be greater than 4"
                           data-valid-message="Password is correct" data-id="invalidPassword"
                           data-class="invalid-input">
                    <c:if test="${action == 'Add'}">
                        <div class="required-icon mr-2">
                            <div class="text">*</div>
                        </div>
                    </c:if>
                    <small id="invalidPassword" class="output"></small>
                </div>
            </div>
            <div class="form-group row">
                <label for="passwordRepeat" class="col-xl-3 col-4 col-form-label-sm">Confirm password</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <input type="password" class="form-control" id="passwordRepeat" aria-describedby="passwordConfirm"
                           placeholder="password" name="passwordRepeat" min="5" maxlength="64"
                           data-invalid-message="Password doesn't match"
                           data-valid-message="Passwords match" tabindex="3"
                           data-class="invalid-input" data-id="invalidRPassword">
                    <c:if test="${action == 'Add'}">
                        <div class="required-icon mr-2">
                            <div class="text">*</div>
                        </div>
                    </c:if>
                    <small id="invalidRPassword" class="output"></small>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-xl-3 col-4 col-form-label-sm">Email</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <input type="email" class="form-control" id="email" placeholder="some@email.com"
                                   name="email"
                                   value="${user.email}" tabindex="4"
                                   data-pattern="^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$"
                                   data-invalid-message="Email is not correct"
                                   data-valid-message="Email is correct"
                                   data-class="invalid-input" data-id="invalidEmail">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidEmail" class="output"></small>
                        </c:when>
                        <c:otherwise>
                            <input type="email" class="form-control" id="email" placeholder="some@email.com"
                                   name="email" tabindex="4"
                                   data-pattern="^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$"
                                   data-invalid-message="Email is not correct"
                                   data-valid-message="Email is correct"
                                   data-class="invalid-input" data-id="invalidEmail">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidEmail" class="output"></small>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label for="firstname" class="col-xl-3 col-4 col-form-label-sm">First Name</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <input type="text" class="form-control" min="2" maxlength="64" id="firstName" tabindex="5"
                                   placeholder="FirstName"
                                   name="firstname" data-invalid-message="First name is not correct"
                                   data-valid-message="First name is correct" data-id="invalidFname"
                                   value="${user.firstName}" data-class="invalid-input">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidFname" class="output"></small>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" min="2" maxlength="64" id="firstName" tabindex="5"
                                   placeholder="FirstName" data-class="invalid-input"
                                   name="firstName" data-invalid-message="First name is not correct"
                                   data-valid-message="First name is correct" data-id="invalidFname">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidFname" class="output"></small>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label for="lastname" class="col-xl-3 col-4 col-form-label-sm">Last Name</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <input type="text" class="form-control" id="lastName" placeholder="LastName" name="lastName"
                                   value="${user.lastName}" min="2" maxlength="64" tabindex="6"
                                   data-invalid-message="Last name is not correct" data-class="invalid-input"
                                   data-valid-message="Last name is correct" data-id="invalidLname">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidLname" class="output"></small>
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="lastName" placeholder="LastName"
                                   name="lastName" min="2" maxlength="64" data-class="invalid-input"
                                   data-invalid-message="Last name is not correct" tabindex="6"
                                   data-valid-message="Last name is correct" data-id="invalidLname">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidLname" class="output"></small>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label for="birthday" class="col-xl-3 col-4 col-form-label-sm">Birthday</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <c:choose>
                        <c:when test="${action == 'Edit'}">
                            <input type="date" class="form-control" id="birthday" placeholder="1999-01-01"
                                   name="birthday" data-class="invalid-input" tabindex="7"
                                   min="1900-01-01" value="${user.birthday}"
                                   data-invalid-message="Date is not correct"
                                   data-valid-message="Date is correct" data-id="invalidDate">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidDate" class="output"></small>
                        </c:when>
                        <c:otherwise>
                            <input type="date" class="form-control" id="birthday"
                                   name="birthday" data-class="invalid-input"
                                   min="1900-01-01" tabindex="7"
                                   data-invalid-message="Date is not correct"
                                   data-valid-message="Date is correct" data-id="invalidDate">
                            <div class="required-icon mr-2">
                                <div class="text">*</div>
                            </div>
                            <small id="invalidDate" class="output"></small>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label for="select" class="col-xl-3 col-4 col-form-label-sm">Role</label>
                <div class="col-sm-7 px-3 col-8 col-xl-9 required-field-block">
                    <select id="select" class="form-control" name="role">
                        <option selected value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </div>
            </div>
            <div>
                <a class="btn btn-danger float-right text-center" href="${pageContext.request.contextPath}/users">Cancel</a>
                <input type="submit" class="btn btn-primary float-right mr-1 text-center" value="Ok">
            </div>
        </form>
    </div>
</div>
</body>
</html>
