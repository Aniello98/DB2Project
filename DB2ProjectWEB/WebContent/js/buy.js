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
            if(req.status == 200) {
              var response = JSON.parse(req.responseText);
							response.validityPeriods.forEach(function (period) {
								var option = document.createElement("option");
								option.value = period;
								option.innerHTML = period;

								document.getElementById("validityPeriod").appendChild(option)
							});
              response.optionalProducts.forEach(function (product) {
							var checkboxDiv = document.createElement("div");
							checkboxDiv.setAttribute("class", "checkbox");

							var label = document.createElement("label");
							label.setAttribute("for", product);
							
							var checkbox = document.createElement("input");
							checkbox.type ="checkbox";
								checkbox.value = product;
								checkbox.innerHTML = product;
								checkbox.setAttribute("id", product);
								checkbox.setAttribute("name", product);

							label.innerHTML = "<input type='checkbox' id='"+ product+"' name='"+product+"'>"+product;
							checkboxDiv.appendChild(label);

								document.getElementById("checkboxContainer").appendChild(checkboxDiv)
							});
              //create selects and append them
            	document.getElementById("validityPeriod");
            }
          }
        }
      );
	}, false);





}())