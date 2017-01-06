/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="profile.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import LoginRequest = org.usd232.robotics.management.apis.LoginRequest;
    import User = org.usd232.robotics.management.apis.User;

    export class UsersController extends AbstractPage {
        protected init(): void {
            this.$scope.users = [];
            this.$scope.openProfile = (id: number) => ProfileController.show(id);
            this.$scope.updateVerification = (user: User) => {
                if ( user.verified ) {
                    ApiController.instance.verify.request(user.id, res => {
                        if ( !res.success ) {
                            Materialize.toast("Error verifying user", 4000);
                            this.$scope.$apply(() => user.verified = false);
                        }
                    });
                } else {
                    ApiController.instance.unverify.request(user.id, res => {
                        if ( !res.success ) {
                            Materialize.toast("Error unverifying user", 4000);
                            this.$scope.$apply(() => user.verified = true);
                        }
                    });
                }
            };
        }

        protected open(): void {
            ApiController.instance.users.request(users => this.$scope.$apply(() => {
                this.$scope.users = users;
            }));
        }
    }
}
