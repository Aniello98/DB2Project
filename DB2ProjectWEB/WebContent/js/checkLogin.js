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
			document.getElementById("usernameField").innerHTML = "";
			viewElements.loginBtn.setAttribute("href", "login.html");
		} else {
			document.getElementById("usernameField").innerHTML = sessionStorage.getItem("username");
			viewElements.loginBtn.innerHTML = "Logout";
			viewElements.loginBtn.addEventListener("click", () => {
				sessionStorage.clear();
				window.location.replace("index.html");

			});
		}
	}, false);





}())