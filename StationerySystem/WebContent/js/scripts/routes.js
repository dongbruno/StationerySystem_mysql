define([], function () {
    return {
        defaultRoute: '/show/stationery',
        routes: {
            'show': {
                templateUrl: 'views/show.html',
                url: '/show',
                dependencies: [],
                allowAnonymous: true
            },
            'show.stationery': {
                templateUrl: 'views/show-stationery.html',
                url: '/stationery',
                dependencies: ['controller/stationeryController'],
                allowAnonymous: true
            },
            'show.order': {
                templateUrl: 'views/show-order.html',
                url: '/order',
                dependencies: ['controller/orderController', 'filter/shortNameFilter'],
                allowAnonymous: true
            }, 
            'show.adminTools': {
                templateUrl: 'views/show-adminTools.html',
                url: '/adminTools',
                dependencies: ['controller/adminToolsController'],
                allowAnonymous: true
            },
            'show.cart':{
            	 templateUrl: 'views/show-cart.html',
                 url: '/cart',
                 dependencies: ['controller/cartController'],
                 allowAnonymous: true
            }
            
            
        }
    };
});