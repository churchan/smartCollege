<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.tfswufe.entity.*" %>
<% String path = request.getContextPath(); %>
<%@page import="edu.tfswufe.util.MTimeUtil"%>
<%@ page import="edu.tfswufe.entity.Personnel" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>天府学院 - 查看学生信息</title>
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
						<h5>查看学生信息</h5>
					</div>
					<div class="ibox-content">
						<% Personnel personnel = (Personnel)request.getAttribute("personnel"); %>
						<form method="post" class="form-horizontal" action="">
						    <div class="form-group">
						    	<div class="col-md-1"></div>
								<div class="col-md-4">
							        <label class="col-sm-8 control-label">学号：</label>
							        <p class="form-control-static"><%=personnel.getPersonnelNumber() %></p>
						   		</div>
						   		<div class="col-md-4">
							        <label class="col-sm-5 control-label">姓名：</label>
							        <p class="form-control-static"><%=personnel.getName() %></p>
							    </div>
							</div>
							<div class="form-group">
						    	<div class="col-md-1"></div>
								<div class="col-md-4">
							        <label class="col-sm-8 control-label">性别：</label>
							        <p class="form-control-static"><%=personnel.getGender() %></p>
						   		</div>
						   		<div class="col-md-4">
							        <label class="col-sm-5 control-label">出生日期：</label>
							        <% String birthday = MTimeUtil.dateFormat(personnel.getBirthday()); 	%>
							        <p class="form-control-static"><%=birthday %></p>
							    </div>
							</div>
							<div class="form-group">
								<div class="col-md-1"></div>
								<div class="col-md-4">
							        <label class="col-sm-8 control-label">电话：</label>
							        <p class="form-control-static"><%=personnel.getTelephone() %></p>
						   		</div>
						   		<div class="col-md-4">
							        <label class="col-sm-5 control-label">邮箱：</label>
							        <p class="form-control-static"><%=personnel.getEmail() %></p>
							    </div>
							</div>
							<div class="form-group">
								<div class="col-md-1"></div>
								<div class="col-md-4">
							        <label class="col-sm-8 control-label">籍贯：</label>
							        <p class="form-control-static"><%=personnel.getAddress() %></p>
						   		</div>
						   		<div class="col-md-4">
							        <label class="col-sm-5 control-label">学历：</label>
							        <p class="form-control-static"><%=personnel.getEducation() %></p>
							    </div>
							</div>
							<div class="form-group">
						    	<div class="col-md-1"></div>
								<div class="col-md-4">
							        <label class="col-sm-8 control-label">部门：</label>
							        <p class="form-control-static"><%=personnel.getDepartment().getName() %></p>
						   		</div>
						   		<div class="col-md-4">
							        <label class="col-sm-5 control-label">职称：</label>
							        <p class="form-control-static"><%=personnel.getPosition().getName() %></p>
							    </div>
							</div>
							<div class="form-group">
						    	<div class="col-md-1"></div>
						    	<div class="col-md-4">
							        <label class="col-sm-8 control-label">入职时间：</label>
							         <% String inttime = MTimeUtil.dateFormat(personnel.getInTime()); 	%>
							        <p class="form-control-static"><%=inttime %></p>
						   		</div>
						    	<div class="col-md-4">
							        <label class="col-sm-5 control-label">备注：</label>
							        <p class="form-control-static"><%=personnel.getNotes() %></p>
						   		</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-8">
									<a href="<%=path %>/personnel/<%=personnel.getId() %>/toUpdate.do" class="btn btn-primary">修&nbsp;&nbsp;改</a>&nbsp;&nbsp;
									<a href="<%=path %>/personnel/listPage.do?pageNo=1" class="btn btn-info">返&nbsp;&nbsp;回</a>
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

</body>
</html>
