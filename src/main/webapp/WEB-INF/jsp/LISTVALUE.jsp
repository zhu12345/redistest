<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LISTVALUE</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/icon.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/demo/demo.css">
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
	
</head>
<script type="text/javascript">
	
</script>
<body>
		<div id="di1" title="注册" style="padding:10px" data-options="closable:true" >
			<a id="flash" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="flashList()">刷新</a>
			<a id="" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="removeList()">删除</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveList()">保存新增</a>
			<table id="listTable" class="easyui-datagrid" style="width: 100%;height: 500px;" >
				<thead>
					<tr>
						<th data-options="field:'key'" style="width: 20%;">名称</th>
						<th data-options="field:'value'" style="width: 80%;">值</th>
						
					</tr>					
				</thead>
				<tbody>
					<tr>
						
						<td>key</td><td><textarea id="text1" style="width: 100%;">${key }</textarea></td>
						</tr>
					<tr>
						<td>value</td><td><textarea id="text2" style="width: 100%;" ></textarea></td>
					</tr>
					<tr>
						<td>time</td><td><textarea id="text3" style="width: 100%;"></textarea></td>
					</tr>
				</tbody>
				
			</table>
			
		</div>
	<script>
		$(function(){
			//alert('${redisReturnValue.stringValue }');
			var value = '${redisReturnValue.list }';
			var expiretime = '${redisReturnValue.expiretime }';
			
			$('#listTable #text2').html(value);
			$('#listTable #text3').html(expiretime);
			/* if( expiretime < 0 ) {
				$('#listTable #text3').html("永久");
			}else
			if( expiretime  < 3600000 && expiretime > 60000 ) {
				$('#listTable #text3').html(expiretime/60000+"分");
			}else
			if(expiretime < 60000 && expiretime >=0 ) {
				$('#listTable #text3').html(expiretime/1000+"s");
			}else
			if( expiretime  > 3600000 && expiretime < 3600000*24 ) {
				$('#listTable #text3').html(expiretime/3600000+"时");
			}else 
			if( expiretime  > 3600000*24 && expiretime < 3600000*24*365 ) {
				$('#listTable #text3').html(expiretime/(3600000*24)+"天");
			}else{
				$('#listTable #text3').html(expiretime/(3600000*24*365)+"年");
			} */
		});
		function flashList(){
		  	var key = $('#listTable #text1').val();
		  	var id=$("#select option:selected").val();
		  	tableCreate(id, key);
		}
		function removeList(){
			var key = $('#listTable #text1').val();
		  	var id=$("#select option:selected").val();
		  	deletebyKey(id, key);
		}
		function saveList(){
			var key = $('#listTable #text1').val();
		  	var id=$("#select option:selected").val();
		  	var listValue = $('#listTable #text2').val();
		  	//var listValue = "aa,aaa,aaaa";
		  	var timeout = $('#listTable #text3').val();
		  	var URL ="save/listValue";
		  	$.messager.confirm('Confirm', 'Are you sure you want to save?', function(r) {
			    if (r) {
					$.ajax({ 
				        type: "POST",  
				        url: URL,
				        data: { listValue: listValue, index: id, key: key , timeout: timeout },
				        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				        async:false,
				        cache:false,
				        success: function(data){  
				           alert("OK success");  
				        },  
				        error: function(json){  
				            alert("error.");  
				        }  
				    }); 
			    }
			});
		}
	</script>
</body>
</html>