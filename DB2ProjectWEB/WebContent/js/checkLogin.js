(function() {
	var viewElements;

	//References to the html elements
	viewElements = {
		loginBtn: document.getElementById("login-btn"),
		usernameField: document.getElementById("usernameField")
	}

	//Check login
	window.addEventListener("load", () => {
		if (sessionStorage.getItem("username") == null) {
			viewElements.loginBtn.innerHTML = "Login";
			viewElements.usernameField.innerHTML = "";
			viewElements.loginBtn.setAttribute("href", "login.html");
		} else {
			viewElements.usernameField = sessionStorage.getItem("username");
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