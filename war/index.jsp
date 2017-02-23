<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="java.util.List" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="org.slim3.util.StringUtil" %>
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
            Dashboard
            <small>Control panel</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-3 col-xs-6 col-md-offset-3">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>40,000<sup style="font-size: 20px">社</sup></h3>
                  <p>クライアント</p>
                </div>
                <div class="icon">
                  <i class="fa fa-users"></i>
                </div>
                <a href="/company/client/" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
            <div class="col-md-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>7,000<sup style="font-size: 20px">社</sup></h3>
                  <p>パートナー</p>
                </div>
                <div class="icon">
                  <i class="fa fa-building-o"></i>
                </div>
                <a href="/company/partner/" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
          </div>
          
          <div class="row">
            <div class="col-md-3 col-xs-6 col-md-offset-3">
              <div class="text-right" style="font-size:36px; padding-right: 35px;">
                <i class="fa fa-angle-double-down" aria-hidden="true"></i>
              </div>
            </div><!-- ./col -->
            <div class="col-md-3 col-xs-6">
              <div class="text-left" style="font-size:36px; padding-left: 35px;">
                <i class="fa fa-angle-double-down" aria-hidden="true"></i>
              </div>
            </div><!-- ./col -->
          </div>
          
          <div class="row">
            <div class="col-md-4 col-xs-6 col-md-offset-4 col-xs-offset-3">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3>53<sup style="font-size: 20px">件</sup></h3>
                  <p>プロジェクト</p>
                </div>
                <div class="icon">
                  <i class="fa fa-line-chart"></i>
                </div>
                <a href="/project/" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div><!-- ./col -->
          </div>
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
