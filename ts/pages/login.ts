/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;

    export class LoginController extends AbstractPage {
        private static instances: LoginController[] = [];

        public static readStorage(): void {
            for ( var i: number = 0; i < LoginController.instances.length; ++i ) {
                LoginController.instances[i].instanceReadStorage();
            }
        }

        private instanceReadStorage(): void {
            this.$scope.$apply(() => {
                this.$scope.server = localStorage.getItem("serverUrl");
                this.$scope.username = localStorage.getItem("username");
                if ( this.$scope.server && this.$scope.username ) {
                    setTimeout(() => $(".login-container input[name='pass']").select(), 0);
                }
            });
        }

        protected init(): void {
            LoginController.instances.push(this);
            this.$scope.create = () => HistoryController.load("/register");
            this.$scope.login = () => {
                ApiController.instance.setServerUrl(this.$scope.server);
                ApiController.instance.login.request(new LoginRequest(this.$scope.username, this.$scope.password), res => {
                    if ( res.authentication == "success" ) {
                        localStorage.setItem("serverUrl", this.$scope.server);
                        localStorage.setItem("username", this.$scope.username);
                        HistoryController.load("/home");
                    } else {
                        Materialize.toast("Invalid username or password!", 4000);
                    }
                });
            };
            setTimeout(() => this.instanceReadStorage(), 0);
        }
    }
}
