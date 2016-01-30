angular.module('dream-box-admin').controller('AdminUserController', ['$scope', 'AuthenticationService', '$rootScope', 
	function ($scope, AuthenticationService, $rootScope) {
		
	var vm = this;

	vm.isLogged = function() {
		return AuthenticationService.HasCredentials();
	};

	vm.logout = function() {
		return AuthenticationService.ClearCredentials();
	};

	vm.getEmail = function() {
		if($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.email) {
			return $rootScope.globals.currentUser.email;
		}
		return '';
	};
}]);