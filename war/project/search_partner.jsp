<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="org.slim3.util.StringUtil" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");

Project project = (Project) request.getAttribute("project");
Client client = (Client) request.getAttribute("client");

List<Company> companyList = (List<Company>)request.getAttribute("companyList");
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
            <i class="fa fa-line-chart"></i> <%=project.getName() %>
            <small>パートナー登録</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- partner left content -->
            <div class="col-md-3">
            
            	<div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title">クライアント情報</h3>
                  <div class='box-tools'>
                    <a class='btn btn-box-tool' href="/company/client/editInfo?company=<%=client.getKey().getName() %>" style="font-size: 14px"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                  </div>
                </div>
                <div class="box-body">
                  <ul class="nav nav-pills nav-stacked">
                    <li><a><i class="fa fa-user" aria-hidden="true"></i> <%=client.getName() %></a></li>
                    <li><a><i class="fa fa-tags" aria-hidden="true"></i> <%=client.getIndustry().getName() %></a></li>
                    <li>
                        <a <%=client.getHomepage() != null ? "href='" + client.getHomepage() + "'"  : "" %> target="_companyhp">
                            <i class="fa fa-link" aria-hidden="true"></i> <%=client.getHomepage() != null ? client.getHomepage()  : "" %>
                            
                            <%if(client.getHomepage() != null) { %>
                    	    <span class="pull-right"><i class="fa fa-external-link" aria-hidden="true"></i></span>
                    	    <%} %>
                         </a>
                    </li>
                    <li><a><i class="fa fa-globe" aria-hidden="true"></i> <%=client.getDomain() != null ? client.getDomain()  : "" %></a></li>
                    <li><a><i class="fa fa-map-marker" aria-hidden="true"></i> <%=client.getAddress() != null ? client.getAddress() : "" %></a></li>
                    <li>
                        <a <%=client.getEmail() != null ? "href=mailto:'" + client.getEmail().getEmail() + "'"  : "" %>>
                            <i class="fa fa-envelope" aria-hidden="true"></i> <%=client.getEmail() != null ? client.getEmail().getEmail() : "" %>
                            
                            <%if(client.getEmail() != null) { %>
                    	    <span class="pull-right"><i class="fa fa-external-link" aria-hidden="true"></i></span>
                    	    <%} %>
                        </a>
                    </li>
                    <li><a><i class="fa fa-phone" aria-hidden="true"></i> <%=client.getPhoneNumber() != null ? client.getPhoneNumber().getNumber() : "" %></a></li>
                    <li><a><i class="fa fa-user" aria-hidden="true"></i> <%=client.getResponsiblePartyName() != null ? client.getResponsiblePartyName() : "" %></a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-search" aria-hidden="true"></i> パートナー検索</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body">
                   <ul class="nav nav-pills nav-stacked">
                       <li>
                			<form action="/project/searchPartner" method="get">
                  				<div class="input-group">
				                    <input type="text" name="keyword" class="form-control" placeholder="キーワード">
				                    <span class="input-group-btn">
				                      <button class="btn btn-primary btn-flat" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
				                    </span>
				                    <input type="hidden" name="projectId" value="<%=project.getKey().getId() %>" />
			                  </div>
                  			</form>
                  		</li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
            </div>
            <!-- partner left content -->
            
            <div class="col-md-9">
              <div class="box box-primary" style="z-index: 1;">
                <div class="box-header with-border">
                  <h3 class="box-title"><%=client.getLocationCode().getName() %> のパートナー一覧</h3>
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
                      	<%if(companyList != null) {
                      		for(Company company: companyList) {
                      	%>
                        <tr>
                          <td class="mailbox-name">
                          <a href="/company/partner/info?company=<%=company.getKey().getName() %>" ><i class="fa fa-building-o"></i> <%=company.getName() %></a>
                          </td>
                          <td class="mailbox-date">
                          <%=company.getLocationCode() == null ? "": company.getLocationCode().getName() %>
                          </td>
                          
                          <td class="mailbox-date">
                          	<a href="/project/addPartnerEntry?projectId=<%=project.getKey().getId() %>&partner=<%=company.getKey().getName() %>" class="btn btn-primary btn-sm">パートナーとして登録</a>
                          </td>
                          
                        </tr>
                        <%}} %>
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
