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
MailTemplate mailTemplate = (MailTemplate) request.getAttribute("mailTemplate");
MailKey mailKey = MailKey.valueOf(mailTemplate.getKey().getName());
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
            <i class="fa fa-envelope-o"></i> メールテンプレートの管理
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- partner left content -->
            <jsp:include page="/includes/mail_left_content.jsp" />
            <!-- partner left content -->
            
            <div class="col-md-9">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title"><%=mailKey.getName() %></h3>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                  <div class="mailbox-read-info">
                    <h3><%=StringUtil.isEmpty(mailTemplate.getSubject()) ? "未定義" : mailTemplate.getSubject() %></h3>
                    <h5><b>From Personal:</b> <%=StringUtil.isEmpty(mailTemplate.getFromPersonal()) ? "未定義" : mailTemplate.getFromPersonal() %></h5>
                    <h5><b>From Address:</b> <%=mailKey.getFromAddress() %></h5>
                  </div><!-- /.mailbox-read-info -->
                  <div class="mailbox-controls with-border">
                    <p style="padding-top:20px">株式会社プラシャル 高原様   <span class="text-red" style="font-size:12px;">(社名、担当者名を自動追加)</span></p>
                  </div><!-- /.mailbox-controls -->
                  <div class="mailbox-read-message">
                    <p><%=mailTemplate.getMessageString().replace("\r\n", "<br />") %></p>
                  </div><!-- /.mailbox-read-message -->
                </div><!-- /.box-body -->

                <div class="box-footer">
                  <div class="pull-right">
                    <a class="btn btn-default" href="/mail/edit?key=<%=mailKey.toString() %>">修正</a>
                  </div>
                </div><!-- /.box-footer -->
              </div><!-- /. box -->
            </div>
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
