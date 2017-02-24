<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="java.util.List" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="org.slim3.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
Errors errors =(Errors) request.getAttribute("errors");

Partner company = (Partner)request.getAttribute("company");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");

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
                <!-- project_list -->
				<jsp:include page="/includes/project_list.jsp" />
			    <!-- /project_list -->
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
