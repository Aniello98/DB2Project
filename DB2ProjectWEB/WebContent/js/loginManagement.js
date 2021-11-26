/**
 * Login management
 */

(function() {

  document.getElementById("loginbutton").addEventListener('click', (e) => {
    var form = e.target.closest("form");
    if (form.checkValidity()) {
      makeCall("POST", 'CheckLogin', e.target.closest("form"),
        function(req) {
          if (req.readyState == XMLHttpRequest.DONE) {
            var message = req.responseText;
            switch (req.status) {
              case 200:
            	sessionStorage.setItem('username', message);
              if(getParameter("redirect")=="confirmation")
              window.location.replace("confirmation.html");
              else window.location.replace("index.html");
                break;
              case 400: // bad request
                document.getElementById("login_errormessage").textContent = message;
                break;
              case 401: // unauthorized
                  document.getElementById("login_errormessage").textContent = message;
                  break;
              case 500: // server error
            	document.getElementById("login_errormessage").textContent = message;
                break;
            }
          }
        }
      );
    } else {
    	form.reportValidity();
    }
  });


	document.getElementById("signupbutton").addEventListener('click', (e) => {
	    var form = e.target.closest("form");
		
		if(form.elements[0].value == "" ||form.elements[1].value == "" ||form.elements[2].value == "" ||form.elements[3].value == "" ){
			document.getElementById("signup_message").textContent = "Complete all the fields!";
		}
		else  if(form.elements[2].value != form.elements[3].value){
			document.getElementById("signup_message").textContent = "Passwords do not match!";
		}
		else if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/.test(form.elements[1].value))){
			document.getElementById("signup_message").textContent = "The mail is not valid!";
		}
		else{
	      makeCall("POST", 'SignUp', e.target.closest("form"),
	        function(req) {
	          if (req.readyState == XMLHttpRequest.DONE) {
	            var message = req.responseText;
				document.getElementById("signup_message").textContent = message;
	            }
	          }
	      );
	    } 
		}
	    
	  );

})();