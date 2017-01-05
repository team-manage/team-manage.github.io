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
    import RsvpRequest = org.usd232.robotics.management.apis.RsvpRequest;

    export class ProfileController extends AbstractPage {
        private media: MediaStream;

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
            this.$scope.uploadImage = () => {
                let file: File = ($("input.profile-upload-image")[0] as HTMLInputElement).files[0];
                let reader: FileReader = new FileReader();
                reader.onload = () => {
                    ApiController.instance.setPicture.request(reader.result, res => {
                        if ( res.success ) {
                            this.$scope.$apply(() => LoginController.user.profile.picture = reader.result);
                        } else {
                            Materialize.toast("An error occurred while setting picture", 4000);
                        }
                    });
                };
                reader.readAsDataURL(file);
            };
            this.$scope.startWebcam = () => {
                (navigator.mediaDevices.getUserMedia({
                    "video": {
                        "facingMode": "user"
                    }
                }).then(media => {
                    this.media = media;
                    let video: HTMLVideoElement = $("video.profile-upload-image")[0] as HTMLVideoElement;
                    video.srcObject = media;
                    video.play();
                    ($(".profile-take-picture") as any).modal("open");
                }) as any).catch(ex => {
                    Materialize.toast("Unable to take picture", 4000);
                });
            };
            this.$scope.cleanupWebcam = () => {
                let video: HTMLVideoElement = $("video.profile-upload-image")[0] as HTMLVideoElement;
                video.pause();
                this.media.stop();
            };
            this.$scope.takePicture = () => {
                let video: HTMLVideoElement = $("video.profile-upload-image")[0] as HTMLVideoElement;
                let canvas: HTMLCanvasElement = $("canvas.profile-upload-image")[0] as HTMLCanvasElement;
                canvas.width = video.videoWidth;
                canvas.height = video.videoHeight;
                let context = canvas.getContext("2d");
                context.clearRect(0, 0, canvas.width, canvas.height);
                context.drawImage(video, 0, 0, canvas.width, canvas.height);
                let url: string = canvas.toDataURL();
                ApiController.instance.setPicture.request(url, res => {
                    if ( res.success ) {
                        this.$scope.$apply(() => LoginController.user.profile.picture = url);
                    } else {
                        Materialize.toast("An error occurred while setting picture", 4000);
                    }
                    this.$scope.cleanupWebcam();
                });
            };
            this.$scope.rsvp = ($index: number, value: boolean) => {
                ApiController.instance.rsvp.request(new RsvpRequest(this.$scope.events[$index].id, value), res => {
                    if ( !res.success ) {
                        Materialize.toast("An error occurred while rsvping", 4000);
                    }
                });
            };
            this.$scope.changePin = () => {
                let pin: number = parseInt("" + this.$scope.newPin);
                ApiController.instance.changePin.request(pin, res => {
                    if ( res.success ) {
                        this.$scope.$apply(() => {
                            LoginController.user.profile.pin = pin;
                            this.$scope.newPin = "";
                        });
                    } else {
                        Materialize.toast("That pin has already been taken", 4000);
                    }
                });
            };
        }

        protected open(): void {
            ApiController.instance.events.request(events => this.$scope.$apply(() => {
                this.$scope.events = events;
                setTimeout(() => $(".indeterminate").prop("indeterminate", true));
            }));
        }
    }
}

