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

Partner company = (Partner)request.getAttribute("company");

List<Partner> companyList = (List<Partner>)request.getAttribute("companyList");
String nowCursor = (String) request.getAttribute("cursor");
String nextCursor = null;
boolean hasNext = false;
if (request.getAttribute("nextCursor") != null && request.getAttribute("hasNext") != null) {
	nextCursor = (String) request.getAttribute("nextCursor");
	hasNext = Boolean.valueOf((String) request.getAttribute("hasNext"));
}
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
            <i class="fa fa-building-o"></i> パートナー管理
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- partner left content -->
            <div class="col-md-3">
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title">パートナー情報</h3>
                  <div class='box-tools'>
                    <a class='btn btn-box-tool' href="/company/partner/editInfo?company=<%=company.getKey().getName() %>" style="font-size: 14px"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                  </div>
                </div>
                <div class="box-body">
                  <ul class="nav nav-pills nav-stacked">
                    <li><a><i class="fa fa-database" aria-hidden="true"></i> <%=company.getKey().getName() %></a></li>
                    <li><a><i class="fa fa-building-o"></i> <%=company.getName() %></a></li>
                    <li>
                        <a <%=company.getHomepage() != null ? "href='" + company.getHomepage() + "'"  : "" %> target="_companyhp">
                            <i class="fa fa-link" aria-hidden="true"></i> <%=company.getHomepage() != null ? company.getHomepage()  : "" %>
                            
                            <%if(company.getHomepage() != null) { %>
                    	    <span class="pull-right"><i class="fa fa-external-link" aria-hidden="true"></i></span>
                    	    <%} %>
                         </a>
                    </li>
                    <li><a><i class="fa fa-globe" aria-hidden="true"></i> <%=company.getDomain() != null ? company.getDomain()  : "" %></a></li>
                    <li><a><i class="fa fa-map-marker" aria-hidden="true"></i> <%=company.getAddress() != null ? company.getAddress() : "" %></a></li>
                    <li>
                        <a <%=company.getEmail() != null ? "href=mailto:'" + company.getEmail().getEmail() + "'"  : "" %>>
                            <i class="fa fa-envelope" aria-hidden="true"></i> <%=company.getEmail() != null ? company.getEmail().getEmail() : "" %>
                            
                            <%if(company.getEmail() != null) { %>
                    	    <span class="pull-right"><i class="fa fa-external-link" aria-hidden="true"></i></span>
                    	    <%} %>
                        </a>
                    </li>
                    <li><a><i class="fa fa-phone" aria-hidden="true"></i> <%=company.getPhoneNumber() != null ? company.getPhoneNumber().getNumber() : "" %></a></li>
                    <li><a><i class="fa fa-user" aria-hidden="true"></i> <%=company.getResponsiblePartyName() != null ? company.getResponsiblePartyName() : "" %></a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
            </div><!-- /.col -->
            <!-- partner left content -->
            
            <div class="col-md-9">
              <div class="box box-primary" style="z-index: 1;">
                <div class="box-header with-border">
                  <h3 class="box-title">案件一覧 (<%=company.getNumberOfProjects() %> 件)</h3>
                  <!-- <div class="box-tools pull-right">
                    <div class="has-feedback">
                      <input type="text" class="form-control input-sm" placeholder="Search Keyword"/>
                      <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                  </div>/.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                  <div class="table-responsive mailbox-messages" style="overflow-x: visible !important;overflow-y: visible !important;">
                    <table class="table table-hover table-striped">
                      <tbody>
                      	
                      </tbody>
                    </table><!-- /.table -->
                  </div><!-- /.mail-box-messages -->
                </div><!-- /.box-body -->
                
                
                <div class="box-footer">
                  <div class="mailbox-controls">
                    <%if(!StringUtil.isEmpty(nowCursor)) { %>
                    <a class="btn btn-default btn-sm pull-left" href="javascript:history.back();">
                    	<i class="fa fa-chevron-left"></i>
                    </a>
                    <%} %>
                  	<%if(hasNext) { %>
                    <a class="btn btn-default btn-sm pull-right" href="/company/?cursor=<%=nextCursor %>">
                    	<i class="fa fa-chevron-right"></i>
                    </a>
                    <%} %>
                  </div>
                </div>
                
                
              </div><!-- /. box -->
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
