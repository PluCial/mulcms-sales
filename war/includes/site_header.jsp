<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

      <header class="main-header">               
        <nav class="navbar navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <a href="/" class="navbar-brand"><b>MulCMS</b> 営業ツール</a>
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <i class="fa fa-bars"></i>
              </button>
            </div>
            
            <!-- <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">ドキュメント <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="http://mulcms.plucial.com/ja/manual/setup.html" target="_blank">マニュアル</a></li>
                    <li><a href="http://mulcms.plucial.com/ja/info/agreement.html" target="_blank">利用規約</a></li>
                    <li class="divider"></li>
                    <li><a href="http://inc.plucial.com/" target="_blank">株式会社プラシャル</a></li>
                  </ul>
                </li>
              </ul>                        
            </div> -->

            <!-- Collect the nav links, forms, and other content for toggling -->
            <!-- Navbar Right Menu -->
              <div class="navbar-custom-menu">
              	
              	<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
	              <ul class="nav navbar-nav">
	                <li class="dropdown">
	                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cogs" aria-hidden="true"></i> メニュー <span class="caret"></span></a>
	                  <ul class="dropdown-menu" role="menu">
	                  	<li><a href="/company/partner/add">パートナー登録</a></li>
	                  	<li><a href="/company/client/add">クライアント登録</a></li>
	                    <li class="divider"></li>
	                    <li><a href="/task/">タスク管理</a></li>
	                    <!-- <li class="divider"></li>
	                    <li><a href="/statistics/">集計</a></li> -->
	                  </ul>
	                </li>
	              </ul>                        
	            </div>
              	
              </div><!-- /.navbar-custom-menu -->
          </div><!-- /.container-fluid -->
        </nav>
      </header>