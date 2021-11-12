(function() {
	var viewElements;

	//References to the html elements
	viewElements = {
		loginBtn: document.getElementById("login-btn")
	}

	//Check login
	window.addEventListener("load", () => {
		if (sessionStorage.getItem("username") == null) {
			viewElements.loginBtn.innerHTML = "Login";
			viewElements.loginBtn.setAttribute("href", "login.html");
		} else {
			viewElements.loginBtn.innerHTML = "Logout";
			viewElements.loginBtn.addEventListener("click", () => {
				sessionStorage.removeItem("username");
				makeCall("GET", "Logout", null, () => {
					window.location.replace("index.html");
				})

			});
		}
	}, false);





}())