/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;

    export class KioskController extends AbstractPage {
        protected init(): void {
            this.$scope.pinpad = true;
            this.$scope.go = () => ApiController.instance.kiosk.request(this.$scope.pin, user => this.$scope.$apply(() => {
                this.$scope.user = user;
                this.$scope.confirm = user != null;
                if (!this.$scope.confirm) {
                    Materialize.toast("Invalid pin number", 4000);
                }
            }));
            this.$scope.notme = () => {
                this.$scope.confirm = false;
                this.$scope.pin = '';
                this.$scope.pinpad = true;
            };
            this.$scope.me = () => {
                this.$scope.confirm = false;
                this.$scope.pin = '';
                this.$scope.pinpad = true;
                ApiController.instance.kioskSignIn.request(this.$scope.user.id, response => { 
                    if (response.success){
                        Materialize.toast("You have been signed in!", 4000);
                    } else {
                        Materialize.toast("There was an error in signing in your account", 4000);
                    }
                });
            };
        }
    }
}

