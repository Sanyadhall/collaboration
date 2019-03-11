app.controller("FriendController",function($scope,$http,$rootScope)
{
	
	
	$scope.friend={"id":"","fromId":"","toId":"","status":""};
	
	$scope.fetchAllsuggestedUsers=function(){
		console.log("Fetching all Suggested User");
        console.log($scope.friend);
		
		$http.post("http://localhost:5467/Food_middleware/suggestedUsers")
		.then(
				function(response){
					console.log(response.data);
					$scope.suggestedUsers=response.data;
					
					
					
				},function(response){
					alert('Error in fetching');
					$scope.error=response.data;
					console.log($scope.error);
				});
			};
			
			$scope.addFriend=function(user){
				console.log(user.email);
				$http.post("http://localhost:5467/Food_middleware/addfriend",user)
				.then(
				function(response){
				alert("Friend Request Sent")
					
				},function(response){
					alert('Error in sending Friend Request. Try again');
					
				});
			};
			
			
			$scope.fetchAllPendingUsers=function(){
				console.log("Fetching all Pending User");
		        console.log($scope.friend);
				$http.get("http://localhost:5467/Food_middleware/pendingrequests")
				.then(
				function(response){
					console.log(response.data);
					$scope.pendingUsers=response.data;

					
				},function(response){
					alert('No Pending Requests');
					
				});
			};
			
			$scope.acceptRequest=function(r){
				$http.post("http://localhost:5467/Food_middleware/acceptrequest",r)
				.then(
				function(response){
				alert("Friend Request Accepted")
					
				},function(response){
					alert('Error in accepting Friend Request. Try again');
					
				});
			};
			
			
			$scope.deleteRequest=function(r){
				$http.post("http://localhost:5467/Food_middleware/deleterequest",r)
				.then(
				function(response){
				alert("Friend Request Deleted")
					
				},function(response){
					alert('Error in deleting Friend Request. Try again');
					
				});
			};
			

			$scope.fetchAllFriends=function(){
				console.log("Fetching all friends");
		        console.log($scope.friend);
				$http.get("http://localhost:5467/Food_middleware/friends")
				.then(
				function(response){
					console.log(response.data);
					$scope.friends=response.data;

					
				},function(response){
					alert('No Friends');
					
				});
			};
			
		});
