/// <reference path="../typings/index.d.ts" />

namespace org.usd232.robotics.management.apis {
    export class LoginRequest {
        public username: string;
        public password: string;

        public constructor(username?: string, password?: string) {
            this.username = username;
            this.password = password;
        }
    }

    export enum ContactType {
        email,
        phone
    }

    export namespace notificiations {
        export class SignInNotifications {
            public manual: boolean;
            public auto: boolean;
        }

        export class MeetingNotifications {
            public missed: boolean;
            public reminders: boolean;
        }

        export class Notifications {
            public signIn: SignInNotifications;
            public team: boolean;
            public meetings: MeetingNotifications;
        }
    }

    import Notifications = notificiations.Notifications;

    export class UserContact {
        public type: ContactType;
        public address: string;
        public number: string;
        public provider: string;
        public notifications: Notifications;
    }

    export class UserProfile {
        public contact: UserContact[];
        public picture: string;
        public pin: number;
        public name: string;
    }

    export namespace permissions {
        export class KioskPermissions {
            public open: boolean;
        }

        export class MessagePermissions {
            public send: boolean;
        }

        export class EventEditPermissions {
            public type: boolean;
            public name: boolean;
            public datetime: boolean;
        }

        export class EventPermissions {
            public view: boolean;
            public add: boolean;
            public edit: EventEditPermissions;
            public remove: boolean;
        }

        export class AttendancePermissions {
            public view: boolean;
            public modify: boolean;
            public excuse: boolean;
        }

        export class UserPermissions {
            public view: boolean;
            public verify: boolean;
            public unverify: boolean;
        }

        export class DevicePermissions {
            public add: boolean;
            public update: boolean;
            public remove: boolean;
        }

        export class SettingsPermissions {
            public view: boolean;
            public edit: boolean;
        }

        export class SignInPermissions {
            public kiosk: boolean;
            public code: boolean;
            public auto: boolean;
        }

        export class Permissions {
            public kiosk: KioskPermissions;
            public message: MessagePermissions;
            public events: EventPermissions;
            public attendance: AttendancePermissions;
            public users: UserPermissions;
            public devices: DevicePermissions;
            public settings: SettingsPermissions;
            public signIn: SignInPermissions;
        }
    }

    import Permissions = permissions.Permissions;

    export class LoginResponse {
        public authentication: string;
        public permissions: Permissions;
        public profile: UserProfile;
    }

    export class EventSignup {
        public required: boolean;
        public deadline: string;
        public rsvp?: boolean;
    }

    export enum EventType {
        event,
        meeting
    }

    export class EventTime {
        public allDay: boolean;
        public start: string;
        public end: string;
    }

    export class Event {
        public id: number;
        public type: EventType;
        public name: string;
        public location: string;
        public date: string;
        public time?: EventTime;
        public signup: EventSignup;
        public attended?: boolean;

        public constructor(id?: number, type?: EventType, name?: string, date?: string, time?: EventTime, signup?: EventSignup) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.date = date;
            this.time = time;
            this.signup = signup;
        }
    }

    export enum DeviceRole {
        server,
        AP
    }

    export class Device {
        public hostname: string;
        public role: DeviceRole;
        public version: string;
    }

    export class Infrastructure {
        public latestVersion: string;
        public devices: Device[];
    }

    export enum NotificationTarget {
        subteam,
        team
    }

    export class NotificationRequest {
        public message: string;
        public target: NotificationTarget;

        public constructor(message?: string, target?: NotificationTarget) {
            this.message = message;
            this.target = target;
        }
    }

    export class StatusResponse {
        public success: boolean;
    }

    export class Message {
        public date: string;
        public time: string;
        public from: string;
        public message: string;
    }

    export enum SettingType {
        string,
        integer,
        number
    }

    export class Setting {
        public name: string;
        public type: SettingType;
        public value: any;
    }

    export class User {
        public id: number;
        public name: string;
        public picture?: string;
        public verified?: boolean;
        public unexcused: number;
        public late?: number;
    }

    export class RegisterRequest {
        public username: string;
        public fname: string;
        public lname: string;
        public password: string;
        public email: string;
        public email2: string;
        public phone: string;
        public provider: string;

        public constructor(username?: string, fname?: string, lname?: string, password?: string, email?: string, email2?: string, phone?: string, provider?: string) {
            this.username = username;
            this.fname = fname;
            this.lname = lname;
            this.password = password;
            this.email = email;
            this.email2 = email2;
            this.phone = phone;
            this.provider = provider;
        }
    }

    export class AddContactRequest {
        public type: ContactType;
        public address: string;
        public number: string;
        public provider: string;

        public constructor(type?: ContactType, value?: string, provider?: string) {
            this.type = type;
            if ( type == ContactType.phone ) {
                this.number = value;
                this.provider = provider;
            } else if ( type == ContactType.email ) {
                this.address = value;
            }
        }
    }

    export class EditContactRequest {
        public type: ContactType;
        public address: string;
        public number: string;
        public notifications: Notifications;

        public constructor(type?: ContactType, value?: string, notifications?: Notifications) {
            this.type = type;
            if ( type == ContactType.phone ) {
                this.number = value;
            } else if ( type == ContactType.email ) {
                this.address = value;
            }
            this.notifications = notifications;
        }
    }

    export class RemoveContactRequest {
        public type: ContactType;
        public address: string;
        public number: string;

        public constructor(type?: ContactType, value?: string) {
            this.type = type;
            if ( type == ContactType.phone ) {
                this.number = value;
            } else if ( type == ContactType.email ) {
                this.address = value;
            }
        }
    }

    export class RsvpRequest {
        public event: number;
        public rsvp?: boolean;

        public constructor(event?: number, rsvp?: boolean) {
            this.event = event;
            this.rsvp = rsvp;
        }
    }

    export class StatusIdResponse extends StatusResponse {
        public id: number;
    }

    export class ExcuseRequest {
        public user: number;
        public event: number;
        public excused: boolean;

        public constructor(user?: number, event?: number, excused?: boolean) {
            this.user = user;
            this.event = event;
            this.excused = excused;
        }
    }

    export class SetSettingRequest {
        public key: string;
        public value: string;

        public constructor(key?: string, value?: string) {
            this.key = key;
            this.value = value;
        }
    }

    class ApiBase<T> {
        private url: string;
        private ctrlr: ApiController;
        private lastResponse: T;
        private lastModified: number = 0;

        protected getLastResponse(content: any): T {
            return this.lastResponse;
        }

        protected getLastModified(content: any): number {
            return this.lastModified;
        }

        protected setLastResponse(content: any, lastResponse: T, lastModified: number) {
            this.lastResponse = lastResponse;
            this.lastModified = lastModified;
        }

        protected isMock(callback: (isMock: boolean) => void) {
            if ( this.ctrlr.isMock == null ) {
                this.ctrlr.isMockListeners.push(() => {
                    callback(this.ctrlr.isMock);
                });
            } else {
                callback(this.ctrlr.isMock);
            }
        }

        protected handleSuccess(data: any, xhr: JQueryXHR, content: any, callback: (res: T) => void, lateCallback: (res: T) => void): void {
            let lastModified: number = Date.parse(xhr.getResponseHeader("Last-Modified"));
            if ( lastModified > this.getLastModified(content) ) {
                if ( this.lastResponse ) {
                    lateCallback(data);
                } else {
                    callback(data);
                }
                this.setLastResponse(content, data, lastModified);
            }
        }

        protected sendRequest(url: string, content: any, method: string, callback: (res: T) => void, lateCallback: (res: T) => void): void {
            let lastResponse: T = this.getLastResponse(content);
            if ( lastResponse ) {
                setTimeout(() => callback(lastResponse), 0);
            }
            $.ajax(this.ctrlr.baseUrl + this.url + url, {
                "cache": false,
                "contentType": "application/json; charset=utf-8",
                "data": content,
                "dataType": "json",
                "error": (xhr: JQueryXHR, status: string, error: string) => {
                    console.error(error);
                    if ( !lastResponse ) {
                        callback(null);
                    }
                },
                "headers": {
                    "X-Session-Token": this.ctrlr.sessionToken
                },
                "ifModified": lastResponse != null,
                "jsonp": false,
                "method": method,
                "success": (data: any, status: string, xhr: JQueryXHR) => this.handleSuccess(data, xhr, content, callback, lateCallback)
            });
        }

        public constructor(url: string, ctrlr: ApiController) {
            this.url = url;
            this.ctrlr = ctrlr;
        }
    }

    export class ParameterizedApi<T, P> extends ApiBase<T> {
        private lastResponses: { [json: string]: T } = {};
        private lastModifieds: { [json: string]: number } = {};

        protected getLastResponse(content: any): T {
            return this.lastResponses[content];
        }

        protected getLastModified(content: any): number {
            return this.lastModifieds[content] || 0;
        }

        protected setLastResponse(content: any, lastResponse: T, lastModified: number) {
            this.lastResponses[content] = lastResponse;
            this.lastModifieds[content] = lastModified;
        }

        public request(param: P, callback: (res: T) => void, lateCallback: (res: T) => void = callback): void {
            this.isMock(isMock =>
                this.sendRequest(".json", JSON.stringify(param), isMock ? "GET" : "POST", callback, lateCallback)
            );
        }
    }

    export class LoginApi extends ParameterizedApi<LoginResponse, LoginRequest> {
        public constructor(url: string, ctrlr: ApiController) {
            super(url, ctrlr);
            let base = this.handleSuccess;
            this.handleSuccess = (data: any, xhr: JQueryXHR, content: any, callback: (res: LoginResponse) => void, lateCallback: (res: LoginResponse) => void) => {
                ctrlr.sessionToken = xhr.getResponseHeader("X-Session-Token");
                base.apply(this, [
                    data, xhr, content, callback, lateCallback
                ]);
            };
        }
    }

    export class GeneralApi<T> extends ApiBase<T> {
        public request(callback: (res: T) => void, lateCallback: (res: T) => void = callback): void {
            this.sendRequest(".json", null, "GET", callback, lateCallback);
        }
    }

    export class CollectionApi<T, TIndex> extends ApiBase<T> {
        private lastResponses: { [json: string]: T } = {};
        private lastModifieds: { [json: string]: number } = {};

        protected getLastResponse(content: any): T {
            return this.lastResponses[content];
        }

        protected getLastModified(content: any): number {
            return this.lastModifieds[content] || 0;
        }

        protected setLastResponse(content: any, lastResponse: T, lastModified: number) {
            this.lastResponses[content] = lastResponse;
            this.lastModifieds[content] = lastModified;
        }

        public request(id: TIndex, callback: (res: T) => void, lateCallback: (res: T) => void = callback): void {
            this.sendRequest("/" + id + ".json", "" + id, "GET", callback, lateCallback);
        }
    }

    export class ApiController {
        public static instance: ApiController = new ApiController();

        public baseUrl: string;
        public sessionToken: string;
        public isMock?: boolean;
        public isMockListeners: (() => void)[] = [];

        public login = new LoginApi("/authenticate", this);
        public events = new GeneralApi<Event[]>("/events", this);
        public infrastructure = new GeneralApi<Infrastructure>("/infrastructure", this);
        public notify = new ParameterizedApi<StatusResponse, NotificationRequest>("/notify", this);
        public recent = new GeneralApi<Message[]>("/recent", this);
        public settings = new GeneralApi<Setting[]>("/settings", this);
        public users = new GeneralApi<User[]>("/users", this);
        public attendance = new CollectionApi<Event[], number>("/attendance", this);
        public event = new CollectionApi<Event, number>("/event", this);
        public kiosk = new CollectionApi<User, number>("/kiosk", this);
        public kioskSignIn = new ParameterizedApi<StatusResponse, number>("/kiosk/signIn", this);
        public update = new CollectionApi<StatusResponse, string>("/update", this);
        public register = new ParameterizedApi<StatusResponse, RegisterRequest>("/register", this);
        public addContact = new ParameterizedApi<StatusResponse, AddContactRequest>("/contact/add", this);
        public editContact = new ParameterizedApi<StatusResponse, EditContactRequest>("/contact/edit", this);
        public removeContact = new ParameterizedApi<StatusResponse, RemoveContactRequest>("/contact/remove", this);
        public setPicture = new ParameterizedApi<StatusResponse, string>("/setPicture", this);
        public rsvp = new ParameterizedApi<StatusResponse, RsvpRequest>("/event/rsvp", this);
        public changePin = new ParameterizedApi<StatusResponse, number>("/changePin", this);
        public addEvent = new GeneralApi<StatusIdResponse>("/event/add", this);
        public editEvent = new ParameterizedApi<StatusResponse, Event>("/event/edit", this);
        public removeEvent = new ParameterizedApi<StatusResponse, number>("/event/remove", this);
        public excuse = new ParameterizedApi<StatusResponse, ExcuseRequest>("/attendance/excuse", this);
        public verify = new ParameterizedApi<StatusResponse, number>("/verify", this);
        public setSetting = new ParameterizedApi<StatusResponse, SetSettingRequest>("/setSetting", this);

        public setServerUrl(url: string) {
            if ( url.match(/^!.*$/) ) {
                this.baseUrl = "https://" + url.substr(1) + ".herokuapp.com";
            } else if ( url.match(/^@[A-Za-z0-9+/]{6}$/) ) {
                let ip: string = atob(url.substr(1));
                this.baseUrl = "http://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3);
            } else if ( url.match(/^#[A-Za-z0-9+/]{6}$/) ) {
                let ip: string = atob(url.substr(1));
                this.baseUrl = "https://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3);
            } else if ( url.match(/^@[A-Za-z0-9+/]{8}$/) ) {
                let ip: string = atob(url.substr(1));
                this.baseUrl = "http://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3) + ":" + (ip.charCodeAt(4) * 256 + ip.charCodeAt(5));
            } else if ( url.match(/^#[A-Za-z0-9+/]{8}$/) ) {
                let ip: string = atob(url.substr(1));
                this.baseUrl = "https://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3) + ":" + (ip.charCodeAt(4) * 256 + ip.charCodeAt(5));
            } else {
                this.baseUrl = url;
            }
            this.isMock = null;
            $.ajax(this.baseUrl + "/isMock.json", {
                "cache": false,
                "contentType": "application/json; charset=utf-8",
                "dataType": "json",
                "error": (xhr: JQueryXHR, status: string, error: string) => {
                    console.error(error);
                },
                "jsonp": false,
                "method": "GET",
                "success": data => {
                    this.isMock = data;
                    for ( var i: number = 0; i < this.isMockListeners.length; ++i ) {
                        this.isMockListeners[i]();
                    }
                    this.isMockListeners = [];
                }
            });
        }
    }
}
