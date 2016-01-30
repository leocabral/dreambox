angular.module('dream-box-admin').controller('DreamList', ['$scope', 'AuthenticationService', '$rootScope', '$http',
	function ($scope, AuthenticationService, $rootScope, $http) {
		
	var vm = this;
	vm.dreams = [];
	$http.get('/api/dreams').then(function(response){
		vm.dreams = response.data;
	});	
}]);