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
ProjectStatus status = (ProjectStatus)request.getAttribute("status");

Statistics newProjectStatistics = (Statistics)request.getAttribute("newProjectStatistics");
Statistics inProgressStatistics = (Statistics)request.getAttribute("inProgressStatistics");
Statistics onHoldStatistics = (Statistics)request.getAttribute("onHoldStatistics");
Statistics orderedStatistics = (Statistics)request.getAttribute("orderedStatistics");
Statistics lossOfOrderStatistics = (Statistics)request.getAttribute("lossOfOrderStatistics");
%>

      <div class="col-md-3">
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-bar-chart" aria-hidden="true"></i> コンタクト管理</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body no-padding">
                  <ul class="nav nav-pills nav-stacked">
                    <%for(ProjectStatus projectStatus: ProjectStatus.values()) { %>
                    <li <%=status != null && status == projectStatus ? "class='active'" : "" %>><a href="/project/?status=<%=projectStatus.toString() %>"><i class="<%=projectStatus.getIconClass() %>" aria-hidden="true"></i> <%=projectStatus.getName() %> 
                    <span class="pull-right">
                    <%if(projectStatus == ProjectStatus.new_project) { %>
                    <%=newProjectStatistics.getStatistic() %>
                    <%}else if(projectStatus == ProjectStatus.in_progress) {%>
                    <%=inProgressStatistics.getStatistic() %>
                    <%}else if(projectStatus == ProjectStatus.on_hold) {%>
                    <%=onHoldStatistics.getStatistic() %>
                    <%}else if(projectStatus == ProjectStatus.ordered) {%>
                    <%=orderedStatistics.getStatistic() %>
                    <%}else if(projectStatus == ProjectStatus.loss_of_order) {%>
                    <%=lossOfOrderStatistics.getStatistic() %>
                    <%} %>
                    </span></a></li>
                    <%} %>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
            </div><!-- /.col -->