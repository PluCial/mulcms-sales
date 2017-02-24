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
