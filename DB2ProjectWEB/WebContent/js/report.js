(function() {
	

	//Check login
	window.addEventListener("load", () => {
		makeCall("GET", "LoadReport", null,
        function (req) {
            if (req.readyState == XMLHttpRequest.DONE) {
                if (req.status == 200) {
                    var report = JSON.parse(req.responseText);
                    report.purchasesPerPackage.forEach(e => {
                        populatePurchasesPerPackage(e)
                    });

                    report.purchasesPerPackageAndPeriod.forEach(e => {
                        populatePurchasesPerPackageAndPeriod(e)
                    });

                    report.valueSalesPerPackage.forEach(e => {
                        populateValueSalesPerPackage(e)
                    });

                    report.averageOptionalPerPackage.forEach(e => {
                        populateAverageOptionalPerPackage(e)
                    });

                    report.insolventUsers.forEach(e => {
                        populateInsolventUsers(e)
                    });

                    report.suspendedOrders.forEach(e => {
                        populateSuspendedOrders(e)
                    });

                    report.alerts.forEach(e => {
                        populateAlerts(e)
                    });

                    report.bestOptionalProductSeller.forEach(e => {
                        populateBestOptionalProductSeller(e)
                    });

                    
                    
                }
            }
        });
	}, false);


    var populatePurchasesPerPackage = (e)=>{

        
        var tr = document.createElement("tr");
        var id = document.createElement("td");
        var name = document.createElement("td");
        var amount = document.createElement("td");

        id.innerHTML = e.packageId;
        name.innerHTML = e.packageName;
        amount.innerHTML = e.amount;

        tr.appendChild(id);
        tr.appendChild(name);
        tr.appendChild(amount);

        document.getElementById("purchases-per-package-table").appendChild(tr);
    }

    var populatePurchasesPerPackageAndPeriod = (e)=>{

        
        var tr = document.createElement("tr");
        var packageId = document.createElement("td");
        var periodId = document.createElement("td");
        var packageName = document.createElement("td");
        var months = document.createElement("td");
        var monthlyFee = document.createElement("td");
        var amount = document.createElement("td");

        packageId.innerHTML = e.packageId;
        periodId.innerHTML = e.periodId;
        packageName.innerHTML = e.packageName;
        months.innerHTML = e.months;
        monthlyFee.innerHTML = e.monthlyFee;
        amount.innerHTML = e.amount;

        tr.appendChild(packageId);
        tr.appendChild(periodId);
        tr.appendChild(packageName);
        tr.appendChild(months);
        tr.appendChild(monthlyFee);
        tr.appendChild(amount);

        document.getElementById("purchases-per-package-period-table").appendChild(tr);
    }

    var populateValueSalesPerPackage = (e)=>{

        
        var tr = document.createElement("tr");
        var packageId = document.createElement("td");
        var packageName = document.createElement("td");
        var totalWith = document.createElement("td");
        var totalWithout = document.createElement("td");

        packageId.innerHTML = e.packageId;
        packageName.innerHTML = e.packageName;
        totalWith.innerHTML = e.totalWithoutOptional;
        totalWithout.innerHTML = e.totalWithOptional;

        tr.appendChild(packageId);
        tr.appendChild(packageName);
        tr.appendChild(totalWith);
        tr.appendChild(totalWithout);

        document.getElementById("value-package-table").appendChild(tr);
    }

    var populateAverageOptionalPerPackage = (e)=>{

        
        var tr = document.createElement("tr");
        var packageId = document.createElement("td");
        var packageName = document.createElement("td");
        var total = document.createElement("td");
        var average = document.createElement("td");

        packageId.innerHTML = e.packageId;
        packageName.innerHTML = e.packageName;
        total.innerHTML = e.totalProductsSold;
        average.innerHTML = e.averageOptional;

        tr.appendChild(packageId);
        tr.appendChild(packageName);
        tr.appendChild(total);
        tr.appendChild(average);

        document.getElementById("average-package-table").appendChild(tr);
    }

    var populateInsolventUsers = (e)=>{

        
        var tr = document.createElement("tr");
        var id = document.createElement("td");
        var name = document.createElement("td");

        id.innerHTML = e.id;
        name.innerHTML = e.username;

        tr.appendChild(id);
        tr.appendChild(name);

        document.getElementById("insolvent-table").appendChild(tr);
    }

    var populateSuspendedOrders = (e)=>{

        
        var tr = document.createElement("tr");
        var id = document.createElement("td");
        var name = document.createElement("td");

        id.innerHTML = e.orderId;
        name.innerHTML = e.userId;

        tr.appendChild(id);
        tr.appendChild(name);

        document.getElementById("suspended-table").appendChild(tr);
    }

    var populateAlerts = (e)=>{

        
        var tr = document.createElement("tr");
        var id = document.createElement("td");
        var amount = document.createElement("td");
        var last = document.createElement("td");

        id.innerHTML = e.user;
        amount.innerHTML = e.amount;
        last.innerHTML = e.lastRejection;

        tr.appendChild(id);
        tr.appendChild(amount);
        tr.appendChild(last);

        document.getElementById("suspended-table").appendChild(tr);
    }

    var populateBestOptionalProductSeller = (e)=>{
        
        var tr = document.createElement("tr");
        var id = document.createElement("td");
        var name = document.createElement("td");
        var total = document.createElement("td");

        id.innerHTML = e.productId;
        name.innerHTML = e.productName;
        total.innerHTML = e.salesValue;

        tr.appendChild(id);
        tr.appendChild(name);
        tr.appendChild(total);

        document.getElementById("bestseller-table").appendChild(tr);
    }





}())