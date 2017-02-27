<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="java.util.List" %>
<%@ page import="com.plucial.mulcms.sales.model.*" %>
<%@ page import="com.plucial.mulcms.sales.enums.*" %>
<%@ page import="org.slim3.util.StringUtil" %>
<%
Task task = (Task) request.getAttribute("task");
%>

      <div class="col-md-3">
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-tasks" aria-hidden="true"></i> タスク</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body no-padding">
                  <ul class="nav nav-pills nav-stacked">
                    <%for(Task taskType: Task.values()) { %>
                    <li <%=task != null && task == taskType ? "class='active'" : "" %>><a href="/task/?type=<%=taskType.toString() %>"><i class="fa <%=taskType.getIconClass() %>" aria-hidden="true"></i> <%=taskType.getName() %></a></li>
                    <%} %>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
            </div><!-- /.col -->