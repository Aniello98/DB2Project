(function () {

    var order = null;
    //References to the html elements
    viewElements = {
        //loginBtn: document.getElementById("login-btn")
    }

    window.addEventListener("load", () => {
		makeCall("GET", 'LoadEmployeeContent', null,
			function (req) {
				if (req.readyState == XMLHttpRequest.DONE) {
					if (req.status == 200) {
						response = JSON.parse(req.responseText);
						for (var i = 0; i < response.services.length; i++) {
							var service = response.services[i];
							var checkboxDiv = document.createElement("div");
							checkboxDiv.setAttribute("class", "checkbox");

							var label = document.createElement("label");
							label.setAttribute("for", service.id);

                            var string = "";

                            if (service.minutes != 0) {
                                string =  " - " + service.minutes + " minutes at " + service.feeMinutes + "€";
                              }
                              if (service.sms != 0) {
                                string =  " - " + service.sms + " sms at " + service.feeSms + "€";
                              }
                              if (service.giga != 0) {
                                string =  " - " + service.giga + " giga at " + service.feeGiga + "€";
                              }
              

							label.innerHTML = "<input type='checkbox' name='services' value='" + response.services[i].id + "'>" + service.type + string ;
							checkboxDiv.appendChild(label);

							document.getElementById("servicesContainer").appendChild(checkboxDiv)
						}
						for (var i = 0; i < response.optionalProducts.length; i++) {
							var product = response.optionalProducts[i];
							var checkboxDiv = document.createElement("div");
							checkboxDiv.setAttribute("class", "checkbox");

							var label = document.createElement("label");
							label.setAttribute("for", product.id);

							label.innerHTML = "<input type='checkbox' name='optional-products' value='" + product.id + "'>" + product.name + " - " + product.monthlyFee + "€/m";
							checkboxDiv.appendChild(label);

							document.getElementById("checkboxContainer").appendChild(checkboxDiv)
						}

						//create selects and append them
						document.getElementById("validityPeriod");
					}
				}
			}
		);
	}, false);

    document.getElementById("packagebutton").addEventListener("click", () => {
        var form = e.target.closest("form");
        if (form.checkValidity()) {
            makeCall("POST", 'CreatePackage', e.target.closest("form"),
                function (req) {
                    if (req.readyState == XMLHttpRequest.DONE) {
                        var message = req.responseText;
                        switch (req.status) {
                            case 200:
                                
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




}())