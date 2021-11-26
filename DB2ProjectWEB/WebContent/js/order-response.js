(function () {

    //References to the html elements
    viewElements = {
        //loginBtn: document.getElementById("login-btn")
    }

    //Call BuyService and retrive
    window.addEventListener("load", () => {
        var response = getParameter("response");

        if (response == "false") {
            document.getElementById("order-ok").style.display = "none";
        }
        else {
            document.getElementById("order-ko").style.display = "none";
            sessionStorage.removeItem("order");
        }
    }, false);


    document.getElementById("retry-btn").addEventListener("click", () => {
        alert("call");
    })




}())