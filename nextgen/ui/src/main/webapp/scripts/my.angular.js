/**
 * 
 */
var myangular = angular.module("myangular", []);

myangular.controller("myctrl", function($scope) {
	$scope.name = "John";
});

myangular.controller("dd", function($scope) {
	$scope.ddOptions = ["One", "Two", "Three"];
	$scope.ddOptionsArr=[
	                     {val:"1", label:"One"},
	                     {val:"2", label:"Two"},
	                     {val:"3", label:"Three"}
	                     ];
});


