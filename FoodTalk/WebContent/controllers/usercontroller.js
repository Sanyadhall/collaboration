app.controller("UserController",function($scope,$http,$location,$rootScope,$cookieStore){
	
	
	$scope.user={"email":"","password":"","firstName":"","lastName":"","mobileNumber":"","role":"","onlineStatus":""};
	
	

	
	$scope.registerUser=function(){
		console.log("I m Registering User");
		console.log($scope.user);
		
		$http.post("http://localhost:5467/Food_middleware/registerUser",$scope.user)
		.then(
		function(response){
			alert('Registered Successfully..');
			$location.path("login");

			
		},function(response){
			$scope.error=response.data;
		});
	};
	
	$scope.loginUser=function(){
		console.log("I m login User function"+$scope.user.email+" "+$scope.user.password);
		$http.post("http://localhost:5467/Food_middleware/validate",$scope.user)
		.then(
		function(response){
			
			$scope.user=response.data;
			$rootScope.currentUser=response.data;
			$cookieStore.put('userDetails',response.data);
			console.log($scope.user);
			console.log($scope.user.role);
			
			//$window.location.reload();
			$location.path("userHome");
			
		},function(response){
			alert("User is invalid");
			$scope.error=response.data;
		});
	
	};
	$rootScope.logout=function(){
		console.log('LogOut function');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path("logout");
	}
	
	$scope.update=function(){
		console.log("I am updating profile");
		console.log($rootScope.currentUser);
		
		$scope.user=$rootScope.currentUser;
		$http.post("http://localhost:5467/Food_middleware/update",$scope.user)
		.then(
		function(response){
			
			$scope.user=response.data;
			$rootScope.currentUser=response.data;
			$cookieStore.put('userDetails',response.data);
			console.log($scope.user);
			console.log($scope.user.role);
			
			alert("Profile Updated");
			//$window.location.reload();
			$location.path("viewProfile");
			
		},function(response){
			alert("Problem in Updating");
			$scope.error=response.data;
		});
		
	}
	
});


	
	