/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;

    export class RegisterController extends AbstractPage {
        protected init(): void {
            $(document).ready(function(){
                $('select').material_select();
            });
            this.$scope.back = () => HistoryController.load("/");

        }
    }
}
