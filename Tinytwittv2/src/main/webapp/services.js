app = angular.module('tinyTwitt', []);
app.controller('tinyTwitt', function($scope, $http) {
    $http.get('https://tinytwitt-brohan.appspot.com/_ah/api/tinytwitt/v1/liste').
        then(function(response) {
            $scope.users = response.data.items;
        });
});

app.controller('nouvelUtilisateur', ['$scope','$http',function($scope, $http) {
	
	
	
	$scope.addUtilisateur = function(pseudo){
		$scope.t0= performance.now();
		$scope.master = {};
		$scope.master = angular.copy(pseudo);
		$http.post('https://tinytwitt-brohan.appspot.com/_ah/api/tinytwitt/v1/newuser', $scope.master , {headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    'transformRequest': function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    }}).then(function(response){
			$scope.user = response;
		});
		$scope.t1 = performance.now();
		$scope.perf = $scope.t1 - $scope.t0;
	}
}]);

app.controller('followController', ['$scope','$http',function($scope, $http) {
	

	$scope.followUtilisateur = function(pseudo,followed){
			$scope.t0 = performance.now();
			$scope.psd = {};
			$scope.cbl = {};
			$scope.psd = angular.copy(pseudo);
			$scope.cbl = angular.copy(followed);
			$scope.data = {"pseudo" : $scope.psd.name, "followed" : $scope.cbl.name};
			
		$http.post('https://tinytwitt-brohan.appspot.com/_ah/api/tinytwitt/v1/follow', $scope.data , {headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    'transformRequest': function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    } 		
		}).then(function(response){
			$scope.user = response;
		});
		$scope.t1 = performance.now();
		$scope.perf = $scope.t1 - $scope.t0;
	}
}]);

app.controller('tweetController', ['$scope','$http',function($scope, $http) {
	

	$scope.postTweet = function(pseudo,tweet){	
			$scope.t0 = performance.now();
			$scope.psd = {};
			$scope.twt = {};
			$scope.psd = angular.copy(pseudo);
			$scope.twt = angular.copy(tweet);
			$scope.data = {"pseudo" : $scope.psd.name, "tweet" : $scope.twt.content};
			
		$http.post('https://tinytwitt-brohan.appspot.com/_ah/api/tinytwitt/v1/tweet', $scope.data , {headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    'transformRequest': function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    } 		
		}).then(function(response){
			$scope.user = response;
		});
		$scope.t1 = performance.now();
		$scope.perf = $scope.t1 - $scope.t0;
	}
}]);

app.controller('timelineController', ['$scope','$http',function($scope, $http) {
	
	
	
	$scope.afficheTimeline = function(psd){
		$scope.t0 = performance.now();
		$scope.twet = {};
		$scope.twet = angular.copy(psd);
		$http.post('https://tinytwitt-brohan.appspot.com/_ah/api/tinytwitt/v1/timeline',{"pseudo" : $scope.twet.name}, {headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    'transformRequest': function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    }}).then(function(response){		    	
			$scope.tweets = response.data.items;
			$scope.tweets = $scope.tweets.map(function(e) {
				return {tweet : e}
			});
		});
		$scope.t1 = performance.now();
		$scope.perf = $scope.t1 - $scope.t0;
	}
}]);

