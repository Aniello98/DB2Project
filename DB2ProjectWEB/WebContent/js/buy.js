(function() {
	var viewElements, validityPeriods=[], startDate, optionalProducts=[];

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
								option.value = period.months+"m - "+period.monthlyFee+"€";
								option.innerHTML = period.months+"m - "+period.monthlyFee+"€";

								document.getElementById("validityPeriod").appendChild(option)
							});
              response.optionalProducts.forEach(function (product) {
							var checkboxDiv = document.createElement("div");
							checkboxDiv.setAttribute("class", "checkbox");

							var label = document.createElement("label");
							label.setAttribute("for", product.id);

							label.innerHTML = "<input type='checkbox' name='optional-products' value='"+product.name+"'>"+product.name;
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

	/** 
	document.getElementById("validityPeriod").addEventListener("change", ()=>{
		validityPeriod = document.getElementById("validityPeriod").value;
	});

	document.getElementById("startDate").addEventListener("change", ()=>{
		startDate = document.getElementById("startDate").value;
		alert(startDate);
	});

	document.getElementById("checkboxContainer").addEventListener("change", ()=>{
		var checkboxes = document.getElementsByName("optional-products");
		optionalProducts = [];
		for(c=0; c<checkboxes.length; c++){
			if(checkboxes[c].checked){
				optionalProducts.push(checkboxes[c]);
			}
		}
		alert(JSON.stringify(optionalProducts));
	})

	*/

	document.getElementById("submit-button").addEventListener("click", (e)=>{
		var form = new FormData(e.target.closest("form"));
		var checkedBoxes = getCheckedBoxes("optional-products");
		form.append("optionalProducts", checkedBoxes);

		document.getElementById("packageField").innerHTML = getParameter("serviceName");
		document.getElementById("validityField").innerHTML = form.get("validityPeriod");
		document.getElementById("dateField").innerHTML = form.get("startDate");
		var productsString = "";
		for(i in checkedBoxes){
			if(productsString==""){
				productsString = checkedBoxes[i];
			}
			else{
				productsString = productsString +"\n" + checkedBoxes[i];
			}
		}
		document.getElementById("productsField").innerHTML = productsString;
		$("#modal").modal();
		
	
	});

	function getCheckedBoxes(chkboxName) {
		var checkboxes = document.getElementsByName(chkboxName);
		var checkboxesChecked = [];
		// loop over them all
		for (var i=0; i<checkboxes.length; i++) {
		   // And stick the checked ones onto an array...
		   if (checkboxes[i].checked) {
			  checkboxesChecked.push(checkboxes[i].value);
		   }
		}
		return checkboxesChecked;
	  }





}())