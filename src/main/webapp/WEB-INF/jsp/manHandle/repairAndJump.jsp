<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>doEvery</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/icon.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/demo/demo.css">
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
	<link rel="shortcut icon" href="http://bss.ctyun.com.cn:80/img/favicon.ico">
</head>
<body>
<a>vms执行进度</a><div id="progressbar" class="easyui-progressbar" style="width:400px;"></div>
<div> cook样例："UM_distinctid=1628fd6da9625a-0aa86e3bfbcaf8-4c584130-ff000-1628fd6da981ed; JSESSIONID=DA955859776BD2D36191019A1C8B629C; loginUserId=e7f906a4d3ce48ed8a0406f57851ab32" </div>
<div>
		<div class="easyui-panel" title="VMS2.0修复与跳过" data-options="fit:true">
		<div style="padding:10px 0 10px 60px">
	    <form id="ff" action="vms/repairAndJump" method="post" >
	    	<table>
	    		<tr>
	    			<td>cook:</td>
	    			<td><input class="easyui-validatebox" type="text" name="cook" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>bizid:</td>
	    			<td><textarea class="easyui-validatebox" name="bizids" style="width:300px;height:100px;" data-options="required:true"></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>pd:</td>
	    			<td>
	    				<select class="easyui-combobox" name="pd" style="width:200px;">
	    					<option value="0">修复</option>
	    					<option value="1">跳过</option>
	    				</select>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>
</div>
<div><br></div>
<a>HWS执行进度</a><div id="progressbarhws" class="easyui-progressbar" style="width:400px;"></div>
<div>
		<div class="easyui-panel" title="HWS包周期修复与取消" data-options="fit:true">
		<div style="padding:10px 0 10px 60px">
	    <form id="ffHWS" action="hws/repairAndCancel" method="post" >
	    	<table>
	    		<tr>
	    			<td>bizid:</td>
	    			<td><textarea class="easyui-validatebox" name="bizids" style="width:300px;height:100px;" data-options="required:true"></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>pd:</td>
	    			<td>
	    				<select class="easyui-combobox" name="pd" style="width:200px;">
	    					<option value="0">修复</option>
	    					<option value="1">取消</option>
	    				</select>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormHWS()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormHWS()">重置</a>
	    </div>
	</div>
</div>
</body>

<script type="text/javascript">
	function submitForm(){
		var key = _getRandomString(32);
		var bodyData = $('#ff').serialize()+"&key="+key;
		$('#progressbar').progressbar('setValue', 0);
        var progressTime = window.setInterval(function () {
                    start(key);
                }, 3000);
        $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "vms/repairAndJump" ,//url
                data: bodyData,
                success: function (result) {
                    start(key);
                	window.clearTimeout(progressTime);
                },
                error : function() {
                    start(key);
                    window.clearTimeout(progressTime);
                }
            });
	}
	function clearForm(){
		$('#ff').form('clear');
	}
	function submitFormHWS(){
		var key = _getRandomString(32);
		var bodyData = $('#ffHWS').serialize()+"&key="+key;
		$('#progressbarhws').progressbar('setValue', 0);
        var progressTime = window.setInterval(function () {
                    startHWS(key);
                }, 3000);
        $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "hws/repairAndCancel" ,//url
                data: bodyData,
                success: function (result) {
                    startHWS(key);
                	window.clearTimeout(progressTime);
                },
                error : function() {
                    startHWS(key);
                    window.clearTimeout(progressTime);
                }
            });
	}
	function clearFormHWS(){
		$('#ffHWS').form('clear');
	}
	function _getRandomString(len) {  
	    len = len || 32;  
	    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1  
	    var maxPos = $chars.length;  
	    var pwd = '';  
	    for (i = 0; i < len; i++) {  
	        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));  
	    }  
	    return pwd;  
	}  
	function start(key){
			console.log("start");
			var value = $('#progressbar').progressbar('getValue');
			var key_budile = "key="+key;
				$.ajax({
	            //几个参数需要注意一下
	                type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: "getCont" ,//url
	                data: key_budile,
	                success: function (data) {
						value = ((data.current/data.total)*100).toFixed(2);
						$('#progressbar').progressbar('setValue', value);
	                },
	                error : function() {
	                    alert("异常！");
	                }
	          	});
	};
	function startHWS(key){
			console.log("start");
			var value = $('#progressbarhws').progressbar('getValue');
			var key_budile = "key="+key;
				$.ajax({
	            //几个参数需要注意一下
	                type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: "getCont" ,//url
	                data: key_budile,
	                success: function (data) {
						value = ((data.current/data.total)*100).toFixed(2);
						$('#progressbarhws').progressbar('setValue', value);
	                },
	                error : function() {
	                    alert("异常！");
	                }
	          	});
	};
</script>
</html>
