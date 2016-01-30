var Helper = (function() {
	var cache = {};
	var Helper = {
		'id' : function() {
			if(cache['loggedUser']) {
				return id;
			} else {
				return undefined;
			}
		},
		isLogged : function() {
			return cache['loggedUser'] != undefined;
		}, 
		login : function(helper) {
			cache['loggedUser'] = helper;
		},
		logout : function() {
			delete cache['loggedUser'];
		}
	};
	return Helper;
})();