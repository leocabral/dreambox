angular.module('dream-box-admin').controller('AdminList', ['$scope', 'AuthenticationService', '$rootScope', '$http',
	function ($scope, AuthenticationService, $rootScope, $http) {
		
	var vm = this;
	vm.admins = [];
	
	$http.get('/api/admin').then(function(response){
		vm.admins = response.data;
	});	

}]);