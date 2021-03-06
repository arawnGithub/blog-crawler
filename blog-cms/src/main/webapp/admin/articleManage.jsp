<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	
	function searchArticle(){
		$("#dg").datagrid('load',{
			"title":$("#s_title").val()
		});
	}
	
	function formatState(val,row){
		if(val==0){
			return "未发布";
		}else{
			return "已发布";
		}
	}
	
	function formatTitle(val,row){
		return "&nbsp;<a target='_blank' href='${pageContext.request.contextPath}/article/"+row.id+".html'>"+val+"</a>";
	}
	
	function openArticleAddTag(){
		window.parent.openTab("添加文章","articleAdd.jsp","icon-writeArticle");
	}
	
	function openArticleModifyTag(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一个要修改的文章");
			return;
		}
		var row=selectedRows[0];
		window.parent.openTab("修改文章","articleModify.jsp?id="+row.id,"icon-writeArticle");
	}
	
	function deleteArticle(){
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
				$.post("${pageContext.request.contextPath}/admin/article/delete.do",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
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
<table id="dg" class="easyui-datagrid" title="文章管理" 
  fitColumns="true" pagination="true" rownumbers="true" 
  url="${pageContext.request.contextPath}/admin/article/list.do" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="title" width="150" formatter="formatTitle">标题</th>
  		<th field="crawlerDate" width="80" align="center">抓取日期</th>
  		<th field="originalUrl" width="150" align="center">原始地址</th>
  		<th field="state" width="40" align="center" formatter="formatState">文章状态</th>
  	</tr>
  </thead>
</table>

<div id="tb">
	<div>
		<a href="javascript:openArticleAddTag()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openArticleModifyTag()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteArticle()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchArticle()"/>
		<a href="javascript:searchArticle()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>
</body>
</html>