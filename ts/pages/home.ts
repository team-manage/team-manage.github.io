/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;

    export class HomeController extends AbstractPage {
        protected init(): void {
            this.$scope.LoginController = LoginController;
        }

        protected open(): void {
            ApiController.instance.recent.request(messages => this.$scope.$apply(() => {
                this.$scope.messages = messages;
            }));
        }
    }
}
