<?php

echo '
<link rel="stylesheet" href="navigationBar.css">
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a href="#" class="navbar-left"><img src="logo3.png"></a>
      <a class="navbar-brand" href="#">rent-a-ride</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="#">the process</a></li>
        <li><a href="#">current rates</a></li> 
        <li><a href="#">faq</a></li> 
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" data-toggle="modal" data-target="#signup-modal">sign up</a></li>
        <li><a href="#" data-toggle="modal" data-target="#login-modal">login</a></li>
      </ul>
    </div>
  </div>
</nav>

';
?>