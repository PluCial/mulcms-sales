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
ContactStatus status = (ContactStatus)request.getAttribute("status");

String locationCodeString = (String)request.getAttribute("locationCode");

Statistics partnerNumber = (Statistics)request.getAttribute("partnerNumber");
Statistics pendingDeliveryStatistics = (Statistics)request.getAttribute("pendingDeliveryStatistics");
Statistics deliveredStatistics = (Statistics)request.getAttribute("deliveredStatistics");
Statistics deliveryFailureStatistics = (Statistics)request.getAttribute("deliveryFailureStatistics");
%>

      <div class="col-md-3">
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-bar-chart" aria-hidden="true"></i> パートナー管理</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body no-padding">
                  <ul class="nav nav-pills nav-stacked">
                    <li <%=status == null ? "class='active'" : "" %>><a href="/company/partner/"><i class="fa fa-building-o" aria-hidden="true"></i> パートナー <span class="pull-right"><%=partnerNumber.getStatistic() %></span></a></li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-search" aria-hidden="true"></i> 地域別パートナー検索</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body">
                   <form action="/company/partner/searchPartnerByLocationCode" method="get">
                  		<div class="form-group">
	                      <label>都道府県</label>
	                      <select class="form-control" name="locationCode">
	                        <option value="">--- 選択 ---</option>
	                        <%for(LocationCode locationCode: LocationCode.values()) { %>
	                        <option value="<%=locationCode.toString() %>" <%=locationCodeString != null && locationCodeString.equals(locationCode.toString()) ? "selected" : "" %>><%=locationCode.getName() %></option>
	                        <%} %>
	                      </select>
	                    </div>
	                    <div class="form-group text-right">
				        	<button class="btn btn-primary btn-flat" type="submit">検索</button>
				        </div>
                	</form>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-bar-chart" aria-hidden="true"></i> コンタクト管理</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body no-padding">
                  <ul class="nav nav-pills nav-stacked">
                    <%for(ContactStatus contactStatus: ContactStatus.values()) { %>
                    <li <%=status != null && status == contactStatus ? "class='active'" : "" %>><a href="/company/partner/?status=<%=contactStatus.toString() %>"><i class="<%=contactStatus.getIconClass() %>" aria-hidden="true"></i> <%=contactStatus.getName() %> 
                    <span class="pull-right">
                    <%if(contactStatus == ContactStatus.pending_delivery) { %>
                    <%=pendingDeliveryStatistics.getStatistic() %>
                    <%}else if(contactStatus == ContactStatus.delivered) {%>
                    <%=deliveredStatistics.getStatistic() %>
                    <%}else if(contactStatus == ContactStatus.delivery_failure) {%>
                    <%=deliveryFailureStatistics.getStatistic() %>
                    <%} %>
                    </span></a></li>
                    <%} %>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
              <div class="box box-solid">
                <div class="box-header with-border">
                  <h3 class="box-title"><i class="fa fa-search" aria-hidden="true"></i> キーワード検索</h3>
                  <div class='box-tools'>
                    <button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
                  </div>
                </div>
                <div class="box-body">
                   <ul class="nav nav-pills nav-stacked">
                       <li>
                			<form action="/company/partner/search" method="get">
                  				<div class="input-group">
				                    <input type="text" name="keyword" class="form-control">
				                    <span class="input-group-btn">
				                      <button class="btn btn-primary btn-flat" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
				                    </span>
			                    
			                  </div>
                  			</form>
                  		</li>
                  </ul>
                </div><!-- /.box-body -->
              </div><!-- /. box -->
              
            </div><!-- /.col -->