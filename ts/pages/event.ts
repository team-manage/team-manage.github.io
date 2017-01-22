/// <reference path="../page.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import HistoryController = org.usd232.robotics.management.HistoryController;
    import Event = org.usd232.robotics.management.apis.Event;
    import EventTime = org.usd232.robotics.management.apis.EventTime;

    export class EventController extends AbstractPage {
        private static scope: any;
        private static newId: number;

        public static show(id: number): void {
            EventController.newId = id;
            HistoryController.load("/admin/event");
        }

        protected init(): void {
            let monthMap: string[] = [
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"
            ];
            let hourMap: number[] = [
                12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
            ];
            this.$scope.save = () => {
                ApiController.instance.editEvent.request(new Event(this.$scope.event.id, this.$scope.event.type, this.$scope.event.name,
                monthMap[this.$scope.event.date.getMonth()] + " " + this.$scope.event.date.getDate() + ", " + this.$scope.event.date.getFullYear(),
                new EventTime(this.$scope.event.time.allDay,
                hourMap[this.$scope.event.time.start.getHours()] + ":" + this.$scope.event.time.start.getMinutes() + ":00 " + (this.$scope.event.time.start.getHours() < 12 ? "AM" : "PM"),
                hourMap[this.$scope.event.time.end.getHours()] + ":" + this.$scope.event.time.end.getMinutes() + ":00 " + (this.$scope.event.time.end.getHours() < 12 ? "AM" : "PM")),
                this.$scope.event.signup), res => {
                    if (res.success) {
                        Materialize.toast("Event saved!", 4000);
                    } else {
                        Materialize.toast("Error saving event", 4000);
                    }
                });
            };
            this.$scope.viewAttendance = () => {

            };
            this.$scope.delete = () => {
                ApiController.instance.removeEvent.request(this.$scope.event.id, res => {
                    if (res.success) {
                        Materialize.toast("Event deleted!", 4000);
                        HistoryController.back();
                    } else {
                        Materialize.toast("Error deleting event", 4000);
                    }
                });
            };
            this.$scope.day = () => {
                return this.$scope.event == null || this.$scope.event.date == null ? null : "" + this.$scope.event.date.getDay();
            };
            this.$scope.month = val => {
                if (!this.$scope.event || !this.$scope.event.date) {
                    return null;
                }
                if (val) {
                    this.$scope.event.date.setMonth(parseInt(val));
                }
                return "" + this.$scope.event.date.getMonth();
            };
            this.$scope.date = val => {
                if (!this.$scope.event || !this.$scope.event.date) {
                    return null;
                }
                if (val) {
                    this.$scope.event.date.setDate(parseInt(val));
                }
                return this.$scope.event.date.getDate();
            };
            this.$scope.year = val => {
                if (!this.$scope.event || !this.$scope.event.date) {
                    return null;
                }
                if (val) {
                    this.$scope.event.date.setFullYear(parseInt(val));
                }
                return this.$scope.event.date.getFullYear();
            };
            var startOffset: number = 0;
            this.$scope.startHour = val => {
                if (!this.$scope.event || !this.$scope.event.time || !this.$scope.event.time.start) {
                    return null;
                }
                if (val) {
                    val = parseInt(val);
                    this.$scope.event.time.start.setHours((val == 0 ? 12 : val) + startOffset);
                }
                return hourMap[this.$scope.event.time.start.getHours()];
            };
            this.$scope.startMinute = val => {
                if (!this.$scope.event || !this.$scope.event.time || !this.$scope.event.time.start) {
                    return null;
                }
                if (val) {
                    this.$scope.event.time.start.setMinutes(parseInt(val));
                }
                return this.$scope.event.time.start.getMinutes();
            };
            this.$scope.startMeridiem = val => {
                if (!this.$scope.event || !this.$scope.event.time || !this.$scope.event.time.start) {
                    return null;
                }
                if (val) {
                    startOffset = parseInt(val);
                    this.$scope.startHour(this.$scope.startHour());
                }
                return "" + startOffset;
            };
            var endOffset: number = 0;
            this.$scope.endHour = val => {
                if (!this.$scope.event || !this.$scope.event.time || !this.$scope.event.time.end) {
                    return null;
                }
                if (val) {
                    val = parseInt(val);
                    this.$scope.event.time.end.setHours((val == 0 ? 12 : val) + endOffset);
                }
                return hourMap[this.$scope.event.time.end.getHours()];
            };
            this.$scope.endMinute = val => {
                if (!this.$scope.event || !this.$scope.event.time || !this.$scope.event.time.end) {
                    return null;
                }
                if (val) {
                    this.$scope.event.time.end.setMinutes(parseInt(val));
                }
                return this.$scope.event.time.end.getMinutes();
            };
            this.$scope.endMeridiem = val => {
                if (!this.$scope.event || !this.$scope.event.time || !this.$scope.event.time.end) {
                    return null;
                }
                if (val) {
                    endOffset = parseInt(val);
                    this.$scope.endHour(this.$scope.endHour());
                }
                return "" + endOffset;
            };
            this.$scope.changed = () => setTimeout(() => $("select").material_select(), 0);
        }

        protected open(): void {
            this.$scope.id = EventController.newId;
            ApiController.instance.event.request(this.$scope.id, event => this.$scope.$apply(() => {
                event.date = new Date(event.date) as any;
                let now: Date = new Date();
                event.time.start = event.time.start == null ? new Date(0, 0, 0, now.getHours(), now.getMinutes()) : new Date("1/1/0 " + event.time.start) as any;
                event.time.end = event.time.end == null ? new Date(0, 0, 0, now.getHours() + 1, now.getMinutes()) : new Date("1/1/0 " + event.time.end) as any;
                this.$scope.event = event;
                this.$scope.startMeridiem(event.time.start == null ? 0 : (event.time.start as any as Date).getHours() >= 12 ? 12 : 0);
                this.$scope.endMeridiem(event.time.end == null ? 0 : (event.time.end as any as Date).getHours() >= 12 ? 12 : 0);
                this.$scope.changed();
                setTimeout(() => $(".event-active").addClass("active"), 0);
            }));
        }
    }
}
