angular.module('dream-box-admin').controller('HelpersList', ['$scope', 'AuthenticationService', '$rootScope', '$http',
	function ($scope, AuthenticationService, $rootScope, $http) {
		
	var vm = this;
	vm.helpers = [];
	$http.get('/api/helpers').then(function(response){
		vm.helpers = response.data;
	});	
}]);