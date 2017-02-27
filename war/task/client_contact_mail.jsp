<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="java.util.List" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="org.slim3.util.StringUtil" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/includes/html_head.jsp" />
</head>
<body class="skin-blue layout-top-nav">
	<div class="wrapper">
		<!-- site-header -->
		<jsp:include page="/includes/site_header.jsp" />
		<!-- /site-header -->
      

		<!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            <i class="fa fa-tasks"></i> タスク管理
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- partner left content -->
            <jsp:include page="/includes/task_left_content.jsp" />
            <!-- partner left content -->
            
            <div class="col-md-4 col-md-offset-2">
				
						<%if (!errors.isEmpty()){ %>
						<!-- alert -->
						<div class="alert alert-warning alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<h4><i class="icon fa fa-warning"></i> Alert!</h4>
							<%=errors.get(0) %>
						</div>
						<!-- /alert -->
						<%} %>
                  
						<div class="box box-primary">
						
							<div class="box-header">
			                  <h3 class="box-title"><%=Task.client_contact_mail.getName() %></h3>
			                </div>
							
								<div class="box-body">
									<form action="/task/clientContactMailQueue" method="post">
										<div class="input-group">
						                    <input type="number" name="count" class="form-control" placeholder="実行数" value="50" />
						                    <span class="input-group-btn">
						                      <button class="btn btn-primary btn-flat" type="submit">実行</button>
						                    </span>
					                    </div>
				                    </form>
								</div><!-- /.box-body -->
							
						</div><!-- /.box -->
					</div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	
		<!-- page footer -->
    	<jsp:include page="/includes/site_footer.jsp" />
		<!-- /page footer -->
      
		<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
		<div class='control-sidebar-bg'></div>
    </div><!-- ./wrapper -->

    
    <!-- page script -->
    <jsp:include page="/includes/html_script.jsp" />
    <!-- page script -->

  </body>
</html>
