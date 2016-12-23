/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;

    export class LoginController extends AbstractPage {
        protected init(): void {
            this.$scope.create = () => HistoryController.load("/create");
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
            this.$scope.server = localStorage.getItem("serverUrl");
            this.$scope.username = localStorage.getItem("username");
            if ( this.$scope.server && this.$scope.username ) {
                setTimeout(() => $(".login-container input[name='pass']").select(), 0);
            }
        }
    }
}
