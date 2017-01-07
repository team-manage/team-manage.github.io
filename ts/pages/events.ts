/// <reference path="../page.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />
/// <reference path="event.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import Event = org.usd232.robotics.management.apis.Event;

    export class EventsController extends AbstractPage {
        protected init(): void {
            this.$scope.LoginController = LoginController;
            this.$scope.events = [];
            this.$scope.view = (event: Event) => {
                if ( LoginController.user.permissions.events.view ) {
                    EventController.show(event.id)
                }
            };
            this.$scope.add = () => {
                ApiController.instance.addEvent.request(res => {
                    if ( res.success ) {
                        EventController.show(res.id)
                    } else {
                        Materialize.toast("Unable to create event", 4000);
                    }
                });
            };
        }

        protected open(): void {
            ApiController.instance.events.request(events => this.$scope.$apply(() => {
                this.$scope.events = events;
            }));
        }
    }
}
