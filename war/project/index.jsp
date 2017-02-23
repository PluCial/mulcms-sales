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

Statistics projectTotalNumber = (Statistics)request.getAttribute("projectTotalNumber");

List<Project> projectList = (List<Project>)request.getAttribute("projectList");
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
            <i class="fa fa-line-chart"></i> プロジェクト管理
            <small>プロジェクト: <%=projectTotalNumber.getStatistic() %>件</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- partner left content -->
            <jsp:include page="/includes/project_left_content.jsp" />
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
                      	<%if(projectList != null) {
                      		for(Project project: projectList) {
                      	%>
                        <tr>
                          <td class="mailbox-name"><i class="fa fa-product-hunt" aria-hidden="true"></i> <%=project.getName() %></td>
                          
                          <td class="mailbox-subject">
                          	<%Client client = project.getClientRef().getModel(); %>
                          	<a href="/company/client/info?company=<%=client.getKey().getName() %>" ><i class="fa fa-user" aria-hidden="true"></i> <%=client.getName() %></a>
                          </td>
                          
                          <td class="mailbox-subject">
                            <%if(project.getPartnerRef() != null) { %>
                            <%Partner partner = project.getPartnerRef().getModel(); %>
                            <%if(partner != null) { %>
                          	<a href="/company/partner/info?company=<%=partner.getKey().getName() %>" ><i class="fa fa-building-o"></i> <%=partner.getName() %></a>
                          	<%}else { %>
                          	<a href="/project/searchPartner?projectId=<%=project.getKey().getId() %>" class="btn btn-primary btn-sm">パートナー登録</a>
                          	<%} %>
                          	<%} %>
                          </td>
                          
                          <td class="mailbox-date">
                          <%=client.getLocationCode() == null ? "": client.getLocationCode().getName() %>
                          </td>
                          
                          <td class="mailbox-date">
                          	<%if(project.getStatus() != ProjectStatus.new_project) { %>
                          	<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm"><i class="<%=project.getStatus().getIconClass() %>" aria-hidden="true"></i> <%=project.getStatus().getName() %></button>
								<button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span>
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<ul class="dropdown-menu dropdown-menu-left" role="menu">
									<%for(ProjectStatus statusAction: ProjectStatus.values()) {
										if(statusAction != ProjectStatus.new_project) {
										%>
									<li><a tabindex="-1" href="/project/changeStatus?projectId=<%=project.getKey().getId() %>&status=<%=statusAction.toString() %>"><i class="<%=statusAction.getIconClass() %>" aria-hidden="true"></i> <%=statusAction.getName() %></a></li>
									<%}} %>
								</ul>
							</div>

                          	<%}else { %>
                          	<span><%=sdf.format(project.getCreateDate()) %></span>
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
