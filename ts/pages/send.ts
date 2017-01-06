/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import NotificationRequest = org.usd232.robotics.management.apis.NotificationRequest;
    import HistoryController = org.usd232.robotics.management.HistoryController;

    export class MessageController extends AbstractPage {
        protected init(): void {
            $("#send-notification-editor").on("materialnote.change", (we: any, contents: string) => {
                this.$scope.content = contents;
            });
            this.$scope.send = () => {
                ApiController.instance.notify.request(new NotificationRequest(this.$scope.content, this.$scope.target), res => {
                    if ( res.success ) {
                        Materialize.toast("Message sent!", 4000);
                        HistoryController.back();
                    } else {
                        Materialize.toast("Error sending message", 4000);
                    }
                });
            };
        }
    }
}
