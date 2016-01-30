angular.module('dream-box').controller('SigninController', ['$scope', '$http', function($scope, $http){
	var vm = this;
	vm.credentials = {
		email : '',
		password : ''
	};

	vm.login = function() {
		$http.post('/api/helpers/authenticate', vm.credentials).then(function(response){
			Helper.login(response);
			Cloud.cleanActive();
		}, function(response){
			Helper.logout();
		});
	};
}]);
