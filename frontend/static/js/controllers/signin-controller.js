angular.module('dream-box').controller('SigninController', ['$scope', '$http', function($scope, $http){
	var vm = this;
	vm.credentials = {
		username : '',
		password : ''
	};

	vm.login = function() {
		$http.post('/api/helpers', vm.credentials).then(function(){
			console.log(arguments);
		}, function(){
			console.log(arguments);
		});
	};
}]);
