/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />

namespace org.usd232.robotics.management.pages {
    import ApiController = org.usd232.robotics.management.apis.ApiController;
    import UserContact = org.usd232.robotics.management.apis.UserContact;
    import Notifications = org.usd232.robotics.management.apis.notificiations.Notifications;
    import SignInNotifications = org.usd232.robotics.management.apis.notificiations.SignInNotifications;
    import MeetingNotifications = org.usd232.robotics.management.apis.notificiations.MeetingNotifications;
    import AddContactRequest = org.usd232.robotics.management.apis.AddContactRequest;
    import EditContactRequest = org.usd232.robotics.management.apis.EditContactRequest;
    import RemoveContactRequest = org.usd232.robotics.management.apis.RemoveContactRequest;

    export class ProfileController extends AbstractPage {
        protected init(): void {
            this.$scope.LoginController = LoginController;
            this.$scope.addEmail = () => {
                let contact: UserContact = new UserContact("email", this.$scope.newEmail, null, null, new Notifications(new SignInNotifications(this.$scope.newNotificationsManualSignIn, this.$scope.newNotificationsAutoSignIn), this.$scope.newNotificationsTeam, new MeetingNotifications(this.$scope.newNotificationsMissedMeeting, this.$scope.newNotificationsMeetingReminders)));
                ApiController.instance.addContact.request(new AddContactRequest(contact), res => {
                    if ( res.success ) {
                        this.$scope.$apply(() => {
                            LoginController.user.profile.contact.push(contact);
                            this.$scope.newEmail = "";
                        });
                    } else {
                        Materialize.toast("An error occurred while adding email", 4000);
                    }
                });
            };
            this.$scope.addPhone = () => {
                let contact: UserContact = new UserContact("phone", null, this.$scope.newPhone, this.$scope.newPhoneProvider, new Notifications(new SignInNotifications(this.$scope.newNotificationsManualSignIn, this.$scope.newNotificationsAutoSignIn), this.$scope.newNotificationsTeam, new MeetingNotifications(this.$scope.newNotificationsMissedMeeting, this.$scope.newNotificationsMeetingReminders)));
                ApiController.instance.addContact.request(new AddContactRequest(contact), res => {
                    if ( res.success ) {
                        this.$scope.$apply(() => {
                            LoginController.user.profile.contact.push(contact);
                            this.$scope.newPhone = "";
                            this.$scope.newPhoneProvider = "";
                        });
                    } else {
                        Materialize.toast("An error occurred while adding phone number", 4000);
                    }
                });
            };
            this.$scope.changeContact = ($index: number) => {
                let contact: UserContact = LoginController.user.profile.contact[$index];
                ApiController.instance.editContact.request(new EditContactRequest(contact), res => {
                    if ( !res.success ) {
                        Materialize.toast("Unable to update notification settings", 4000);
                    }
                });
            };
            this.$scope.deleteContact = ($index: number) => {
                let contact: UserContact = LoginController.user.profile.contact[$index];
                ApiController.instance.removeContact.request(new RemoveContactRequest(contact), res => {
                    if ( res.success ) {
                        this.$scope.$apply(() => LoginController.user.profile.contact.splice($index, 1));
                    } else {
                        Materialize.toast("An error occurred while removing contact", 4000);
                    }
                });
            };
        }
    }
}

