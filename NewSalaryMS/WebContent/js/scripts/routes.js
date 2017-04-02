define([], function () {
    return {
        defaultRoute: '/show',
        routes: {
        	'show': {
                templateUrl: 'views/show.html',
                url: '/show',
                dependencies: ['controller/navigationBarController'],
                allowAnonymous: true
            },
        	'show.login': {
                templateUrl: 'views/login.html',
                url: '/login',
                dependencies: ['controller/loginController'],
                allowAnonymous: true
            },
            'show.staffSalary': {
                templateUrl: 'views/show-staffSalary.html',
                url: '/staffSalary',
                dependencies: ['controller/staffSalaryController'],
                allowAnonymous: true
            },
            'show.staffTime': {
                templateUrl: 'views/show-staffTime.html',
                url: '/staffTime',
                dependencies: ['controller/staffTimeController'],
                allowAnonymous: true
            }, 
            'show.staffPassword': {
                templateUrl: 'views/show-staffPassword.html',
                url: '/staffPassword',
                dependencies: ['controller/staffPasswordController'],
                allowAnonymous: true
            },
            'show.manageStaffs': {
                templateUrl: 'views/show-manageStaffs.html',
                url: '/manageStaffs',
                dependencies: ['controller/manageStaffsController'],
                allowAnonymous: true
            },
            'show.addItem': {
                templateUrl: 'views/show-addItem.html',
                url: '/adminTools',
                dependencies: ['controller/addItemController'],
                allowAnonymous: true
            },
            'show.checkSalary': {
                templateUrl: 'views/show-checkSalary.html',
                url: '/checkSalary',
                dependencies: ['controller/checkSalaryController'],
                allowAnonymous: true
            },
            'show.generateExcel': {
                templateUrl: 'views/show-generateExcel.html',
                url: '/generateExcel',
                dependencies: ['controller/generateExcelController'],
                allowAnonymous: true
            }
            
            
            
        }
    };
});