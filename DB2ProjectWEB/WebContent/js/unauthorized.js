(function() {

	window.addEventListener("load", () => {
        console.log(sessionStorage.getItem("isEmployee"));
		if(sessionStorage.getItem("isEmployee") == "true"){
            document.getElementById("home-btn").setAttribute("href", "employee_page.html")
        }
        else{
            document.getElementById("home-btn").setAttribute("href", "index.html")
        }
	}, false);





}())