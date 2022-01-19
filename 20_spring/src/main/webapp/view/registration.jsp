<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

</head>
<body style="min-width: 100px">
<div class="container col-xl-11">
    <div class="text-left">
        <h1 class="text-dark">Registration</h1>
    </div>
    <div class="text-left p-3 clearfix col-xl-5 col-8 col-sm-11">
        <form:form action="/registration" method="POST" id="userForm" modelAttribute="user">
            <div class="form-group row">
                <label for="login" class="col-xl-3 col-4 col-form-label-sm">Login</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="login" type="text" class="form-control" id="login" placeholder="user" name="login"
                                min="4" maxlength="64" tabindex="1"
                                data-invalid-message="Login is not correct" data-valid-message="Login is correct"
                                data-id="invalidLogin" data-class="invalid-input" required="required"/>
                    <small id="invalidLogin" class="output"></small>
                    <form:errors path="login" cssClass="alert-warning"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <label for="password" class="col-xl-3 col-4 col-form-label-sm">Password</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="password" type="password" class="form-control" id="password"
                                aria-describedby="pass"
                                placeholder="4657512" min="4" maxlength="64" tabindex="2"
                                name="password" data-invalid-message="Password length must be greater than 4"
                                data-valid-message="Password is correct" data-id="invalidPassword"
                                data-class="invalid-input" required="required"/>
                    <small id="invalidPassword" class="output"></small>
                    <form:errors path="password" cssClass="alert-warning"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <label for="passwordRepeat" class="col-xl-3 col-4 col-form-label-sm">Confirm password</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="passwordAgain" type="password" class="form-control" id="passwordRepeat"
                                aria-describedby="passwordConfirm"
                                placeholder="password" name="passwordRepeat" min="4" maxlength="64"
                                data-invalid-message="Password doesn't match"
                                data-valid-message="Passwords match" tabindex="3"
                                data-class="invalid-input" data-id="invalidRPassword" required="required"/>
                    <small id="invalidRPassword" class="output"></small>
                    <form:errors path="passwordAgain" cssClass="alert-warning"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <label for="email" class="col-xl-3 col-4 col-form-label-sm">Email</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="email" type="email" class="form-control" id="email" placeholder="some@email.com"
                                name="email" tabindex="4"
                                data-invalid-message="Email is not correct"
                                data-valid-message="Email is correct"
                                data-class="invalid-input" data-id="invalidEmail" required="required"/>
                    <small id="invalidEmail" class="output"></small>
                    <form:errors path="email" cssClass="alert-warning"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <label for="firstName" class="col-xl-3 col-4 col-form-label-sm">First Name</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="firstName" type="text" class="form-control" min="2" maxlength="64" id="firstName"
                                tabindex="5"
                                placeholder="FirstName" data-class="invalid-input"
                                name="firstName" data-invalid-message="First name is not correct"
                                data-valid-message="First name is correct" data-id="invalidFname" required="required"/>
                    <small id="invalidFname" class="output"></small>
                    <form:errors path="firstName" cssClass="alert-warning"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <label for="lastname" class="col-xl-3 col-4 col-form-label-sm">Last Name</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="lastName" type="text" class="form-control" id="lastname" placeholder="LastName"
                                name="lastName" min="2" maxlength="64" data-class="invalid-input"
                                data-invalid-message="Last name is not correct" tabindex="6"
                                data-valid-message="Last name is correct" data-id="invalidLname" required="required"/>
                    <small id="invalidLname" class="output"></small>
                    <form:errors path="lastName" cssClass="alert-warning"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <label for="birthday" class="col-xl-3 col-4 col-form-label-sm">Birthday</label>
                <div class="col-8 col-xl-9 required-field-block">
                    <form:input path="birthday" type="date" class="form-control" id="birthday"
                                name="birthday" data-class="invalid-input"
                                min="1900-01-01" max="${birthday.max}" tabindex="7"
                                data-invalid-message="Date is not correct"
                                data-valid-message="Date is correct" data-id="invalidDate" required="required"/>
                    <small id="invalidDate" class="output"></small>
                    <form:errors path="birthday" cssClass="alert-warning"/>
                </div>
            </div>
            <div>
                <div class="g-recaptcha" data-sitekey="6LemaDgcAAAAAOxRzCahX9B2iL2xOWuPuOY63XoD"></div>
                <c:if test="${captchaError != null}">
                    <small class="text-danger">${captchaError}</small>
                </c:if>
            </div>
            <br>
            <div>
                <a class="btn btn-danger float-right text-center"
                   href="${pageContext.request.contextPath}/login">Cancel</a>
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