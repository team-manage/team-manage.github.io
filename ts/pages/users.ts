/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;

    export class UsersController extends AbstractPage {
        protected init(): void {
            this.$scope.users = [];
        }

        protected open(): void {
            ApiController.instance.users.request(users => this.$scope.$apply(() => {
                this.$scope.users = users;
            }));
        }
    }
}