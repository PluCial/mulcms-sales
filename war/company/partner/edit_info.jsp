<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="org.slim3.util.StringUtil" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
Partner company = (Partner) request.getAttribute("company");
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
						<h2 class="page-header"><i class="fa fa-building-o"></i> パートナー情報の修正</h2>
						
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
							<form action="/company/partner/editInfoEntry" method="post">
								<div class="box-body">
										<div class="form-group">
											<label>会社名<span class="text-red">*</span></label>
											<input ${f:text("name")} class="form-control" id="exampleInputEmail1" placeholder="株式会社プラシャル">
										</div>
										<div class="form-group">
											<label>ホームページURL<span class="text-red">*</span></label>
											<input ${f:text("homepage")} class="form-control" id="exampleInputEmail1" placeholder="https:///xxxx.com/xxx.html">
										</div>
										<div class="form-group">
											<label>ドメイン<span class="text-red">*</span></label>
											<input ${f:text("domain")} class="form-control" id="exampleInputEmail1" placeholder="xxxx.com, yyy.co.jp">
										</div>
										<div class="form-group">
											<label>住所<span class="text-red">*</span></label>
											<input ${f:text("address")} class="form-control" id="exampleInputEmail1" placeholder="東京都渋谷区・・・">
										</div>
										<div class="form-group">
											<label>Email</label>
											<input ${f:text("email")} class="form-control" id="exampleInputEmail1" placeholder="info@plucial.com">
										</div>
										<div class="form-group">
											<label>電話番号</label>
											<input ${f:text("phoneNumber")} class="form-control" id="exampleInputEmail1" placeholder="03-0000-0000">
										</div>
										<div class="form-group">
											<label>担当者名</label>
											<input ${f:text("responsiblePartyName")} class="form-control" id="exampleInputEmail1" placeholder="高原 功">
										</div>
								</div><!-- /.box-body -->

								<div class="box-footer">
									<button type="submit" class="btn btn-primary pull-right">変更</button>
								</div>
								
								<input type="hidden" name="company" value="<%=company.getKey().getName() %>">
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
