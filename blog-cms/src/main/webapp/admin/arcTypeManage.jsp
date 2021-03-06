<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章类别管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;

	function openArcTypeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加文章类别信息");
		url="${pageContext.request.contextPath}/admin/arcType/save.do";
	}
	
	function openArcTypeModifyDialog(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑文章类别信息");
		$("#fm").form("load",row);
		url="${pageContext.request.contextPath}/admin/arcType/save.do?id="+row.id;
	}
	
	
	function saveArcType(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功！");
                    closeArcTypeDialog();
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function resetValue(){
		$("#typeName").val("");
		$("#sortNo").val("");
	}
	
	function closeArcTypeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function deleteArcType(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗?",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/arcType/delete.do",{ids:ids},function(result){
					if(result.success){
						if(result.exist){
							$.messager.alert("系统提示",result.exist);
						}else{
							$.messager.alert("系统提示","数据已成功删除！");							
						}
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
				}, "json");
			}
		});
	}
</script>
</head>
<body style="margin: 1px">
<table id="dg" class="easyui-datagrid" title="文章类别管理" 
  fitColumns="true" pagination="true" rownumbers="true" 
  url="${pageContext.request.contextPath}/admin/arcType/list.do" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="typeName" width="80" align="center">文章类别名称</th>
  		<th field="sortNo" width="30" align="center">排列序号</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<a href="javascript:openArcTypeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
	<a href="javascript:openArcTypeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	<a href="javascript:deleteArcType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 500px;height: 200px;padding: 10px 20px" closed="true" buttons="#dlg-buttons" data-options="onClose:resetValue">
	<form id="fm" method="post" data-options="onClose:resetValue">
		<table cellspacing="8px">
			<tr>
				<td>文章类别名称：</td>
				<td>
					<input type="text" id="typeName" name="typeName" class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td>排列序号：</td>
				<td>
					<input type="text" id="sortNo" name="sortNo" class="easyui-numberbox" required="true" style="width: 60px"/>&nbsp;(类别根据排列序号从小到大排列)
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveArcType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeArcTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>