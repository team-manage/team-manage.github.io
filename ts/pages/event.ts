/// <reference path="../page.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import HistoryController = org.usd232.robotics.management.HistoryController;

    export class EventController extends AbstractPage {
        private static newId: number;

        public static show(id: number): void {
            EventController.newId = id;
            HistoryController.load("/admin/event");
        }

        protected init(): void {
            this.$scope.save = () => {

            };
            let months: string[] = [
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"
            ];
            let picker: any = new (window as any).MaterialDatePicker({
                "container": document.body
            });
            var editing: JQuery;
            picker.on("submit", val => {
                let date: Date = new Date(val);
                editing.val(months[date.getMonth()] + " " + date.getDate() + ", " + date.getFullYear());
            });
            $(".event-datepicker").on("focus", function() {
                picker.open();
                editing = this;
            });
        }

        protected open(): void {
            this.$scope.id = EventController.newId;
            ApiController.instance.event.request(this.$scope.id, event => this.$scope.$apply(() => {
                event.date = new Date(event.date) as any;
                event.time.start = event.time.start == null ? null : new Date(event.time.start) as any;
                event.time.end = event.time.end == null ? null : new Date(event.time.end) as any;
                this.$scope.event = event;
                setTimeout(() => $("select").material_select(), 0);
            }));
        }
    }
}
