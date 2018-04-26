<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>redismain</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/icon.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/demo/demo.css">
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
	
</head>
<body>
<h2>数据表格中的行边框</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>这个示例教我们如何改变表格的行边框样式.</div>
	</div>
	<div style="margin:10px 0;">
		<span>边框:</span>
		<select onchange="changeBorder(this.value)">
			<option value="lines-both">都有</option>
			<option value="lines-no">无边框</option>
			<option value="lines-right">右边框</option>
			<option value="lines-bottom">下边框</option>
		</select>
		<span>斑马线:</span>
		<input type="checkbox" onclick="$('#dg').datagrid({striped:$(this).is(':checked')})">
	</div>
	<table id="dg" class="easyui-datagrid" title="数据表格中的行边框" style="width:705px;height:250px"
			data-options="singleSelect:true,fitColumns:true,url:'createJson',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">编号</th>
				<th data-options="field:'productid',width:100">产品编号</th>
				<th data-options="field:'listprice',width:80,align:'right'">市场价</th>
				<th data-options="field:'unitcost',width:80,align:'right'">成本价</th>
				<th data-options="field:'attr1',width:250">描述</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		function changeBorder(cls){
			$('#dg').datagrid('getPanel').removeClass('lines-both lines-no lines-right lines-bottom').addClass(cls);
		}
	</script>
	<style type="text/css">
		.lines-both .datagrid-body td{
		}
		.lines-no .datagrid-body td{
			border-right:1px dotted transparent;
			border-bottom:1px dotted transparent;
		}
		.lines-right .datagrid-body td{
			border-bottom:1px dotted transparent;
		}
		.lines-bottom .datagrid-body td{
			border-right:1px dotted transparent;
		}
	</style>
	
</body>
</html>