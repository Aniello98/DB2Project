(function () {

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
							console.log(service.type);
							console.log(service.minutes);

                            if (service.minutes != 0) {
                                string =  " - " + service.minutes + " minutes at " + service.feeMinutes + "€";
                              }
                              if (service.sms != 0) {
                                string = string + " - " + service.sms + " sms at " + service.feeSms + "€";
                              }
                              if (service.giga != 0) {
                                string = string +  " - " + service.giga + " giga at " + service.feeGiga + "€";
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
					}else if(req.status == 401){
						window.location.replace("unauthorised.html");
						return;
					}
				}
			}
		);
	}, false);

    document.getElementById("packagebutton").addEventListener("click", (e) => {
        var form = e.target.closest("form");
        if (form.checkValidity()) {
            var validityPeriods=[];
                    if (document.getElementById("check-12").checked){
                        validityPeriods.push({"months":12, "monthlyFee": document.getElementById("fee-12").value});
                    }
                    if (document.getElementById("check-24").checked){
                        validityPeriods.push({"months":24, "monthlyFee": document.getElementById("fee-24").value});
                    }
                    if (document.getElementById("check-36").checked){
                        validityPeriods.push({"months":36, "monthlyFee": document.getElementById("fee-36").value});
                    }
                   
            var data = new FormData(form);
            data.append("validityPeriods", JSON.stringify(validityPeriods));
            makeCallForm("POST", 'CreatePackage', data,
                function (req) {
                    if (req.readyState == XMLHttpRequest.DONE) {
                        document.getElementById("package_message").textContent = req.responseText;
                    }
                }
            );
        } else {
            form.reportValidity();
        }
    });


    document.getElementById("opbutton").addEventListener('click', (e) => {
        var form = e.target.closest("form");
        if (form.checkValidity()) {
            console.log("Create..");
          makeCall("POST", 'CreateProduct', e.target.closest("form"),
            function(req) {
              if (req.readyState == XMLHttpRequest.DONE) {
                document.getElementById("product_message").textContent = req.responseText;
              }
            }
          );
        } else {
            form.reportValidity();
        }
      });

    document.getElementById("check-12").addEventListener("change", ()=>{
        if(document.getElementById("check-12").checked){
            document.getElementById("fee-12").style.display = "block";
        }
        else{
            document.getElementById("fee-12").style.display = "none"
        }
    })

    document.getElementById("check-24").addEventListener("change", ()=>{
        if(document.getElementById("check-24").checked){
            document.getElementById("fee-24").style.display = "block"
        }
        else{
            document.getElementById("fee-24").style.display = "none"
        }
    })

    document.getElementById("check-36").addEventListener("change", ()=>{
        if(document.getElementById("check-36").checked){
            document.getElementById("fee-36").style.display = "block"
        }
        else{
            document.getElementById("fee-36").style.display = "none"
        }
    })


}())