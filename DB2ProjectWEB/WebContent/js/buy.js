(function() {
	var viewElements;

	//References to the html elements
	viewElements = {
		//loginBtn: document.getElementById("login-btn")
	}

	//Call BuyService and retrive
	window.addEventListener("load", () => {
        makeCall("GET", 'BuyService?service=' + getParameter("service"), null,
        function(req) {
          if (req.readyState == XMLHttpRequest.DONE) {
            var message = req.responseText;
            switch (req.status) {
              case 200:
            	sessionStorage.setItem('username', message);
                window.location.href = "index.html";
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
	}, false);





}())