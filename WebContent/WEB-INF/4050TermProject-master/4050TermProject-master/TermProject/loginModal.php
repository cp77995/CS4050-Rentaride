<?php

echo'
<link rel="stylesheet" href="loginModal.css">

<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	  <div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>login</h1><br>
				  <form>
					<input type="text" name="user" placeholder="username">
					<input type="password" name="pass" placeholder="password">
					<input type="submit" name="login" class="login loginmodal-submit" value="submit">
				  </form>
				</div>
			</div>
		  </div>
          '
    ;
    
?>