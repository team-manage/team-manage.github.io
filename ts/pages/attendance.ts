/// <reference path="../page.ts" />
/// <reference path="../apis.ts" />
/// <reference path="profile.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import HistoryController = org.usd232.robotics.management.HistoryController;
    import EventAttendanceRecord = org.usd232.robotics.management.apis.EventAttendanceRecord;
    import ExcuseRequest = org.usd232.robotics.management.apis.ExcuseRequest;

    export class AttendanceController extends AbstractPage {
        private static newId: number;

        public static show(id: number): void {
            AttendanceController.newId = id;
            HistoryController.load("/admin/attendance");
        }

        protected init(): void {
            this.$scope.showUser = id => ProfileController.show(id);
            this.$scope.update = (user: EventAttendanceRecord) => {
                ApiController.instance.excuse.request(new ExcuseRequest(user.id, this.$scope.attendance.id, user.excused), res => {
                    if (!res.success) {
                        Materialize.toast("Unable to excuse absence", 4000);
                    }
                });
            };
        }

        protected open(): void {
            ApiController.instance.eventAttendance.request(AttendanceController.newId, res => this.$scope.$apply(() => {
                this.$scope.attendance = res;
            }));
        }
    }
}
