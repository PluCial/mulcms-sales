<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
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

        	<!-- Main content -->
			<section class="content">
				<div class="row">
            
					<div class="col-md-4 col-md-offset-4">
						<h2 class="page-header">デザイン会社の取り込み</h2>
						
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
						
							<!-- form start -->
							<form action="/setting/collectionEntry" method="post">
								<div class="box-body">
										<div class="form-group">
											<label>取り込み先URL</label>
											<input ${f:text("url")} class="form-control" id="exampleInputEmail1" placeholder="https:///xxxx.com/xxx.html">
										</div>
								</div><!-- /.box-body -->

								<div class="box-footer">
									<button type="submit" class="btn btn-primary pull-right">取り込み</button>
								</div>
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
