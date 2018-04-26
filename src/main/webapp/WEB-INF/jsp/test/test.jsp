<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>search</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/icon.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/demo/demo.css">
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
	
</head>
<body>
<div>
  <table id="dg" toolbar="#tb"></table>
  <div id="tb">
    <a id="delete" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteRows()">删除</a>
  </div>
</div>
</body>
<script type="text/javascript">
	var dataStr = '{"total": 7, "rows": [{"test": 1}, {"test": 2}, {"test": 3}, {"test": 4}, {"test": 5}, {"test": 6}, {"test":7}]}';
	var data = $.parseJSON(dataStr);
	 
	$(function () {
	  $('#dg').datagrid({
	    width: 'auto',
	    height: 'auto',
	    title: 'datagrid多行删除测试',
	    fitColumns: true,
	    rownumbers: true,
	    columns: [[
	      {
	        field: 'checkbox',
	        checkbox: true,
	      },
	      {
	        field: 'test',
	        title: 'test',
	        width: '100px',
	      }
	    ]],
	  });
	 
	  $('#dg').datagrid('loadData', data);
	 
	});
	 
	function deleteRows() {
	  var deletedData = $('#dg').datagrid('getChecked');
	  for (var i = deletedData.length - 1; i >= 0; i--) {
	    var rowIndex = $('#dg').datagrid('getRowIndex', deletedData[i]);
	    $('#dg').datagrid('deleteRow', rowIndex);
	  }
	}
</script>
</html>
