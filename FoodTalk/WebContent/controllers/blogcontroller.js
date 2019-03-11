app.controller("BlogController",function($scope,$http,$location,$rootScope,$cookieStore,$routeParams,$window)
{
	
	$scope.blog={"blogName":"","blogContext":"","createDate":"","email":"","status":"","likes":""};
	
	var id=$routeParams.id;

	console.log('id = '+id);
	
	$scope.addBlog=function(){
		console.log("I am Adding Blog");
        console.log($scope.blog);
		
		$http.post("http://localhost:5467/Food_middleware/AddBlog",$scope.blog)
		.then(
		function(response){
			$scope.blog=response.data;
			$rootScope.currentUser=response.data;
			alert('Adding Successfully..');
			
		},function(response){
			$scope.error=response.data;
		});
	};
	
	$scope.fetchAllBlogs=function(){
		console.log("Getting all Blog");
		$http.get("http://localhost:5467/Food_middleware/listApprovedBlogs")
		.then(
				function(response){
					console.log(response.data);
					$scope.blogs=response.data;
					console.log($scope.blogs);
					
				},function(response){
					alert('No Blog Found');
		});
	}
	
	
	
	
	$scope.fetchMyBlogs=function(){
		console.log("Getting all Blog");
		console.log("Getting My blog"+$scope.currentUser.email);

		$http.get("http://localhost:5467/Food_middleware/listMyBlogs/"+$scope.currentUser.email)
		.then(
				function(response){
					console.log(response.data);
					$scope.blogs=response.data;
					console.log($scope.blogs);
					
				},function(response){
					alert('No Blog Found');
		});
	};

	$scope.fetchAllPendingBlogs=function(){
		console.log('fetching all the pending blogs');
		$http.get("http://localhost:5467/Food_middleware/listPendingBlogs")
		.then(
		function(response){
			console.log(response.data);
			$scope.blogs=response.data;
			
			
		},function(response){
			alert('No Blogs found');
			
		});
	};

	$scope.approveBlog=function(blog){
		console.log('Approving Blog');
		$http.post("http://localhost:5467/Food_middleware/approveBlog",blog)
		.then(
		function(response){	
			console.log(response.data);
			alert("Blog Approved Succesfully");
			$location.path("viewBlog");
			
		},function(response){
			console.log(response.data);
			alert("Problem in Approving Blog. Try again");
			
		});
		
	};

	$scope.rejectBlog=function(blog){
		console.log('Rejecting Blog');
		$http.post("http://localhost:5467/Food_middleware/rejectBlog",blog)
		.then(
		function(response){	
			console.log(response.data);
			alert("Blog Rejected Succesfully");
			$location.path("viewBlog");
			
		},function(response){
			console.log(response.data);
			alert("Problem in Rejecting Blog. Try again");
			
		});
	};

	$scope.incrementLikes=function(blog){
		console.log('Incrementing Blog');
		$http.post("http://localhost:5467/Food_middleware/increamentLikes",blog)
		.then(
		function(response){	
			console.log(response.data);
			$window.location.reload();
			
		},function(response){
			console.log(response.data);
			
			
		});
	};



	$scope.addComment=function(blogId,commentText){
		console.log('Adding Comment');

		$scope.blogComment={"commentId":"","commentText":commentText,"commentedOn":"","commentedBy":"","blogId":blogId};
		
		
		
		$http.post("http://localhost:5467/Food_middleware/addComment",$scope.blogComment)
		.then(
		function(response){	
			console.log(response.data);
			alert("Comment Added Succesfully");
			
		},function(response){
			console.log(response.data);
			alert("U need to login , for making any comment");
			$location.path("login");
			
		});
	};

	$scope.onShowComments=function(blogId){
		console.log('show comments for blog : '+blogId);
		$http.get("http://localhost:5467/Food_middleware/getBlogComments/"+blogId)
		.then(
		function(response){	
			console.log(response.data);
			$scope.comments=response.data;
			$scope.showComments=true;
			
		},function(response){
			console.log(response.data);
			alert("No comments available")
			
		});
		
	};

	$scope.getBlogById=function(){
		console.log('getting Blog  by ID '+id);
		$http.get("http://localhost:5467/Food_middleware/getBlog/"+id)
		.then(
		function(response){
			console.log(response.data);
			$scope.bg=response.data;
			
			
		},function(response){
			console.log(response.data);
			alert("Some error occured in fetching blog Details");
		});
	};


	
});













