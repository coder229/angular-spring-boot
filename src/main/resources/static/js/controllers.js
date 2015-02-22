'use strict';

/* Controllers */

var controllers = angular.module('controllers', []);

controllers.controller('headerController', function ($scope, $http, $location) {
	$scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };
});

/* To Do*/ 

controllers.controller('itemController', function ($scope, $http, $location) {
	console.log("item");
	$scope.refresh = function() {
		$http.get('task').success(function(data, status, headers, config) {
			$scope.items = data;
		});
	};
	$scope.refresh();
	$scope.newItem = function() {
    	console.log("new item");
		$location.path('/detail/new');
    };
    $scope.removeItem = function(item) {
    	if ((item.id != 'new') && confirm('Remove item ' + item.name + '?')) {
        	console.log("removing item");
	    	$http({method: 'DELETE', url: 'task/' + item.id})
		    	.success(function(data, status, headers, config) {
					$scope.alerts = [
	                	{ type: 'success', msg: 'Item saved' }
					];				
					$scope.refresh();
		    	})
				.error(function(data, status, headers, config) {
					$scope.alerts = [
	                	{ type: 'danger', msg: data }
					];				
				});
    	}
    };
});

controllers.controller('itemDetailController', function ($scope, $routeParams, $http, $location) {
	console.log("item detail");
	$scope.item = { created: new Date() };

	if ($routeParams.itemId != undefined) {
		$http.get('task/' + $routeParams.itemId).success(function(data, status, headers, config) {
			$scope.item = data;
		});
	}
	$scope.save = function(item) {
    	console.log("save item");
		if (item.id) {
		  	$http.put('task/' + item.id, item)
			  	.success(function(data, status, headers, config) {
			  		$scope.item = data;
			  		$location.path('/list');
				})
				.error(function(data, status, headers, config) {
					$scope.alerts = [
	                	{ type: 'danger', msg: data }
					];				
				});
		} else {
		  	$http.post('task', item)
		  		.success(function(data, status, headers, config) {
		  			$scope.item = data;
			  		$location.path('/list');
				})
				.error(function(data, status, headers, config) {
					$scope.alerts = [
	                	{ type: 'danger', msg: data }
					];				
				});
		}
    };
	$scope.closeAlert = function(index) {
		  $scope.alerts.splice(index, 1);
	};
});
