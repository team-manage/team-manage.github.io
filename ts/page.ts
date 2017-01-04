/// <reference path="angular.ts" />

namespace org.usd232.robotics.management {
    import AngularController = org.usd232.robotics.management.ng.AngularController;

    export abstract class AbstractPage {
        protected $scope;
        protected $http;

        protected abstract init(): void;

        protected open(): void {
        }

        public constructor(name: string) {
            AngularController.registerController("page-" + name, ($scope, $http) => {
                this.$scope = $scope;
                this.$http = $http;
                this.init();
            });
            HistoryController.getPageController().onLoad(name, () => this.open());
        }
    }
}
