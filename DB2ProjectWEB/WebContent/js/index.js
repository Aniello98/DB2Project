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
      document.getElementById("activation-schedule").style.display = "none";
    }
    else {
      loadRejected(username);
      loadSchedule(username);
    }

  }, false);

  var loadRejected = function (username) {

    var payRejected = (id) => {
      makeCall("GET", "GetOrder?id=" + id, null,
        function (req) {
          if (req.readyState == XMLHttpRequest.DONE) {
            if (req.status == 200) {
              order = JSON.parse(req.responseText);
              sessionStorage.setItem("order", JSON.stringify(order));
              window.location.replace("confirmation.html");
            }
            else if(req.status == 401){
              window.location.replace("unauthorised.html");
              return;
            }
          }
        });
    }

    makeCall("GET", 'LoadRejected?username=' + username, null,
      function (req) {
        if (req.readyState == XMLHttpRequest.DONE) {
          if (req.status == 200) {
            var rejectedOrders = [];
            rejectedOrders = JSON.parse(req.responseText);
            if(rejectedOrders.length == 0){
              document.getElementById("rejected-container").style.display = "none";
              return;
            }
            rejectedOrders.forEach((o) => {
              var container = document.createElement('div');
              container.setAttribute("class", "alert alert-danger to-pay");
              container.setAttribute("role", "alert");

              container.innerHTML = "#" + o.id + ": " + o.creationDate + " - " + o.totalValue;
              var id = o.id;
              container.addEventListener("click", ()=>{
                payRejected(id);}, id);
              document.getElementById("rejected-list").appendChild(container);
            });
              

          }
          else if(req.status == 401){
						window.location.replace("unauthorised.html");
						return;
					}
        }
      }
    );

  }

  var loadSchedule = function (username){
    makeCall("GET", 'LoadSchedule?username=' + username, null,
    function (req) {
      if (req.readyState == XMLHttpRequest.DONE) {
        if (req.status == 200) {
          var schedule = [];
          schedule = JSON.parse(req.responseText);
          if(schedule.length == 0){
            document.getElementById("rejected-container").style.display = "none";
            return;
          }
          schedule.forEach((o) => {
            var tr = document.createElement("tr");
            var id = document.createElement("td");
            var activation = document.createElement("td");
            var expiration = document.createElement("td");

            id.innerHTML = o.orderId;
            activation.innerHTML = o.activationDate;
            expiration.innerHTML = o.expirationDate;

            tr.appendChild(id);
            tr.appendChild(activation);
            tr.appendChild(expiration);
            document.getElementById("schedule-table").appendChild(tr);
          });
            

        }
        else if(req.status == 401){
          window.location.replace("unauthorised.html");
          return;
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
          else if(req.status == 401){
						window.location.replace("unauthorised.html");
						return;
					}
        }
      }
    );
  }

}());