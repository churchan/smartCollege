<%@page import="edu.tfswufe.util.MTimeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.tfswufe.entity.*" %>
<%@ page import="edu.tfswufe.entity.Personnel" %>
<%@ page import="edu.tfswufe.entity.Overtime" %>
<%@ page import="edu.tfswufe.entity.Department" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>天府学院 - 修改加班安排</title>
	<meta name="keywords" content="">
	<meta name="description" content="">

	<link rel="shortcut icon" href="favicon.ico">
	<link href="<%=path %>/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="<%=path %>/css/font-awesome.css?v=4.4.0" rel="stylesheet">

	<!-- Data Tables -->
	<link href="<%=path %>/css/plugins/dataTables/dataTables.bootstrap.css"
		rel="stylesheet">
	<link href="<%=path %>/css/animate.css" rel="stylesheet">
	<link href="<%=path %>/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>修改加班安排</h5>
					</div>
					<div class="ibox-content">
						<% Overtime overtime = (Overtime)request.getAttribute("overtime"); %>
						<form method="post" class="form-horizontal" id="commentForm" action="<%=path %>/overtime/<%=overtime.getId() %>/update.do">
							<div class="form-group">
								<label class="col-sm-3 control-label">部门</label>
								<div class="col-sm-7">
									<select class="form-control m-b" id="department" name="departmentNumber" size="1" required>
										<option value="">--请选择部门--</option>
									<%
										List<Department> dList = (List<Department>) request.getAttribute("dList");
										for(Department department : dList){

									%>
										<option value="<%=department.getDepartmentNumber() %>"
										<%
											if(overtime.getDepartmentNumber().equals(department.getDepartmentNumber())){
										%>
											selected="selected"
										<%  }  %>
										>
											<%=department.getName() %>
										</option>
									<%
										}
									%>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">员工</label>
								<div class="col-sm-7">
									<select class="form-control m-b" id="personnel" name="personnelNumber" size="1" required>
										<option value="">--请选择员工--</option>
										<%
											List<personnel> eList = (List<personnel>) request.getAttribute("eList");
											for(Personnel personnel : eList){

										%>
											<option value="<%=personnel.getPersonnelNumber() %>"
											<%
												if(overtime.getPersonnelNumber().equals(personnel.getPersonnelNumber())){
											%>
												selected="selected"
											<%  }  %>
											>
												<%=personnel.getName() %>
											</option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">加班日期</label>
								<div class="col-sm-7">
									<% String day = MTimeUtil.dateFormat(overtime.getDay()); %>
									<input type="date" class="form-control" size="1" name="date" value="<%=day %>">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-8">
									<button class="btn btn-primary" type="submit">修&nbsp;&nbsp;改</button>&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="<%=path %>/overtime/listPage.do?pageNo=1" class="btn btn-info">返&nbsp;&nbsp;回</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 全局js -->
	<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path %>/js/plugins/jeditable/jquery.jeditable.js"></script>

	<!-- Data Tables -->
	<script src="<%=path %>/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="<%=path %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- 自定义js -->
	<script src="<%=path %>/js/content.js?v=1.0.0"></script>

	<!-- 表单验证 -->
	<script src="<%=path %>/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="<%=path %>/js/plugins/validate/messages_zh.min.js"></script>

	<!-- layer javascript -->
    <script src="js/plugins/layer/layer.min.js"></script>
    <script>
	$().ready(function() {
	    $("#commentForm").validate();
	});
	$.validator.setDefaults({
	    submitHandler: function() {
	    	parent.layer.msg('修改成功！',{icon: 1});
	    	form.submit();
	    }
	});
	</script>
</body>
</html>
