<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script type="text/javascript">	

	
	    function addTab(title, url){
    	if ($('#tt').tabs('exists', title)){
    		$('#tt').tabs('select', title);
    	} else {
    		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    		$('#tt').tabs('add',{
    			title:title,
    			content:content,
    			closable:true
    		});
    	}
    } 
    
    $(document).ready(function(){
    	    $('#messageInfoAddTree').tree({
				onClick: function(node){
				alert(node.text);  // 在用户点击的时候提示
				$('#messageInfoAddTree').tree('insert',{
					after: node.target,
					data:'http://localhost:8081/redis-client/jquery-easyui-1.5.3/tree1_data.json'
				});
			}
		});
	});
	  
    
    function onC(){
    	var nodes = $('#messageInfoAddTree').tree('getChecked');alert(nodes.length); 
    	 /* $('#messageInfoAddTree').tree('append', {  
			 parent:node.target,  
			 data:nodes  
		 }); */
    }
    
</script>
  <body>
  <div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north'" style="height:50px"><div><input type="button" onclick="onC()" /></div></div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'east',split:true" title="东" style="width:180px;">
			<ul class="easyui-tree" data-options="url:'../layout/tree_data1.json',animate:true,dnd:true"></ul>
		</div>
		<div data-options="region:'west',split:true" title="西" style="width:100px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="keyList" style="padding:10px;">
					    	<div style="width:200px;height:auto;border:1px solid #ccc;">
					<ul id="messageInfoAddTree" class="easyui-tree"  checkbox="true" data-options="lines:true">
		<li>
			<span>My Documents</span>
			<ul>
				<li data-options="state:'closed'">
					<span>Photos</span>
					<ul>
						<li>
							<span>Friend</span>
						</li>
						<li>
							<span>Wife</span>
						</li>
						<li>
							<span>Company</span>
						</li>
					</ul>
				</li>
				<li>
					<span>Program Files</span>
					<ul>
						<li>Intel</li>
						<li>Java</li>
						<li>Microsoft Office</li>
						<li>Games</li>
					</ul>
				</li>
				<li>index.html</li>
				<li>about.html</li>
				<li>welcome.html</li>
			</ul>
		</li>
	</ul> 		

    		</div>
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'主面板区域',iconCls:'icon-ok'">
			<div id="tt" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="关于" data-options="href:'../layout/_content.html'" style="padding:10px"></div>
				<div title="数据表格" style="padding:5px">
					<table class="easyui-datagrid"
							data-options="url:'../layout/datagrid_data1.json',singleSelect:true,fit:true,fitColumns:true">
						<thead>
							<tr>
								<th data-options="field:'itemid'" width="80">编号</th>
								<th data-options="field:'productid'" width="100">商品编号</th>
								<th data-options="field:'listprice',align:'right'" width="80">市场价</th>
								<th data-options="field:'unitcost',align:'right'" width="80">成本价</th>
								<th data-options="field:'attr1'" width="150">描述</th>
								<th data-options="field:'status',align:'center'" width="50">状态</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	   <!--  <div style="margin-bottom:10px">
	    	<a href="#" class="easyui-linkbutton" onclick="addTab('google','http://www.google.com')">google</a>
	    	<a href="#" class="easyui-linkbutton" onclick="addTab('jquery','http://jquery.com/')">jquery</a>
	    	<a href="#" class="easyui-linkbutton" onclick="addTab('easyui','http://jeasyui.com/')">easyui</a>
	    </div>
	    <div id="tt" class="easyui-tabs" style="width:400px;height:250px;">
	    	<div title="Home">
	    	</div>
    	</div> -->
  </body>
</html>
