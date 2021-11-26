(function () {
	var servicePackage;

	//References to the html elements
	viewElements = {
		//loginBtn: document.getElementById("login-btn")
	}

	//Call BuyService and retrive
	window.addEventListener("load", () => {
		makeCall("GET", 'BuyService?packageId=' + getParameter("packageId"), null,
			function (req) {
				if (req.readyState == XMLHttpRequest.DONE) {
					if (req.status == 200) {
						servicePackage = JSON.parse(req.responseText);
						document.getElementById("packageId").value = servicePackage.id;
						for (var i = 0; i < servicePackage.validityPeriods.length; i++) {
							period = servicePackage.validityPeriods[i];
							var option = document.createElement("option");
							option.value = servicePackage.validityPeriods[i].id;
							option.innerHTML = period.months + "m - " + period.monthlyFee + "€/m";

							document.getElementById("validityPeriod").appendChild(option)
						}
						for (var i = 0; i < servicePackage.optionalProducts.length; i++) {
							var product = servicePackage.optionalProducts[i];
							var checkboxDiv = document.createElement("div");
							checkboxDiv.setAttribute("class", "checkbox");

							var label = document.createElement("label");
							label.setAttribute("for", product.id);

							label.innerHTML = "<input type='checkbox' name='optional-products' value='" + servicePackage.optionalProducts[i].id + "'>" + product.name + " - " + product.monthlyFee + "€/m";
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



	document.getElementById("submit-button").addEventListener("click", (e) => {
		var HTMLForm = e.target.closest("form");
		if (HTMLForm.checkValidity()) {
			makeCall("POST", 'CreateOrder', HTMLForm,
				function (req) {
					if (req.readyState == XMLHttpRequest.DONE) {
						if (req.status == 200) {
							order = JSON.parse(req.responseText);
							sessionStorage.setItem("order", JSON.stringify(order));
							window.location.replace("confirmation.html");
						}
					}
				}
			);

		}
	});


	function getCheckedBoxes(chkboxName) {
		var checkboxes = document.getElementsByName(chkboxName);
		var checkboxesChecked = [];
		// loop over them all
		for (var i = 0; i < checkboxes.length; i++) {
			// And stick the checked ones onto an array...
			if (checkboxes[i].checked) {
				checkboxesChecked.push(checkboxes[i].value);
			}
		}
		return checkboxesChecked;
	}





}())