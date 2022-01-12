<%@ page contentType="text/html;charset=UTF-8" %>
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
    <form class="col-md-6 col-lg-4 text-left border border-dark p-3 px-lg-2 clearfix"
          action="${pageContext.request.contextPath}/login" method="post">
      <div class="form-group row">
        <label for="exampleInputEmail1" class="col-md-4 col-lg-3 col-xl-2 col-form-label-sm">Login</label>
        <div class="col-md-8 col-lg-9 col-xl-10">
          <input type="text" class="form-control" id="exampleInputEmail1" placeholder="user" name="login">
        </div>
      </div>
      <div class="form-group row">
        <label for="exampleInputPassword1" class="col-md-4 col-lg-3 col-xl-2 col-form-label-sm">Password</label>
        <div class="col-md-8 col-lg-9 col-xl-10">
          <input type="password" class="form-control" id="exampleInputPassword1" aria-describedby="pass"
                 placeholder="4657512" name="password">
          <p><span class="colortext">${error}</span></p>
        </div>
      </div>
      <button type="submit" class="btn btn-primary float-right">Sign in</button>
    </form>
  </div>
</div>
</body>
</html>
