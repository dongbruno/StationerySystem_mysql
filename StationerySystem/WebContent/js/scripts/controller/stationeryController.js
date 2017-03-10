define(['app'], function(app){
	app.controller('stationeryCtrl', function($scope, $http){
		$scope.stationery = [];
		$scope.user = {};
		$scope.$root.number = $scope.$root.number || 0;
		$scope.$root.cartStationery = $scope.$root.cartStationery || [];
		$scope.addToCart = function(newItem){
			var isContained = $scope.$root.cartStationery.some(function(item, key){
				if(item.id == newItem.id){
					$scope.$root.cartStationery[key].quantity += 1;
					$scope.$root.number += 1;
					return true;
				}
				
			});
			if(!isContained){
				$scope.$root.cartStationery.push(newItem);
				$scope.$root.number += 1;
				
			}
		}
		 $http.get("getUser", {
			 "Accept": "application/json;charset=utf-8",
			 "Accept-Charset": "charset=utf-8",
			 "Content-Type": "application/x-www-form-urlencoded;charset-utf-8"
		 }).then(function(response){
			 $scope.user = response.data;
		 }, function(err){
			 console.log(err);
		 });
		 $http.get("getStationery", {
			 "Accept": "application/json;charset=utf-8",
			 "Accept-Charset": "charset=utf-8",
		 }).then(function(response){
			 $scope.stationery = response.data;
		 }, function(err){
			 console.log(err);
		 })
		
	});
});