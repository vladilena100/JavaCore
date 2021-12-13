<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add User</h2><br />

<form method="post" action="">

    <label><input type="text" name="Login" required></label>Login<br>

    <label><input type="password" name="Password" required></label>Password<br>

    <label><input type="password" name="PasswordAgain" required></label>Password again<br>

    <label><input type="email" name="Email" required></label>Email<br>

    <label><input type="text" name="FirstName" required></label>First Name<br>

    <label><input type="text" name="LastName" required></label>Last Name<br>

    <label><input type="date" name="Birthday" required></label>Birthday<br>

    <select name="Role" required>
        <option value="1">USER</option>
        <option value="2">ADMIN</option>
    </select>

    <input type="submit" value="Ok" name="Ok"><br>

</form>
</body>
</html>
