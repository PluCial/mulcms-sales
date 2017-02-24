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
						<h2 class="page-header"><i class="fa fa-building-o"></i> デザイン会社の取り込み</h2>
						
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
							
								<div class="box-body">
									<form action="/company/partner/collectionEntry" method="post">
										<div class="input-group">
						                    <input ${f:text("url")} class="form-control" placeholder="https:///xxxx.com/xxx.html">
						                    <span class="input-group-btn">
						                      <button class="btn btn-primary btn-flat" type="submit">個別取り込み</button>
						                    </span>
					                    </div>
				                    </form>
								</div><!-- /.box-body -->

								<div class="box-footer text-center">
									<p class="text-red text-left">バッチを使ってまとめて追加する場合は、複数のスレッドから同じStatistics エンティティに対してGET、PUTを行うため、ConcurrentModificationException が発生する。それを回避するために、このバッチでは 「会社総数」と「未配信数」の加算を行っていない。<br /><br />バッチ実行後にGCPの管理画面から手動でStatistics のカウンターを修正する必要がある。</p>
									<p class="">それでも実行したい？<br />good luck.<br /></p>
									<a href="/queue/partnerCollectionQueue" class="btn btn-primary">収集バッチの実行</a>
								</div>
							
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
