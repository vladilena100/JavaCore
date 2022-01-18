<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
  <title>Form Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous">

</head>
<body style="min-width: 350px">
<div class="container">
  <div class="row justify-content-center align-items-center m-1" style="min-height: 100vh">
    <form:form class="col-md-6 col-lg-4 text-left border border-dark p-3 px-lg-2 clearfix" action="/login" method="POST" modelAttribute="userDTO">
      <div class="form-group row">
        <label for="login" class="col-md-4 col-lg-3 col-xl-2 col-form-label-sm">Login</label>
        <div class="col-md-8 col-lg-9 col-xl-10">
          <input type="text" class="form-control" id="login" placeholder="user" value="${userDTO.login}" name="login"/>
        </div>
      </div>
      <div class="form-group row">
        <label for="password" class="col-md-4 col-lg-3 col-xl-2 col-form-label-sm">Password</label>
        <div class="col-md-8 col-lg-9 col-xl-10">
          <input type="password" class="form-control" id="password" aria-describedby="pass" placeholder="4657512" name="password" value="${userDTO.password}"/>
        </div>
      </div>
      <c:if test="${param.error != null}">
        <p style="color: red">Wrong login or password</p>
      </c:if>
      <a href="${pageContext.request.contextPath}/registration" class="btn btn-primary float-left">Registration</a>
      <button type="submit" class="btn btn-primary float-right">Sign in</button>
    </form:form>
  </div>
</div>
</body>
</html>
