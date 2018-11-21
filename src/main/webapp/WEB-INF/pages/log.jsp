<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>
	<h1>日志列表页面</h1>
	<h2><%=new java.util.Date()%></h2>
	<div id="containerId">
		<table border="1" width="100%" cellpadding="2" cellspacing="0">
			<thead>
				<tr>
					<th>id</th>
					<th>username</th>
					<th>createdTime</th>
				</tr>
			</thead>
			<tbody id="tbodyId">
				<tr>
					<td colspan="3">数据正在加载中......</td>
				<tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		//dom event页面元素加载完成后执行js函数
		window.onload = function() {
			doGetObjects();
			console.log("main thread working");
		}
		function doGetObjects() {
			//debugger //断点调试法(浏览器需要处于调试状态:f12)
			//1.创建ajax对象(ajax 应用的入口对象)
			var xhr = new XMLHttpRequest();//借助此对象实现与服务端的交互
			//console.log(xhr);
			console.log("xhr", xhr);
			//2.设置状态监听（与服务器的通讯状态） 		观察者模式！
			xhr.onreadystatechange = function() { //callback回调函数
				//console.log("onreadystatechange.readyState="+xhr.readyState);
				console.log("onreadystatechange");
				//4 表示通讯状态结束
				//200 表示服务端响应OK
				if (xhr.readyState == 4 && xhr.status == 200) {
					//输出响应结果（responseText 代表服务端响应的文本内容）
					//console.log(xhr.responseText);		//响应在控制台
					doHandleResponseResult(xhr.responseText);//响应在页面
				}
			}
			//3.发送请求
			doSendPostRequest(xhr);
		}
		
		//发送post请求
		function doSendPostRequest(xhr) {
			//3.执行open操作,打开连接
			var url = "doFindPageObjects.do"
			xhr.open("POST", url, true);//true表示异步(底层会启动工作线程)false表示同步
			//post请求必须设置此请求头
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			//4.执行请求发送操作
			xhr.send("pageCurrent=1");//POST请求send方法内部传数据
		}
		
		//发送get请求
		function doSendGetRequest(xhr) {
			//3.执行open，打开连接
			var url = "doFindPageObjects.do?pageCurrent=1"
			xhr.open("GET", url, true); //true表示异步，false表示同步
			//4.执行请求发送
			xhr.send(null); //GET请求send方法内部不能传数据
		}

		//处理响应结果
		function doHandleResponseResult(responseText) {
			//var div = document.getElementById("containerId");
			//div.innerHTML = responseText;
			//1.获取tbody对象，并清空tbody中内容
			var tBody = document.getElementById("tbodyId");
			tBody.innerHTML = "";
			//2.将json格式字符串转换为json格式的js对象
			var result = JSON.parse(responseText);//JsonResult
			console.log("result", result);
			//3.将数据呈现在页面上
			if (result.state == 1) {//OK
				doSetTableBodyRows(result, tBody);
			} else {//Error
				alert(result.message);
			}
		}

		//设置table的tbody内容
		function doSetTableBodyRows(result, tBody) {
			//3.1 获取result中的记录
			var records = result.data.records;//PageObject对象用的records
			//3.2 迭代记录
			for (var i = 0; i < records.length; i++) {//一行一个tr
				//3.2.1 创建tr对象
				var tr = document.createElement("tr");
				//3.2.2 创建td对象，并将td追加tr中
				tr.appendChild(doCreateTd(records[i].id));
				tr.appendChild(doCreateTd(records[i].username));
				tr.appendChild(doCreateTd(records[i].createdTime));
				//3.2.3 将tr对象追加tbody中
				tBody.appendChild(tr);
			}
		}

		function doCreateTd(value) {
			var td = document.createElement("td");
			td.innerText = value;
			return td;
		}
	</script>
</body>
</html>