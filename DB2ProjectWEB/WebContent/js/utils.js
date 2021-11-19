/**
 * AJAX call management
 */

	function makeCall(method, url, formElement, cback, reset = true) {

		 var req = new XMLHttpRequest(); // visible by closure
	    req.onreadystatechange = function() {
	      cback(req)
	    }; // closure
	    req.open(method, url);
	    if (formElement == null) {
	      req.send();
	    } else {
	      req.send(new FormData(formElement));
	    }
	    if (formElement !== null && reset === true) {
	      formElement.reset();
	    }
		
	    
	  }

	function getParameter(key){
  
		// Address of the current window
		address = window.location.search
	  
		// Returns a URLSearchParams object instance
		parameterList = new URLSearchParams(address)
	  
		// Returning the respected value associated
		// with the provided key
		return parameterList.get(key)
	}
