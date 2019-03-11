app.controller("JobController",function($scope,$http,$location,$rootScope,$cookieStore,$window,$routeParams)
		{
	$scope.job={"id":"","jobTitle":"","jobDescription":"","skillsRequired":"","location":"","yrsofExp":"","companyName":"","salary":"","postedOn":""};
	var id=$routeParams.id;
	$scope.addJob=function(){
		console.log("add Job ");
		console.log($scope.job);
		
		$http.post("http://localhost:5467/Food_middleware/addJob",$scope.job)
		.then(
		function(response){
			alert('Job Added Successfully..');
			$location.path("userHome");
			
		},function(response){
			alert('Error in Adding Job');
			$scope.error=response.data;
			console.log($scope.error);
		});
	};
	
	$scope.fetchAllJobs=function(){
		console.log("Getting all jobs");
		$http.get("http://localhost:5467/Food_middleware/listJobs")
		.then(
				function(response){
					console.log(response.data);
					$scope.jobs=response.data;
					
					
				},function(response){
					alert('No Job found');
		});
	}
	

	$scope.getJobById=function(){
		console.log('getting job  by ID '+id);
		$http.get("http://localhost:5467/Food_middleware/getJob/"+id)
		.then(
		function(response){
			console.log(response.data);
			$scope.job=response.data;
			$http.get("http://localhost:5467/Food_middleware/checkAppliedJobs/"+id)
			.then(
			function(response){
				console.log(response.data);
				$scope.status=response.data;
				
			},function(response){
				$scope.status=response.data;
			});
			
		},function(response){
			
			
		});
	};
		
		
	
	
	$scope.applyJob=function(jId){
		console.log('Applying Job'+jId);
		$http.post("http://localhost:5467/Food_middleware/applyjob",jId)
		.then(
		function(response){
			alert('Job Applied Successfully..');
			
			
		},function(response){
			alert("You need to login first for applying the Job");
			$location.path("login");
		});
	};
	
	$scope.fetchAllAppliedJobs=function(){
		console.log('fetching all the jobs');
		$http.get("http://localhost:5467/Food_middleware/allAppliedJobs")
		.then(
		function(response){
			console.log(response.data);
			$scope.appliedjobs=response.data;
			
			
		},function(response){
			alert('No Job found');
			
		});
	};
});




