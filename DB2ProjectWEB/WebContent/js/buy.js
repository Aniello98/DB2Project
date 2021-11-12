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
            if(req.status == 200) {

              //create selects and append them
            	document.getElementById("validityPeriod");
            }
          }
        }
      );
	}, false);





}())