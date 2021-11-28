(function () {
	var order = null;

	//References to the html elements
	viewElements = {
		//loginBtn: document.getElementById("login-btn")
	}

	window.addEventListener("load", () => {
		order = JSON.parse(sessionStorage.getItem("order"));
		if (sessionStorage.getItem("username") != null) {
			document.getElementById("login-reg-btn").style.display = "none";
		}
		else {
			document.getElementById("confirm-btn").style.display = "none";
		}
		populateSummary();
	}, false);


	function populateSummary() {
		var validityPeriod = order.validityPeriod;
		var optionalProducts = order.optionalProducts;
		var costOptionalProducts = 0;

		document.getElementById("packageField").innerHTML = order.servicePackage.name;
		document.getElementById("dateField").innerHTML = order.startDate;
		document.getElementById("validityField").innerHTML = validityPeriod.months + "m - " + validityPeriod.monthlyFee + "€";
		//document.getElementById("dateField").innerHTML = order.startDate;
		var productsString = "";
		for (i in optionalProducts) {
			if (productsString == "") {
				productsString = optionalProducts[i].name + " - " + optionalProducts[i].monthlyFee + "€";
			}
			else {
				productsString = productsString + "<br>" + optionalProducts[i].name + " - " + optionalProducts[i].monthlyFee + "€";
			}

			costOptionalProducts = costOptionalProducts + optionalProducts[i].monthlyFee;

		}
		var totalPrice = (validityPeriod.monthlyFee * validityPeriod.months) + (costOptionalProducts * validityPeriod.months);

		order.totalValue = totalPrice;
		document.getElementById("productsField").innerHTML = productsString;
		document.getElementById("priceField").innerHTML = totalPrice + "€";
	}

	document.getElementById("login-reg-btn").addEventListener("click", () => {
		window.location.replace("login.html?redirect=confirmation");
	})
	document.getElementById("confirm-btn").addEventListener("click", () => {

		var data = new FormData();
		data.append("order", JSON.stringify(order));
		makeCallForm("POST", 'ConfirmOrder?user=' + sessionStorage.getItem("username"), data,
			function (req) {
				if (req.readyState == XMLHttpRequest.DONE) {
					if (req.status == 200) {
						order = JSON.parse(req.responseText);
						sessionStorage.setItem("order", JSON.stringify(order));
						window.location.replace("order_response.html");
					}
				}
			}
		);
	})

})();


