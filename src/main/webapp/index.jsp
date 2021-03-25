<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        $(function (){
            $("#btn").click(function (){
                $.ajax({
                    //url:"test/returnVoid-ajax.do",
                    //url:"test/returnStudentJson.do",
                    url:"test/returnStudentJsonArray.do",
                    //url:"test/returnStringData.do",
                    data:{
                        name:"zhangsan",
                        age:20
                    },
                    type:"post",
                    //dataType:"json",//可以不写
                    dataType:"text",
                    success: function (resp) {
                        /*$.each(resp,function (i,n){
                            alert(n.name+"    "+n.age)
                        })*/
                        alert(resp)
                    }
                })
            })
        })
    </script>
</head>
<body>
<p><a href="test/some.do">发起some.do的get请求</a></p>
<p><a href="test/other.do">发起other.do的get请求</a></p>

<form action="test/other.do" method="get">
    <input type="submit" value="get请求other.do">
</form>

<form action="test/others.do" method="post">
    姓名:<input type="text" name="rname">
    年龄:<input type="text" name="rage">
    <input type="submit" value="post请求others.do">
</form>
<a href="test/others.do">发起others.do的get请求带参数</a>

<form action="test/receiveObject.do" method="post">
    姓名:<input type="text" name="name">
    年龄:<input type="text" name="age">
    <input type="submit" value="提交参数">
</form>

<form action="test/returnString.do" method="post">
    姓名:<input type="text" name="name">
    年龄:<input type="text" name="age">
    <input type="submit" value="提交参数">
</form><br>

<button id="btn">发起ajax请求</button>

<img src="images/MainBG.jpg" alt="资源加载失败" width="1920px" height="1080px">
</body>
</html>
