<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="shortcut icon" href="${pageContext.request.contextPath }/Image/favicon.ico">
</head>
<script type="text/javascript">	
    	$(function(){
    	    $('#messageInfoAddTree').tree({
					onClick: function(node){
					//alert(node.text);  // 在用户点击的时候提示
					var id=$("#select option:selected").val();
					var path = "";
					//alert($(this).tree('getRoot', node.target).text);
					//如果是顶级节点
					if($(this).tree('getRoot', node.target) == node){
						path = node.text;
					}else{
						path = path + node.text;
						var father = $(this).tree("getParent", node.target);
						path=father.text + ":" + path;
						while ( $(this).tree("getParent", father.target) ) { 
							var father = $(this).tree("getParent", father.target);
							path = father.text+":"+path;
						}
					}
					var key = path;
					tableCreate(id, key);
				}
			});
		});
        function onC(){
        	var nodeP = $('#messageInfoAddTree');
        	var node0 = $('#0');
        	var id=$("#select option:selected").val();
        	nodeP.tree({  
					 url: 'select/Base?index='+id, 
					 loadFilter: function(data,node0){   
				        if (data.d){   
				            return data.d;   
				        } else {   
				            return data;   
				        }   
				    }
				});
        }
        function tableCreate(id, key) {
        	$.ajax({
				        method : 'POST',
				        url : "queryValueType?index="+id+"&key="+key,
				        async : false,
				        dataType : 'json',
				        success : function(data) {
				            var url="redisvalue.do?index="+id+"&key="+key;
				            if($('#tt').tabs('exists', data)){
				            		 $('#tt').tabs('select', data);
				            		 var tab = $('#tt').tabs('getSelected');
									 $("#tt").tabs('update',{
								        tab: tab,
								        options:{
								            href: url
								        }
								    });
								    tab.panel('refresh');
				            }else{
				            	$('#tt').tabs('add',{
									title: data,
									href:url,
									closable: true
								});
				            }
							//alert(data);
				        },
				        error : function() {
				            alert('error');
				        }
				    });
        }
        function deletebyKey(id, key) {
        	$.messager.confirm('Confirm', 'Are you sure you want to delete?', function(r) {
			    if (r) {
					$.ajax({
				        method : 'GET',
				        url : "delete/value?index="+id+"&key="+key,
				        async : false
					});
					//$('#tt').tabs('select', data);
				    var tab = $('#tt').tabs('getSelected');
				    tab.tabs('close',tabTitle);
			    }
			});
        }
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north'" style="height:50px">
		<div style="margin-top: 10px;margin-left: 15px">
			请选择数据库：
			<select id="select" onchange="onC()">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
			</select>
		</div>
	</div>
	<div data-options="region:'south',split:true" style="height:50px;">
	</div>
	<div data-options="region:'east',split:true" title="版本及提示" style="width:180px;">
		<ul class="easyui-tree" data-options="fit:true,border:false" id="tabs">
			<li>版本：0.1</li>
			<li>制作：xq工作室</li>
			<li><a href="search.do" onclick="" target="_blank">点击搜索</a></li>
		</ul>
	</div>
	<div data-options="region:'west',split:true" title="主键树区" style="width:100px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="keyList" style="padding:10px;">
				<div style="width:200px;height:auto;border:1px solid #ccc;">
					<ul id="messageInfoAddTree" class="easyui-tree" data-options="lines:true,xanimate:false">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'主面板区域',iconCls:'icon-ok'">
		<div id="tt" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title="关于" data-options="tools:'#p-tools'" style="padding:10px">
				<p style="font-size:15px">
					主键区介绍
				</p>
				<ul>
					<li>1、树每一级节点都可以用来作为key查询相应的信息</li>
					<li>2、如果查询不到页面NONE会显示相应的提示</li>
					<li>3、右边点击搜索功能是搜索redis的key-value信息</li>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>