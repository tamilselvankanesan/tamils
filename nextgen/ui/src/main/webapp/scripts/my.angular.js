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

myangular.controller("packet", function($scope, $http) {
	//with param
	$scope.getPackets = function() {
		$http({method: "GET", 
			url: "http://localhost:8080/rest/packet/getPacketsSet",
			params:{"input" : $scope.caseNum}}).then (function(response) {
				$scope.packetDTO = response.data.packets;		
			});
		/*alert($scope.schParam);  //without param
		$http.get("http://localhost:8080/rest/packet/getPacketsSet").then(function(response) {
			$scope.packetDTO = response.data.packets;		
		});*/
	}
	$scope.getPacketsUsingJson = function() {
		var jsonParamObj = {packetName: $scope.pName, doc: $scope.docNum};
		var res = $http.post("http://localhost:8080/rest/packet/getPacketsByJson", jsonParamObj);
		/*$http.post("http://localhost:8080/rest/packet/getPacketsByJson", JSON.stringify(jsonParamObj)).success(function(response) {
			alert('ss');
		}).error(function(response) {
			alert('s1s');
		});*/ //doesn't work . post.success doesn't work
		
		res.then(function(data) {
			$scope.packetDTO = data.data;
		});//works
		
		/*$http.get("http://localhost:8080/rest/packet/getPacketsByJson", jsonParamObj).then(function(response) {
			$scope.packetDTO = response.data.packets;
		});*/ //cannot send JSON body in GET request
	}
});

