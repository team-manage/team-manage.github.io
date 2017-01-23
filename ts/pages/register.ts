/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="../providers.ts" />
/// <reference path="login.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;
    import RegisterRequest = org.usd232.robotics.management.apis.RegisterRequest;
    import StatusResponse = org.usd232.robotics.management.apis.StatusResponse;
    let Providers = (org.usd232.robotics.management as any).Providers; // VS doesn't like Liquid

    export class RegisterController extends AbstractPage {
        protected init(): void {
            this.$scope.back = () => HistoryController.back();
            this.$scope.register = () => {
                if ( Providers.getProviderNames().indexOf(this.$scope.provider) < 0 ) {
                    Materialize.toast("Please choose a provider from the list", 4000);
                    return;
                }
                if (this.$scope.password != this.$scope.passconf) {
                    Materialize.toast("Passwords do not match", 4000);
                    return;
                }
                ApiController.instance.setServerUrl(this.$scope.server);
                ApiController.instance.register.request(new RegisterRequest(
                    this.$scope.username, this.$scope.firstname, this.$scope.lastname, this.$scope.password,
                    this.$scope.email, this.$scope.parentemail, this.$scope.phonenumber, this.$scope.provider), res => {
                        if ( res.success ) {
                            localStorage.setItem("serverUrl", this.$scope.server);
                            localStorage.setItem("username", this.$scope.username);
                            LoginController.readStorage();
                            HistoryController.load("/");
                            Materialize.toast("Your account has been created!", 4000);
                        } else {
                            Materialize.toast("There was an error creating your account.", 4000);
                        }
                    });
            };
            this.$scope.server = localStorage.getItem("serverUrl");
        }

        public constructor(name: string) {
            super(name);
            $(document).ready(() => {
                let data: any = {};
                let providers: string[] = Providers.getProviderNames();
                for ( var i: number = 0; i < providers.length; ++i ) {
                    data[providers[i]] = null;
                }
                ($(".autocomplete-register-phone-provider") as any).autocomplete({
                    "data": data
                });
            });
        }
    }
}
