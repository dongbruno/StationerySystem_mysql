define(['app'], function(app){
	app.controller('navigationBarCtrl', function($scope, $http){
		$scope.$root.user = {};
		$scope.$root.systemInfo = {};
		$scope.$root.number = $scope.$root.number || 0;
		
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
		
		$http.get("getStaff", {
			 "Accept": "application/json;charset=utf-8",
			 "Accept-Charset": "charset=utf-8"
		 }).then(function(response){
			 $scope.$root.user = response.data;
		 }, function(err){
			 console.log(err);
		 });
		
		$http.get("getSystemInfo", {
			 "Accept": "application/json;charset=utf-8",
			 "Accept-Charset": "charset=utf-8"
		 }).then(function(response){
			 $scope.$root.systemInfo = response.data;
		 }, function(err){
			 console.log(err);
		 });
		
	})
})