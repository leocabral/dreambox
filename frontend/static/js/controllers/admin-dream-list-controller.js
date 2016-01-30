angular.module('dream-box-admin').controller('DreamList', ['$scope', 'AuthenticationService', '$rootScope', 
	function ($scope, AuthenticationService, $rootScope) {
		
	var vm = this;
	console.log('DreamList', this)
	vm.isLogged = function() {
		return AuthenticationService.HasCredentials();
	};	
}]);