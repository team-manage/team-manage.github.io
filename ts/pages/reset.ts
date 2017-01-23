/// <reference path="../page.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import HistoryController = org.usd232.robotics.management.HistoryController;
    import ResetPasswordRequest = org.usd232.robotics.management.apis.ResetPasswordRequest;

    export class ResetController extends AbstractPage {
        public static hash: string;

        protected init(): void {
            this.$scope.reset = () => {
                if (this.$scope.password != this.$scope.passconf) {
                    Materialize.toast("Passwords do not match", 4000);
                    return;
                }
                let parts: string[] = ResetController.hash.split("/", 2);
                let server: string = atob(parts[0]);
                ApiController.instance.setServerUrl(server);
                ApiController.instance.resetPassword.request(new ResetPasswordRequest(parts[1], this.$scope.password), res => {
                    if (res.success) {
                        localStorage.setItem("serverUrl", server);
                        HistoryController.load("/");
                        Materialize.toast("Password set successfully!", 4000);
                    } else {
                        Materialize.toast("Error while setting password", 4000);
                    }
                });
            };
        }
    }
}
