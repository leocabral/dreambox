angular.module('dream-box').controller('SignupController', ['$http', function($http){
	var vm = this;
	vm.newUser = {
		name : '',
		last_name : '',
		email : '',
		password : ''
	};
	vm.signup = function() {
		$http.post('/api/helpers', vm.newUser).then(function(){
			console.log(arguments);
		}, function(){
			console.log(arguments);
		});
	};
}]);
