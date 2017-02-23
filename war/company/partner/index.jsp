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

Statistics companyTotalNumber = (Statistics)request.getAttribute("companyTotalNumber");

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
            <small>ターゲット: <%=companyTotalNumber.getStatistic() %>社</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- partner left content -->
            <jsp:include page="/includes/partner_left_content.jsp" />
            <!-- partner left content -->
            
            <div class="col-md-9">
              <div class="box box-primary" style="z-index: 1;">
                <div class="box-header with-border">
                  <h3 class="box-title">企業一覧</h3>
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
                      		for(Partner company: companyList) {
                      	%>
                        <tr>
                          <td class="mailbox-star">
	                          <%if(company.isPartner() ||  company.getContactStatus() == ContactStatus.delivered) { %>
	                          <a href="/company/partner/changePartnerFlagEntry?company=<%=company.getKey().getName() %>"><i class="fa <%=company.isPartner() ? "fa-star" : "fa-star-o" %> text-yellow"></i></a>
	                          <%} %>
                          </td>
                          <td class="mailbox-name"><a href="/company/partner/info?company=<%=company.getKey().getName() %>"><%=company.getName() %></a></td>
                          <td class="mailbox-subject"><%=company.getAddress() %></td>
                          <td class="mailbox-attachment">
                          	<%if(company.getHomepage() != null) { %>
                          	<a href="<%=company.getHomepage() %>" target="_companyhp" style="color:#555"><i class="fa fa-globe" aria-hidden="true"></i></a>
                          	<%} %>
                          </td>
                          <td class="mailbox-attachment">
                          	<%if(company.getEmail() != null) { %>
                          	<i class="fa fa-envelope" aria-hidden="true"></i>
                          	<%} %>
                          </td>
                          <td class="mailbox-attachment">
                          	<%if(company.getPhoneNumber() != null) { %>
                          	<i class="fa fa-phone" aria-hidden="true"></i>
                          	<%} %>
                          </td>
                          <td class="mailbox-date">
                            <%if(company.isPartner()) { %>
                            案件数: <%=company.getNumberOfProjects() %>
                            <%} %>
                          </td>
                          
                          <td class="mailbox-date">
                            <%if(!company.isPartner()) { %>
                          	<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm"><i class="<%=company.getContactStatus().getIconClass() %>" aria-hidden="true"></i> <%=company.getContactStatus().getName() %></button>
								<button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span>
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<ul class="dropdown-menu dropdown-menu-left" role="menu">
									<%for(ContactStatus statusAction: ContactStatus.values()) { %>
									<li><a tabindex="-1" href="/company/changeStatus?company=<%=company.getKey().getName() %>&status=<%=statusAction.toString() %>"><i class="<%=statusAction.getIconClass() %>" aria-hidden="true"></i> <%=statusAction.getName() %></a></li>
									<%} %>
								</ul>
							</div>
							<%} %>
                          </td>
                          
                          <td class="mailbox-date">
                          	<%if(company.getContactStatus() == ContactStatus.pending_delivery) { %>
                          	<a href="/company/contactMailSendEntry?company=<%=company.getKey().getName() %>" class="btn btn-primary btn-sm">コンタクト</a>
                          	<%} %>
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
