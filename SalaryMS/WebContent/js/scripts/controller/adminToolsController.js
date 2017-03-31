define(['app'], function(app){
	app.controller('adminToolsCtrl', function($scope, $http){
		$scope.setDeadline = function(){
			$http.get("setDeadline?deadline="+dateform.date.value).then(function(response){
				alert(response.data);
			}, function(err){
				alert(err);
			})
		}
		$scope.setNote = function(){
			$http.get("setNote?note="+noteform.text.value).then(function(response){
				alert(response.data);
			}, function(err){
				alert(err);
			})
		}
		
		$scope.download = function(dlType, location){
			var url = "download/"+dlType+"/"+location;
			window.location.href = url;
		}
	})
})