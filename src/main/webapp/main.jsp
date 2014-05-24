<%@ page language="java" pageEncoding="UTF-8"%>  
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>hello</title>  
</head>  
<body>  
    <form action="generateQrcode" method="post">  
        姓名<input type="text" name="name">
        地区<input type="text" name="area">  
        手机号<input type="text" name="phoneNum">    
        <input type="submit" value="生成二维码">  
    </form>  
</body>  
</html>  