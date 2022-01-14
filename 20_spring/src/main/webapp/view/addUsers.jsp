<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<body>

<h2>Add user</h2>
<br>

<form:form action="addUser" modelAttribute="user">

    <form:hidden path="id"/>

    Login <form:input path="login"/>
    <br><br>
    Password <form:input path="password"/>
    <br><br>
    Password again <form:input path="passwordAgain"/>
    <br><br>
    Email <form:input path="email"/>
    <br><br>
    First name <form:input path="firstName"/>
    <br><br>
    Last name <form:input path="lastName"/>
    <br><br>
    Birthday <form:input path="birthday"/>
    <br><br>
    Role <form:input path="role"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>

</body>
</html>