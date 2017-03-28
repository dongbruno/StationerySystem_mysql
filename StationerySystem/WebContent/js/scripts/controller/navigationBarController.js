define(['app'], function(app){
	app.controller('navigationBarCtrl', function($scope, $http){
		
		
		$scope.defaultUser = function(){
			$http.get("getStaffTest?isDefault="+true, {
				 "Accept": "application/json;charset=utf-8",
				 "Accept-Charset": "charset=utf-8"
			 }).then(function(response){
				 $scope.$root.user = response.data;
			 }, function(err){
				 console.log(err);
			 });
		}
		$scope.changeUser = function(){
			$http.get("getStaffTest?isDefault="+false, {
				 "Accept": "application/json;charset=utf-8",
				 "Accept-Charset": "charset=utf-8"
			 }).then(function(response){
				 $scope.$root.user = response.data;
			 }, function(err){
				 console.log(err);
			 });
		}
	})
})