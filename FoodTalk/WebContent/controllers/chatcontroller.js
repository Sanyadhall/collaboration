app.controller('ChatController',function($rootScope,$scope,ChatService){
	$scope.stompClient=ChatService.stompClient
	$scope.users=[];
	$scope.chats=[];
	
	$scope.$on('sockConnected',function(event,frame){
		console.log('Hello 0');
		alert('Successfully connected with WebSocket')
		$scope.userName=$rootScope.currentUser.firstName
		alert($scope.userName + ' joined the chat room')
		
		$scope.stompClient.subscribe("/app/join/"+$scope.userName,function(message){
			console.log('Hello 1');
			console.log(message.body)
			alert(message.body)
			$scope.users=JSON.parse(message.body)
			console.log('Hello 2 : '+$scope.users);
			$scope.$apply();
		})
		
		$scope.stompClient.subscribe("/topic/join",function(message){
			console.log('Hello 3');
			console.log(message);
			
			//JSON.parse(JSON.stringify(products));
			user=JSON.parse(JSON.stringify(message.body));
			
			if(user != $scope.userName && $.inArray(user, $scope.users) == -1){
				console.log('Hello 3 - 0');
				$scope.addUser(user);
				$scope.latestUser = user;
				console.log('Hello 3 - 1'+$scope.latestUser);
				$scope.$apply();
				alert($scope.latestUser + ' has joined the chat')
				$('#joinedChat').fadeIn(500).delay(10000).fadeOut(500);
			}
		})
	})
	
	$scope.addUser=function(user){
		console.log('Helllo 4');
		$scope.users.push(user)
		$scope.$apply();
	}
	
	$scope.sendMessage=function(chat){
		console.log('Hello 5 : '+chat);
		
		chat.from=$scope.userName
		$scope.stompClient.send("/app/chat",{},JSON.stringify(chat)) //json.stringify converts jspn format to string
		$rootScope.$broadcast('sendingChat',chat)
		$scope.chat.message=''
	}
	
	$scope.$on('sockConnected',function(event,frame){
		console.log('Hello 6');
		$scope.userName=$rootScope.currentUser.firstName;
		
		$scope.stompClient.subscribe("/queue/chats",function(message){
			console.log('hello 6 - 0');
			alert('message ' + message.body)
			$scope.processIncomingMessage(message,true)
		});
		
		$scope.stompClient.subscribe("/queue/chats/"+$scope.userName,function(message){
			console.log('hello 6 - 1');
			alert('message is ' + message.body)
			$scope.processIncomingMessage(message,false)
		})
	})
	
	$scope.processIncomingMessage=function(message,broadcast){
		console.log('Hello 7');
		message=JSON.parse(message.body)//message.body is chat object
		console.log('Hello 7 -0 : '+message);
		message.direction='incoming'
			if(message.from!=$scope.userName){
				console.log('Hello 7 -1 : ');
				$scope.addChat(message);
				$scope.$apply();
			}
	}
	
	$scope.addChat=function(chat){
		console.log('Hello 8');
		
		$scope.chats.push(chat)
		console.log($scope.chats);
	}
	
	$scope.$on('sendingChat',function(event,sentChat){
		console.log('Hello 9');
		chat=angular.copy(sentChat)
		chat.from='Me'
		chat.direction='outgoing'
			$scope.addChat(chat)
	})
})
