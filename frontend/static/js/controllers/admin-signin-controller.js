angular.module('dream-box-admin').controller('SigninController', ['$scope', 'AuthenticationService', 
	function($scope, AuthenticationService){
	var vm = this;
	
	vm.credentials = {
		email : '',
		password : ''
	};

	vm.login = function() {
		AuthenticationService.Login(vm.credentials.email, vm.credentials.password);
	};
}]);
