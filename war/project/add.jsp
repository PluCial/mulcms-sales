<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
Client client = (Client)request.getAttribute("client");
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

        	<!-- Main content -->
			<section class="content">
				<div class="row">
            
					<div class="col-md-4 col-md-offset-4">
						<h2 class="page-header"><i class="fa fa-line-chart"></i> プロジェクトの登録</h2>
						
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
						    <%-- <div class="box-header with-border">
			                  <h3 class="box-title"><i class="fa fa-bar-chart" aria-hidden="true"></i> <%=client.getName() %></h3>
			                  <div class='box-tools'>
			                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
			                  </div>
			                </div> --%>
							<!-- form start -->
							<form action="/project/addEntry" method="post">
								<div class="box-body">
									<div class="form-group">
											<label>クライアント</label>
											<p><%=client.getName() %> 様</p>
										</div>
										<div class="form-group">
											<label>プロジェクト名<span class="text-red">*</span></label>
											<input ${f:text("name")} class="form-control" id="exampleInputEmail1" placeholder="株式会社プラシャル ホームページ">
										</div>
										
								</div><!-- /.box-body -->

								<div class="box-footer">
									<button type="submit" class="btn btn-primary pull-right">登録</button>
								</div>
								
								<input type="hidden" name="client" value="<%=client.getKey().getName() %>" />
							</form>
						</div><!-- /.box -->
					</div><!-- /.col -->
          
				</div><!-- /.row -->
			</section><!-- /.content -->
        <!-- /.content -->
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
