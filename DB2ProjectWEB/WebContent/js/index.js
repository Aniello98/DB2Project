(function () {
  var viewElements, packages = [];

  //References to the html elements
  viewElements = {
    //loginBtn: document.getElementById("login-btn")
  }

  //Call BuyService and retrive
  window.addEventListener("load", () => {
    loadPackages();
    var username = sessionStorage.getItem("username");
    if (username == null) {
      document.getElementById("rejected-container").style.display = "none";
    }
    else {
      loadRejected(username);
    }

  }, false);

  var loadRejected = function (username) {

    makeCall("GET", 'LoadRejected?username=' + username, null,
      function (req) {
        if (req.readyState == XMLHttpRequest.DONE) {
          if (req.status == 200) {
            rejectedOrders = JSON.parse(req.responseText);
            for (i in rejectedOrders) {
              var container = document.createElement('div');
              container.setAttribute("class", "alert alert-danger to-pay");
              container.setAttribute("role", "alert");

              container.innerHTML = "#"+rejectedOrders[i].id+": "+rejectedOrders[i].creationDate + " - " + rejectedOrders[i].totalValue;
              container.addEventListener("click", ()=>{
                makeCall("GET", "GetOrder?id="+rejectedOrders[i].id, null,
                function (req) {
                  if (req.readyState == XMLHttpRequest.DONE) {
                    if (req.status == 200) {
                      order = JSON.parse(req.responseText);
                      sessionStorage.setItem("order", JSON.stringify(order));
                      window.location.replace("confirmation.html");
                    }
                  }
                });
              });
              document.getElementById("rejected-list").appendChild(container);
            }

          }
        }
      }
    );

  }

  var loadPackages = function () {
    makeCall("GET", 'LoadPackages', null,
      function (req) {
        if (req.readyState == XMLHttpRequest.DONE) {
          if (req.status == 200) {
            packages = JSON.parse(req.responseText);
            packages.forEach(function (package) {
              var card = document.createElement("div");
              card.setAttribute("class", "card service-package");

              var header = document.createElement("div");
              header.setAttribute("class", "card-header");
              var title = document.createElement("h3");
              title.innerHTML = package.name;
              header.appendChild(title);

              var body = document.createElement("div");
              header.setAttribute("class", "card-body");

              var string = "";
              package.services.forEach(function (s) {
                string = string + "<p><b>" + s.type + "</b>";
                if (s.minutes != 0) {
                  string = string + " - " + s.minutes + " minutes at " + s.feeMinutes + "€";
                }
                if (s.sms != 0) {
                  string = string + " - " + s.sms + " sms at " + s.feeSms + "€";
                }
                if (s.giga != 0) {
                  string = string + " - " + s.giga + " giga at " + s.feeGiga + "€";
                }

                string = string + "<p>";
              });



              body.innerHTML = string + '<a href="buy.html?packageId=' + package.id + '" class="btn btn-primary float-right">Buy</a>';
              card.appendChild(header);
              card.appendChild(body)
              document.getElementById("packagesContainer").appendChild(card);
            });
          }
        }
      }
    );
  }

}());