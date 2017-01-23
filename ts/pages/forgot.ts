/// <reference path="../page.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import HistoryController = org.usd232.robotics.management.HistoryController;
    import ForgotCredentialsRequest = org.usd232.robotics.management.apis.ForgotCredentialsRequest;

    export class ForgotController extends AbstractPage {
        protected init(): void {
            this.$scope.type = "username";
            this.$scope.server = localStorage.getItem("serverUrl");
            this.$scope.value = localStorage.getItem("username");
            if (this.$scope.server) {
                $(".forgot label[for='server']").addClass("active");
            }
            this.$scope.activateLabels = () => {
                if (this.$scope.value) {
                    setTimeout(() => $(".forgot label[for]").addClass("active"), 0);
                }
            };
            this.$scope.activateLabels();
            this.$scope.back = () => HistoryController.back();
            this.$scope.forgot = (what: string) => {
                ApiController.instance.setServerUrl(this.$scope.server);
                ApiController.instance.forgotCredentials.request(new ForgotCredentialsRequest(this.$scope.type, this.$scope.value, what as any), res => {
                    if (res.success) {
                        localStorage.setItem("serverUrl", this.$scope.server);
                        LoginController.readStorage();
                        HistoryController.load("/");
                        Materialize.toast("Check your email/phone for your " + (what == "username" ? "username" : "password reset link") + ".", 4000);
                    } else {
                        Materialize.toast("Error retrieving account information", 4000);
                    }
                });
            };
        }
    }
}
