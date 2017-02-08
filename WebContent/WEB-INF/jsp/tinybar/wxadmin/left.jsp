<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		<!-- 本页面涉及的js函数，都在head.js页面中     -->
		<div class="snap-drawers">
        <!-- Left Sidebar -->
        <div class="snap-drawer snap-drawer-left">  
            <a  href="javascript:void(0)" onclick="siMenu('wxuser/tophoneindex?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName}'  )"  class="selected-item"><i class="fa fa-home"></i>主页</a>
             <a href="javascript:void(0)"  onclick="siMenu('wxorder/listorders?fromUserName=${pd.fromUserName }&toUserName=${pd.toUserName }' )"   ><i class="fa fa-cog"></i>我的订单</a>
         <!--   <a href="page-media.html"><i class="fa fa-picture-o"></i>Media</a>
            <a href="page-blog.html"><i class="fa fa-pencil"></i>Blog</a>
            <a href="contact.html"><i class="fa fa-envelope-o"></i>Contact</a>
            <a href="#"><i class="fa fa-facebook"></i>Like Us</a>
            <a href="#"><i class="fa fa-twitter"></i>Follow Us</a>
            <a href="#"><i class="fa fa-google-plus"></i>Follow us</a>
            <a href="#" class="show-share-bottom"><i class="fa fa-retweet"></i>Share</a> -->
            <a href="javascript:void(0)" class="sidebar-close"><i class="fa fa-times"></i>关闭</a>
        </div>
    </div>
    
    
    <a href="#" class="footer-ball"><i class="fa fa-navicon"></i></a>