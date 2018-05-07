<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<br>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>search</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/icon.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jquery-easyui-1.5.3/demo/demo.css">
 <script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<link rel="shortcut icon" href="http://bss.ctyun.com.cn:80/img/favicon.ico">	
</head>
<div>下面是使用模糊查询方式：</div>
    <input id="ss" class="easyui-searchbox" style="width:250px"
        data-options="searcher:qq,prompt:'Please Input Value',menu:'#mm'"></input>
    <div id="mm" style="width:120px">
        <div data-options="index:'0'">0</div>
        <div data-options="index:'1'">1</div>
        <div data-options="index:'2'">2</div>
        <div data-options="index:'3'">3</div>
        <div data-options="index:'4'">4</div>
        <div data-options="index:'5'">5</div>
        <div data-options="index:'6'">6</div>
        <div data-options="index:'7'">7</div>
        <div data-options="index:'8'">8</div>
        <div data-options="index:'9'">9</div>
        <div data-options="index:'10'">10</div>
        <div data-options="index:'11'">11</div>
        <div data-options="index:'12'">12</div>
        <div data-options="index:'13'">13</div>
        <div data-options="index:'14'">14</div>
        <div data-options="index:'15'">15</div>
    </div>
</br>
	
    <div >  
        <table id="dg" class="easyui-datagrid"  fitColumns="true" toolbar="#tb" style="width: 600px; height: 300px" >  
            <thead>  
                <tr>  
                	<th data-options="field:'checkbox',width:148,checkbox: true,sortable:true">checkbox</th>
                    <th data-options="field:'key',width:148,sortable:true">key</th>
                </tr>
            </thead> 
        </table>  
    </div>  
    <div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:removeit()">remove</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit()">edit</a>
	</div>
	<div id="dlg" class="easyui-dialog" title="编辑查询结果" style="width:400px;height:200px;padding:10px" closed="true">
		
	</div>
<div>下面是使用模糊删除的方式：</div>
<input id="inputa" class="easyui-searchbox" style="width:250px"
       data-options="searcher:deleteKeys,prompt:'Please Input Value',menu:'#selecta'"></input>
<div id="selecta" style="width:120px">
    <div data-options="index:'0'">0</div>
    <div data-options="index:'1'">1</div>
    <div data-options="index:'2'">2</div>
    <div data-options="index:'3'">3</div>
    <div data-options="index:'4'">4</div>
    <div data-options="index:'5'">5</div>
    <div data-options="index:'6'">6</div>
    <div data-options="index:'7'">7</div>
    <div data-options="index:'8'">8</div>
    <div data-options="index:'9'">9</div>
    <div data-options="index:'10'">10</div>
    <div data-options="index:'11'">11</div>
    <div data-options="index:'12'">12</div>
    <div data-options="index:'13'">13</div>
    <div data-options="index:'14'">14</div>
    <div data-options="index:'15'">15</div>
</div>

<script type="text/javascript"> 
    	var id; 
        function qq(value,index){
            var lengthValue = value.length;
            if (lengthValue<5) {
                alert("至少5个字符");
            } else {
                id = index;
                $.ajax({
                    method : 'GET',
                    url : "select/VagueKey?index="+index+"&ke="+value,
                    async : false,
                    success : function(data) {
                        var data_array = new Array(data.length);
                        for (var i = 0;i < data.length;i++) {
                            var data_ = {"key":data[i]};
                            data_array[i] = data_;
                        }
                        var data_u = {"total":data.length,"rows":data_array};
                        $('#dg').datagrid({
                            title: 'key查询结果',
                            data: data_array,
                        });
                    }
                });
            }
        } 
		 function removeit(){
		   var deletedData = $('#dg').datagrid('getChecked');
			  for (var i = deletedData.length - 1; i >= 0; i--) {
			    var rowIndex = $('#dg').datagrid('getRowIndex', deletedData[i]);
			    $.ajax({
				        method : 'GET',
				        url : "delete/value?index="+id+"&key="+deletedData[i].key,
				        async : true
					});
			    $('#dg').datagrid('deleteRow', rowIndex);
			  }
		  }
		function edit(){
		   /* var selectData = $('#dg').datagrid('getChecked');
			  for (var i = selectData.length - 1; i >= 0; i--) {
			    var rowIndex = $('#dg').datagrid('getRowIndex', selectData[i]);
			    $.ajax({
				        method : 'GET',
				        url : "select/value?index="+id+"&key="+selectData[i].key,
				       success: function (data) {
				       		
							switch (data.dateType) {
								case 'string': data.stringValue
								break;
								case 'list': break;
								case 'set': break;
								case 'zset': break;
								case 'hash': break;
							}
	                	},
	                	error : function() {
	                    	//alert("异常！");
	               		}
				});
			  } */
			  var selectData = $('#dg').datagrid('getChecked');
			  if (selectData.length > 1) {
				  	$.messager.show({
						title:'错误选择',
						msg: '选择数量太多，请只选择一个',
						timeout:500,
						showType:'show',
						style:{
							left:1, // 与左边界的距离
							top:document.body.clientHeight-400 // 与顶部的距离
					    }
					});
			  } else {
			  	var bodyData = "index="+id+"&key="+selectData[0].key;
			  	$.ajax({
				        type: "GET",//方法类型
				        url : "select/value",
				        dataType: "json",//预期服务器返回的数据类型
				        data: bodyData,
				        success: function (data) {
							switch (data.dateType) {
								case 'STRING': 
									$.messager.show({
									    title:'string',
									    msg: data.stringValue,
									    showType:'fade',
									    timeout:-1,
									    style:{
											left:2, // 与左边界的距离
											top:document.body.clientHeight-300 // 与顶部的距离
									    }
									});
									break;
								case 'LIST': 
									$.messager.show({
									    title:'list',
									    msg: data.list,
									    showType:'fade',
									    timeout:-1,
									    style:{
											left:2, // 与左边界的距离
											top:document.body.clientHeight-300 // 与顶部的距离
									    }
									});
									break;
								case 'SET': 
									$.messager.show({
									    title:'set',
									    msg: data.set,
									    showType:'fade',
									    timeout:-1,
									    style:{
											left:2, // 与左边界的距离
											top:document.body.clientHeight-300 // 与顶部的距离
									    }
									});
									break;
								case 'ZSET': 
									$.messager.show({
									    title:'Zset',
									    msg: data.Zset,
									    showType:'fade',
									    timeout:-1,
									    style:{
											left:2, // 与左边界的距离
											top:document.body.clientHeight-300 // 与顶部的距离
									    }
									});
									break;
								case 'HASH': 
									$.messager.show({
									    title:'hashMap',
									    msg: data.map,
									    showType:'fade',
									    timeout:-1,
									    style:{
											left:2, // 与左边界的距离
											top:document.body.clientHeight-300 // 与顶部的距离
									    }
									});
									break;
								default :
									alert("未知类型");
							}
	                	},
	                	error : function(data) {
	                    	console.log("aa",data);
	               		}
				});
			  }
		  }

    </script>
    <script >
        var id;
        function deleteKeys(value,index) {
            var lengthValue = value.length;
            if (lengthValue<5) {
                alert("至少5个字符");
            } else {
                id = index;
                $.ajax({
                    method: 'GET',
                    url: "delete/valuesbykeys?index=" + id + "&keyVague=" + value,
                    async: true,
                    success: function (data) {
                        alert("success del " + value);
                    },
                    error: function (data) {
                        alert("del fail " + value);
                    }
                });
            }
        }
    </script>
</html>