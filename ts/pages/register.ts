/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="../providers.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;
    let Providers = (org.usd232.robotics.management as any).Providers; // VS doesn't like Liquid

    export class RegisterController extends AbstractPage {
        protected init(): void {
            this.$scope.back = () => HistoryController.back();
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
