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

var Dreamer = (function() {
	var Dreamer = {
		'id' : function() {
			return '5639445604728832';
		},

		'avatar' : function() {
			return '/img/avatar.png';
		}
	}
	return Dreamer;
})();