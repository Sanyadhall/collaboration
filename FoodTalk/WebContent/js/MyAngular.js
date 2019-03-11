var app=angular.module('app',["ngRoute",,"ngCookies"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "Main.html"
    })
    .when("/contactUs", {
        templateUrl : "ContactUs.html"
    })
    .when("/aboutUs", {
        templateUrl : "AboutUs.html"
    })
    .when("/login", {
        templateUrl : "user\\Login.html",
        controller:'UserController'

    })
    .when("/blog", {
        templateUrl : "Blog.html"
    })
    .when("/signUpForm", {
        templateUrl : "user\\SignUp.html",
        controller:'UserController'
    })
    .when("/userHome", {
        templateUrl : "user\\UserHome.html"
        
    })
    .when("/logout", {
        templateUrl : "user\\logOut.html"
        
    }) 
    
    .when("/updatePic", {
        templateUrl : "user\\UpdateProfilePicture.html"
        
    })
    .when("/viewProfile", {
        templateUrl : "user\\ViewProfile.html"
        
    })
    .when("/updateProfile", {
        templateUrl : "user\\UpdateProfile.html"
        
    })
    
    .when("/addJob", {
        templateUrl : "job\\AddJob.html",
            controller:'JobController'

        
    })
    
    .when("/getAllJobs", {
        templateUrl : "job\\AllJobs.html",
            controller:'JobController'
 })
 
     .when('/getJob/:id',{
        templateUrl : "job\\JobDetail.html",
            controller:'JobController'
        })
        
        
         .when('/uploadprofilepic',{
        templateUrl : "user\\UpdateProfilePicture.html",
        })
        
         
        
         .when('/viewBlog',{
        templateUrl : "blog\\ViewAllBlog.html",
        controller:'BlogController'
        })
        

        .when('/addBlog',{
       templateUrl : "blog\\AddBlog.html",
       controller:'BlogController'
       })
       
       .when('/myBlog',{
           templateUrl : "blog\\MyBlog.html",
           controller:'BlogController'
           })
           
        
        .when("/viewAllAppliedJobs", {
        templateUrl : "job\\AllAppliedJobs.html",
        controller:'JobController'
        
    })
    
    .when('/viewAllPendingBlog',{
		templateUrl:"blog\\ViewAllPendingBlog.html",
		controller:"BlogController"
	})
        
	
	 .when('/getBlog/:id',{
		templateUrl:"blog\\BlogDetail.html",
		controller:"BlogController"
	})
	
	
	.when('/suggestedusers',{
		templateUrl:"friend\\SuggestedUsers.html",
		controller:"FriendController"
	})
    
	.when('/pendingrequests',{
		templateUrl:"friend\\PendingFriendRequest.html",
		controller:"FriendController"
	})
	
	.when('/friends',{
		templateUrl:"friend\\FriendDetail.html",
		controller:"FriendController"
	})
	
	
	.when('/chat',{
		templateUrl:"chat\\Chat.html",
		controller:"ChatController"
	})
    
});   

app.run(function($rootScope,$cookieStore)
		{
			console.log('I am in run function');
			console.log($rootScope.currentUser);
			
				if($rootScope.currentUser==undefined)
				{
				$rootScope.currentUser=$cookieStore.get('userDetails');
				}
				else
				{
				console.log($rootScope.currentUser.email);
				console.log($rootScope.currentUser.role);
				}
		});