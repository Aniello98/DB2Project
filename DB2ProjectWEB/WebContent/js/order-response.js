(function () {

    var order = null;
    //References to the html elements
    viewElements = {
        //loginBtn: document.getElementById("login-btn")
    }

    //Call BuyService and retrive
    window.addEventListener("load", () => {
        
        order = JSON.parse(sessionStorage.getItem("order"));

        if (order.rejected == 1) {
            document.getElementById("order-ok").style.display = "none";
        }
        else {
            document.getElementById("order-ko").style.display = "none";
        }
    }, false);


    document.getElementById("retry-btn").addEventListener("click", () => {
        window.location.replace("confirmation.html");
    })

    document.getElementById("home-btn-ok").addEventListener("click", goToHome());
    document.getElementById("home-btn-ko").addEventListener("click", goToHome());
        
    var goToHome = function () {
        sessionStorage.removeItem("order");
        window.location.replace("index.html");
    }




}())