var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../typings/index.d.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var ng;
                (function (ng) {
                    var AngularController = (function () {
                        function AngularController() {
                        }
                        AngularController.registerController = function (name, callback) {
                            AngularController.controllers[name] = callback;
                        };
                        AngularController.init = function () {
                            AngularController.app = window.angular.module("team-manage", [
                                "ngSanitize"
                            ]);
                            Object.getOwnPropertyNames(AngularController.controllers).forEach(function (name) {
                                return AngularController.app.controller(name, AngularController.controllers[name]);
                            });
                        };
                        return AngularController;
                    }());
                    AngularController.controllers = {};
                    ng.AngularController = AngularController;
                })(ng = management.ng || (management.ng = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="angular.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var AngularController = org.usd232.robotics.management.ng.AngularController;
                var AbstractPage = (function () {
                    function AbstractPage(name) {
                        var _this = this;
                        AngularController.registerController("page-" + name, function ($scope, $http, $sce) {
                            _this.$scope = $scope;
                            _this.$http = $http;
                            _this.$sce = $sce;
                            _this.init();
                        });
                        management.HistoryController.getPageController().onLoad(name, function () { return _this.open(); });
                    }
                    AbstractPage.prototype.open = function () {
                    };
                    return AbstractPage;
                }());
                management.AbstractPage = AbstractPage;
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="angular.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var AngularController = org.usd232.robotics.management.ng.AngularController;
                var NavBar = (function () {
                    function NavBar() {
                        var _this = this;
                        AngularController.registerController("header", function ($scope) {
                            _this.$scope = $scope;
                            $scope.back = function () { return HistoryController.back(); };
                            $scope.go = function (url) { return HistoryController.load(url); };
                            $scope.openProfile = function () { return management.pages.ProfileController.show(); }; // Cyclic dependency
                            $scope.LoginController = management.pages.LoginController; // Cyclic dependency
                        });
                    }
                    NavBar.prototype.loadPage = function (name) {
                        var _this = this;
                        var element = document.getElementById("page-" + name);
                        if (element) {
                            document.title = $("input[name='pageTitle']", element).val();
                        }
                        setTimeout(function () {
                            return _this.$scope.$apply(function () {
                                return _this.$scope.title = element ? $("input[name='navbarTitle']", element).val() : null;
                            });
                        }, 0);
                        setTimeout(function () {
                            return $(".loading").addClass("done");
                        }, 0);
                    };
                    return NavBar;
                }());
                management.NavBar = NavBar;
                var HistoryController = (function () {
                    function HistoryController() {
                    }
                    HistoryController.setPageController = function (ctrlr) {
                        HistoryController.page = ctrlr;
                    };
                    HistoryController.getPageController = function () {
                        return HistoryController.page;
                    };
                    HistoryController.load = function (url) {
                        var tmp = $("input[name='pageUrl'][value='" + url + "']").parent("div");
                        var page = tmp.length > 0 ? tmp[0].id.substr(5) : "404";
                        HistoryController.page.loadPage(page);
                        HistoryController.history.push(url);
                        history.replaceState({}, document.title, url);
                    };
                    HistoryController.back = function () {
                        HistoryController.history.pop();
                        this.load(HistoryController.history.pop());
                    };
                    return HistoryController;
                }());
                HistoryController.history = [];
                management.HistoryController = HistoryController;
                var PageController = (function () {
                    function PageController(nav) {
                        var _this = this;
                        this.listeners = {};
                        this.nav = nav;
                        AngularController.registerController("pages", function ($scope) {
                            _this.$scope = $scope;
                            HistoryController.load("/");
                        });
                    }
                    PageController.prototype.loadPage = function (name) {
                        this.$scope.name = name;
                        this.nav.loadPage(name);
                        if (this.listeners[name]) {
                            this.listeners[name]();
                        }
                    };
                    PageController.prototype.onLoad = function (name, callback) {
                        this.listeners[name] = callback;
                    };
                    return PageController;
                }());
                management.PageController = PageController;
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../typings/index.d.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var apis;
                (function (apis) {
                    var LoginRequest = (function () {
                        function LoginRequest(username, password) {
                            this.username = username;
                            this.password = password;
                        }
                        return LoginRequest;
                    }());
                    apis.LoginRequest = LoginRequest;
                    var notificiations;
                    (function (notificiations) {
                        var SignInNotifications = (function () {
                            function SignInNotifications(manual, auto) {
                                this.manual = manual;
                                this.auto = auto;
                            }
                            return SignInNotifications;
                        }());
                        notificiations.SignInNotifications = SignInNotifications;
                        var MeetingNotifications = (function () {
                            function MeetingNotifications(missed, reminders) {
                                this.missed = missed;
                                this.reminders = reminders;
                            }
                            return MeetingNotifications;
                        }());
                        notificiations.MeetingNotifications = MeetingNotifications;
                        var Notifications = (function () {
                            function Notifications(signIn, team, meetings) {
                                this.signIn = signIn;
                                this.team = team;
                                this.meetings = meetings;
                            }
                            return Notifications;
                        }());
                        notificiations.Notifications = Notifications;
                    })(notificiations = apis.notificiations || (apis.notificiations = {}));
                    var UserContact = (function () {
                        function UserContact(type, address, number, provider, notifications) {
                            this.type = type;
                            this.address = address;
                            this.number = number;
                            this.provider = provider;
                            this.notifications = notifications;
                        }
                        return UserContact;
                    }());
                    apis.UserContact = UserContact;
                    var UserProfile = (function () {
                        function UserProfile() {
                        }
                        return UserProfile;
                    }());
                    apis.UserProfile = UserProfile;
                    var permissions;
                    (function (permissions) {
                        var KioskPermissions = (function () {
                            function KioskPermissions() {
                            }
                            return KioskPermissions;
                        }());
                        permissions.KioskPermissions = KioskPermissions;
                        var MessagePermissions = (function () {
                            function MessagePermissions() {
                            }
                            return MessagePermissions;
                        }());
                        permissions.MessagePermissions = MessagePermissions;
                        var EventEditPermissions = (function () {
                            function EventEditPermissions() {
                            }
                            return EventEditPermissions;
                        }());
                        permissions.EventEditPermissions = EventEditPermissions;
                        var EventPermissions = (function () {
                            function EventPermissions() {
                            }
                            return EventPermissions;
                        }());
                        permissions.EventPermissions = EventPermissions;
                        var AttendancePermissions = (function () {
                            function AttendancePermissions() {
                            }
                            return AttendancePermissions;
                        }());
                        permissions.AttendancePermissions = AttendancePermissions;
                        var UserPermissions = (function () {
                            function UserPermissions() {
                            }
                            return UserPermissions;
                        }());
                        permissions.UserPermissions = UserPermissions;
                        var DevicePermissions = (function () {
                            function DevicePermissions() {
                            }
                            return DevicePermissions;
                        }());
                        permissions.DevicePermissions = DevicePermissions;
                        var SettingsPermissions = (function () {
                            function SettingsPermissions() {
                            }
                            return SettingsPermissions;
                        }());
                        permissions.SettingsPermissions = SettingsPermissions;
                        var SignInPermissions = (function () {
                            function SignInPermissions() {
                            }
                            return SignInPermissions;
                        }());
                        permissions.SignInPermissions = SignInPermissions;
                        var Permissions = (function () {
                            function Permissions() {
                            }
                            return Permissions;
                        }());
                        permissions.Permissions = Permissions;
                    })(permissions = apis.permissions || (apis.permissions = {}));
                    var LoginResponse = (function () {
                        function LoginResponse() {
                        }
                        return LoginResponse;
                    }());
                    apis.LoginResponse = LoginResponse;
                    var EventSignup = (function () {
                        function EventSignup() {
                        }
                        return EventSignup;
                    }());
                    apis.EventSignup = EventSignup;
                    var EventType;
                    (function (EventType) {
                        EventType[EventType["event"] = 0] = "event";
                        EventType[EventType["meeting"] = 1] = "meeting";
                    })(EventType = apis.EventType || (apis.EventType = {}));
                    var EventTime = (function () {
                        function EventTime() {
                        }
                        return EventTime;
                    }());
                    apis.EventTime = EventTime;
                    var Event = (function () {
                        function Event(id, type, name, date, time, signup) {
                            this.id = id;
                            this.type = type;
                            this.name = name;
                            this.date = date;
                            this.time = time;
                            this.signup = signup;
                        }
                        return Event;
                    }());
                    apis.Event = Event;
                    var DeviceRole;
                    (function (DeviceRole) {
                        DeviceRole[DeviceRole["server"] = 0] = "server";
                        DeviceRole[DeviceRole["AP"] = 1] = "AP";
                    })(DeviceRole = apis.DeviceRole || (apis.DeviceRole = {}));
                    var Device = (function () {
                        function Device() {
                        }
                        return Device;
                    }());
                    apis.Device = Device;
                    var Infrastructure = (function () {
                        function Infrastructure() {
                        }
                        return Infrastructure;
                    }());
                    apis.Infrastructure = Infrastructure;
                    var NotificationTarget;
                    (function (NotificationTarget) {
                        NotificationTarget[NotificationTarget["subteam"] = 0] = "subteam";
                        NotificationTarget[NotificationTarget["team"] = 1] = "team";
                    })(NotificationTarget = apis.NotificationTarget || (apis.NotificationTarget = {}));
                    var NotificationRequest = (function () {
                        function NotificationRequest(message, target) {
                            this.message = message;
                            this.target = target;
                        }
                        return NotificationRequest;
                    }());
                    apis.NotificationRequest = NotificationRequest;
                    var StatusResponse = (function () {
                        function StatusResponse() {
                        }
                        return StatusResponse;
                    }());
                    apis.StatusResponse = StatusResponse;
                    var Message = (function () {
                        function Message() {
                        }
                        return Message;
                    }());
                    apis.Message = Message;
                    var SettingType;
                    (function (SettingType) {
                        SettingType[SettingType["string"] = 0] = "string";
                        SettingType[SettingType["integer"] = 1] = "integer";
                        SettingType[SettingType["number"] = 2] = "number";
                    })(SettingType = apis.SettingType || (apis.SettingType = {}));
                    var Setting = (function () {
                        function Setting() {
                        }
                        return Setting;
                    }());
                    apis.Setting = Setting;
                    var User = (function () {
                        function User() {
                        }
                        return User;
                    }());
                    apis.User = User;
                    var RegisterRequest = (function () {
                        function RegisterRequest(username, fname, lname, password, email, email2, phone, provider) {
                            this.username = username;
                            this.fname = fname;
                            this.lname = lname;
                            this.password = password;
                            this.email = email;
                            this.email2 = email2;
                            this.phone = phone;
                            this.provider = provider;
                        }
                        return RegisterRequest;
                    }());
                    apis.RegisterRequest = RegisterRequest;
                    var AddContactRequest = (function () {
                        function AddContactRequest(contact) {
                            this.type = contact.type;
                            this.address = contact.address;
                            this.number = contact.number;
                            this.provider = contact.provider;
                        }
                        return AddContactRequest;
                    }());
                    apis.AddContactRequest = AddContactRequest;
                    var EditContactRequest = (function () {
                        function EditContactRequest(contact) {
                            this.type = contact.type;
                            this.address = contact.address;
                            this.number = contact.number;
                            this.notifications = contact.notifications;
                        }
                        return EditContactRequest;
                    }());
                    apis.EditContactRequest = EditContactRequest;
                    var RemoveContactRequest = (function () {
                        function RemoveContactRequest(contact) {
                            this.type = contact.type;
                            this.address = contact.address;
                            this.number = contact.number;
                        }
                        return RemoveContactRequest;
                    }());
                    apis.RemoveContactRequest = RemoveContactRequest;
                    var RsvpRequest = (function () {
                        function RsvpRequest(event, rsvp) {
                            this.event = event;
                            this.rsvp = rsvp;
                        }
                        return RsvpRequest;
                    }());
                    apis.RsvpRequest = RsvpRequest;
                    var StatusIdResponse = (function (_super) {
                        __extends(StatusIdResponse, _super);
                        function StatusIdResponse() {
                            return _super.apply(this, arguments) || this;
                        }
                        return StatusIdResponse;
                    }(StatusResponse));
                    apis.StatusIdResponse = StatusIdResponse;
                    var ExcuseRequest = (function () {
                        function ExcuseRequest(user, event, excused) {
                            this.user = user;
                            this.event = event;
                            this.excused = excused;
                        }
                        return ExcuseRequest;
                    }());
                    apis.ExcuseRequest = ExcuseRequest;
                    var SetSettingRequest = (function () {
                        function SetSettingRequest(key, value) {
                            this.key = key;
                            this.value = value;
                        }
                        return SetSettingRequest;
                    }());
                    apis.SetSettingRequest = SetSettingRequest;
                    var ApiBase = (function () {
                        function ApiBase(url, ctrlr) {
                            this.lastModified = 0;
                            this.url = url;
                            this.ctrlr = ctrlr;
                        }
                        ApiBase.prototype.getLastResponse = function (content) {
                            return this.lastResponse;
                        };
                        ApiBase.prototype.getLastModified = function (content) {
                            return this.lastModified;
                        };
                        ApiBase.prototype.setLastResponse = function (content, lastResponse, lastModified) {
                            this.lastResponse = lastResponse;
                            this.lastModified = lastModified;
                        };
                        ApiBase.prototype.isMock = function (callback) {
                            var _this = this;
                            if (this.ctrlr.isMock == null) {
                                this.ctrlr.isMockListeners.push(function () {
                                    callback(_this.ctrlr.isMock);
                                });
                            }
                            else {
                                callback(this.ctrlr.isMock);
                            }
                        };
                        ApiBase.prototype.handleSuccess = function (data, xhr, content, callback, lateCallback) {
                            var lastModified = Date.parse(xhr.getResponseHeader("Last-Modified"));
                            if (lastModified > this.getLastModified(content)) {
                                if (this.lastResponse) {
                                    lateCallback(data);
                                }
                                else {
                                    callback(data);
                                }
                                this.setLastResponse(content, data, lastModified);
                            }
                        };
                        ApiBase.prototype.sendRequest = function (url, content, method, callback, lateCallback) {
                            var _this = this;
                            var lastResponse = this.getLastResponse(content);
                            if (lastResponse) {
                                setTimeout(function () { return callback(lastResponse); }, 0);
                            }
                            $.ajax(this.ctrlr.baseUrl + this.url + url, {
                                "cache": false,
                                "contentType": "application/json; charset=utf-8",
                                "data": content,
                                "dataType": "json",
                                "error": function (xhr, status, error) {
                                    console.error(error);
                                    if (!lastResponse) {
                                        callback(null);
                                    }
                                },
                                "headers": {
                                    "X-Session-Token": this.ctrlr.sessionToken
                                },
                                "ifModified": lastResponse != null,
                                "jsonp": false,
                                "method": method,
                                "success": function (data, status, xhr) { return _this.handleSuccess(data, xhr, content, callback, lateCallback); }
                            });
                        };
                        return ApiBase;
                    }());
                    var ParameterizedApi = (function (_super) {
                        __extends(ParameterizedApi, _super);
                        function ParameterizedApi() {
                            var _this = _super.apply(this, arguments) || this;
                            _this.lastResponses = {};
                            _this.lastModifieds = {};
                            return _this;
                        }
                        ParameterizedApi.prototype.getLastResponse = function (content) {
                            return this.lastResponses[content];
                        };
                        ParameterizedApi.prototype.getLastModified = function (content) {
                            return this.lastModifieds[content] || 0;
                        };
                        ParameterizedApi.prototype.setLastResponse = function (content, lastResponse, lastModified) {
                            this.lastResponses[content] = lastResponse;
                            this.lastModifieds[content] = lastModified;
                        };
                        ParameterizedApi.prototype.request = function (param, callback, lateCallback) {
                            var _this = this;
                            if (lateCallback === void 0) { lateCallback = callback; }
                            this.isMock(function (isMock) {
                                return _this.sendRequest(".json", JSON.stringify(param), isMock ? "GET" : "POST", callback, lateCallback);
                            });
                        };
                        return ParameterizedApi;
                    }(ApiBase));
                    apis.ParameterizedApi = ParameterizedApi;
                    var BinaryParameterizedApi = (function (_super) {
                        __extends(BinaryParameterizedApi, _super);
                        function BinaryParameterizedApi() {
                            return _super.apply(this, arguments) || this;
                        }
                        BinaryParameterizedApi.prototype.request = function (param, callback, lateCallback) {
                            var _this = this;
                            if (lateCallback === void 0) { lateCallback = callback; }
                            this.isMock(function (isMock) {
                                if (isMock) {
                                    _this.sendRequest(".json", param.substr(0, 100), "GET", callback, lateCallback);
                                }
                                else {
                                    _this.sendRequest(".json", JSON.stringify(param), "POST", callback, lateCallback);
                                }
                            });
                        };
                        return BinaryParameterizedApi;
                    }(ParameterizedApi));
                    apis.BinaryParameterizedApi = BinaryParameterizedApi;
                    var LoginApi = (function (_super) {
                        __extends(LoginApi, _super);
                        function LoginApi(url, ctrlr) {
                            var _this = _super.call(this, url, ctrlr) || this;
                            var base = _this.handleSuccess;
                            _this.handleSuccess = function (data, xhr, content, callback, lateCallback) {
                                ctrlr.sessionToken = xhr.getResponseHeader("X-Session-Token");
                                base.apply(_this, [
                                    data, xhr, content, callback, lateCallback
                                ]);
                            };
                            return _this;
                        }
                        return LoginApi;
                    }(ParameterizedApi));
                    apis.LoginApi = LoginApi;
                    var GeneralApi = (function (_super) {
                        __extends(GeneralApi, _super);
                        function GeneralApi() {
                            return _super.apply(this, arguments) || this;
                        }
                        GeneralApi.prototype.request = function (callback, lateCallback) {
                            if (lateCallback === void 0) { lateCallback = callback; }
                            this.sendRequest(".json", null, "GET", callback, lateCallback);
                        };
                        return GeneralApi;
                    }(ApiBase));
                    apis.GeneralApi = GeneralApi;
                    var CollectionApi = (function (_super) {
                        __extends(CollectionApi, _super);
                        function CollectionApi() {
                            var _this = _super.apply(this, arguments) || this;
                            _this.lastResponses = {};
                            _this.lastModifieds = {};
                            return _this;
                        }
                        CollectionApi.prototype.getLastResponse = function (content) {
                            return this.lastResponses[content];
                        };
                        CollectionApi.prototype.getLastModified = function (content) {
                            return this.lastModifieds[content] || 0;
                        };
                        CollectionApi.prototype.setLastResponse = function (content, lastResponse, lastModified) {
                            this.lastResponses[content] = lastResponse;
                            this.lastModifieds[content] = lastModified;
                        };
                        CollectionApi.prototype.request = function (id, callback, lateCallback) {
                            if (lateCallback === void 0) { lateCallback = callback; }
                            this.sendRequest("/" + id + ".json", "" + id, "GET", callback, lateCallback);
                        };
                        return CollectionApi;
                    }(ApiBase));
                    apis.CollectionApi = CollectionApi;
                    var ApiController = (function () {
                        function ApiController() {
                            this.isMockListeners = [];
                            this.login = new LoginApi("/authenticate", this);
                            this.events = new GeneralApi("/events", this);
                            this.infrastructure = new GeneralApi("/infrastructure", this);
                            this.notify = new ParameterizedApi("/notify", this);
                            this.recent = new GeneralApi("/recent", this);
                            this.settings = new GeneralApi("/settings", this);
                            this.users = new GeneralApi("/users", this);
                            this.attendance = new CollectionApi("/attendance", this);
                            this.event = new CollectionApi("/event", this);
                            this.kiosk = new CollectionApi("/kiosk", this);
                            this.kioskSignIn = new ParameterizedApi("/kiosk/signIn", this);
                            this.update = new CollectionApi("/update", this);
                            this.register = new ParameterizedApi("/register", this);
                            this.addContact = new ParameterizedApi("/contact/add", this);
                            this.editContact = new ParameterizedApi("/contact/edit", this);
                            this.removeContact = new ParameterizedApi("/contact/remove", this);
                            this.setPicture = new BinaryParameterizedApi("/setPicture", this);
                            this.rsvp = new ParameterizedApi("/event/rsvp", this);
                            this.changePin = new ParameterizedApi("/changePin", this);
                            this.addEvent = new GeneralApi("/event/add", this);
                            this.editEvent = new ParameterizedApi("/event/edit", this);
                            this.removeEvent = new ParameterizedApi("/event/remove", this);
                            this.excuse = new ParameterizedApi("/attendance/excuse", this);
                            this.verify = new ParameterizedApi("/verify", this);
                            this.unverify = new ParameterizedApi("/unverify", this);
                            this.setSetting = new ParameterizedApi("/setSetting", this);
                            this.impersonate = new ParameterizedApi("/impersonate", this);
                        }
                        ApiController.prototype.setServerUrl = function (url) {
                            var _this = this;
                            if (url.match(/^!.*$/)) {
                                this.baseUrl = "https://" + url.substr(1) + ".herokuapp.com";
                            }
                            else if (url.match(/^@[A-Za-z0-9+/]{6}$/)) {
                                var ip = atob(url.substr(1));
                                this.baseUrl = "http://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3);
                            }
                            else if (url.match(/^#[A-Za-z0-9+/]{6}$/)) {
                                var ip = atob(url.substr(1));
                                this.baseUrl = "https://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3);
                            }
                            else if (url.match(/^@[A-Za-z0-9+/]{8}$/)) {
                                var ip = atob(url.substr(1));
                                this.baseUrl = "http://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3) + ":" + (ip.charCodeAt(4) * 256 + ip.charCodeAt(5));
                            }
                            else if (url.match(/^#[A-Za-z0-9+/]{8}$/)) {
                                var ip = atob(url.substr(1));
                                this.baseUrl = "https://" + ip.charCodeAt(0) + "." + ip.charCodeAt(1) + "." + ip.charCodeAt(2) + "." + ip.charCodeAt(3) + ":" + (ip.charCodeAt(4) * 256 + ip.charCodeAt(5));
                            }
                            else {
                                this.baseUrl = url;
                            }
                            this.isMock = null;
                            $.ajax(this.baseUrl + "/isMock.json", {
                                "cache": false,
                                "contentType": "application/json; charset=utf-8",
                                "dataType": "json",
                                "error": function (xhr, status, error) {
                                    console.error(error);
                                },
                                "jsonp": false,
                                "method": "GET",
                                "success": function (data) {
                                    _this.isMock = data;
                                    for (var i = 0; i < _this.isMockListeners.length; ++i) {
                                        _this.isMockListeners[i]();
                                    }
                                    _this.isMockListeners = [];
                                }
                            });
                        };
                        return ApiController;
                    }());
                    ApiController.instance = new ApiController();
                    apis.ApiController = ApiController;
                })(apis = management.apis || (management.apis = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var LoginRequest = org.usd232.robotics.management.apis.LoginRequest;
                    var LoginController = (function (_super) {
                        __extends(LoginController, _super);
                        function LoginController() {
                            return _super.apply(this, arguments) || this;
                        }
                        LoginController.readStorage = function () {
                            for (var i = 0; i < LoginController.instances.length; ++i) {
                                LoginController.instances[i].instanceReadStorage();
                            }
                        };
                        LoginController.prototype.instanceReadStorage = function () {
                            var _this = this;
                            this.$scope.$apply(function () {
                                _this.$scope.server = localStorage.getItem("serverUrl");
                                _this.$scope.username = localStorage.getItem("username");
                                if (_this.$scope.server) {
                                    $(".login-container label[for='server']").addClass("active");
                                }
                                if (_this.$scope.username) {
                                    $(".login-container label[for='user']").addClass("active");
                                    if (_this.$scope.server) {
                                        setTimeout(function () { return $(".login-container input[name='pass']").select(); }, 0);
                                    }
                                }
                            });
                        };
                        LoginController.prototype.init = function () {
                            var _this = this;
                            LoginController.instances.push(this);
                            this.$scope.create = function () { return management.HistoryController.load("/register"); };
                            this.$scope.login = function () {
                                ApiController.instance.setServerUrl(_this.$scope.server);
                                ApiController.instance.login.request(new LoginRequest(_this.$scope.username, _this.$scope.password), function (res) {
                                    if (res.authentication == "success") {
                                        localStorage.setItem("serverUrl", _this.$scope.server);
                                        localStorage.setItem("username", _this.$scope.username);
                                        LoginController.user = res;
                                        management.HistoryController.load("/home");
                                    }
                                    else {
                                        Materialize.toast("Invalid username or password!", 4000);
                                    }
                                });
                            };
                            setTimeout(function () { return _this.instanceReadStorage(); }, 0);
                        };
                        return LoginController;
                    }(management.AbstractPage));
                    LoginController.instances = [];
                    pages.LoginController = LoginController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var NotFoundController = (function (_super) {
                        __extends(NotFoundController, _super);
                        function NotFoundController() {
                            return _super.apply(this, arguments) || this;
                        }
                        NotFoundController.prototype.init = function () {
                        };
                        return NotFoundController;
                    }(management.AbstractPage));
                    pages.NotFoundController = NotFoundController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var Providers = (function () {
                    function Providers() {
                    }
                    Providers.getProviderNames = function () {
                        return [
                            "3 River Wireless",
                            "Accessyou",
                            "ACS Wireless",
                            "Aio Wireless",
                            "AirCel",
                            "AirFire Mobile",
                            "Airtel",
                            "Airtel (India)",
                            "Airtel (India)",
                            "Alaska Communications",
                            "Aliant Canada",
                            "Allied Wireless",
                            "Alltel",
                            "Ameritech",
                            "Andhra Pradesh AirTel",
                            "Andhra Pradesh Idea",
                            "Assurance Wireless",
                            "AT&T",
                            "AT&T Enterprise Paging",
                            "AT&T Mobility",
                            "Beeline",
                            "Bell Canada",
                            "Bell Mobility",
                            "Bell Mobility (Canada)",
                            "BellSouth",
                            "Blue Sky Frog",
                            "Bluegrass Cellular",
                            "Bluesky Communications",
                            "Boost Mobile",
                            "Bouygues Telecom",
                            "Box Internet Services",
                            "BPL Mobile",
                            "C Spire Wireless",
                            "Carolina West Wireless",
                            "CellCom",
                            "Cellular One",
                            "Cellular South",
                            "Centennial Wireless",
                            "CenturyTel",
                            "Chariton Valley Wireless",
                            "Chat Mobility",
                            "Chennai Airtel",
                            "Chennai RPG Cellular",
                            "Chennai Skycell",
                            "China Mobile",
                            "Cincinnati Bell",
                            "Cingular",
                            "Claro (Argentina)",
                            "Claro (Brazil)",
                            "Claro (Colombia)",
                            "Claro (Nicaragua)",
                            "Claro (Puerto Rico)",
                            "Clearnet",
                            "Cleartalk",
                            "Comcast",
                            "Comviq",
                            "Corr Wireless Communications",
                            "Cricket Wireless",
                            "CSL",
                            "CTI Móvil",
                            "Delhi Airtel",
                            "Delhi Hutch",
                            "Digicel",
                            "Dobson",
                            "DT T-Mobile",
                            "DTC",
                            "Dutchtone",
                            "E-Plus",
                            "Edge Wireless",
                            "Element Mobile",
                            "EMT",
                            "Emtel",
                            "Escotel",
                            "Esendex",
                            "Esendex (Spain)",
                            "Fido",
                            "Firmensms",
                            "Freebie SMS",
                            "General Communications Inc.",
                            "German T-Mobile",
                            "Globalstar",
                            "Globul",
                            "Goa Airtel",
                            "Goa BPLMobil",
                            "Goa Idea Cellular",
                            "Golden State Cellular",
                            "Golden Telecom",
                            "Greatcall",
                            "Gujarat Airtel",
                            "Gujarat Celforce",
                            "Gujarat Fascel",
                            "Gujarat Idea Cellular",
                            "Guyana T&T",
                            "Haryana Airtel",
                            "Haryana Escotel",
                            "Hawaiian Telecom",
                            "Helio",
                            "Himachai Pradesh Airtel",
                            "Houston Cellular",
                            "HSL Mobile",
                            "I-Wireless (Sprint PCS)",
                            "I-Wireless (T-Mobile)",
                            "ICE",
                            "Idea Cellular",
                            "Illinois Valley Cellular",
                            "Inland Cellular Telephone",
                            "JSM Tele-Page",
                            "Kajeet",
                            "Karnataka Airtel",
                            "Kerala Airtel",
                            "Kerala Escotel",
                            "Kolkata Airltel",
                            "Koodo Mobile",
                            "Kyivstar",
                            "Lauttamus Communication",
                            "LMT",
                            "LongLines",
                            "Lynx Mobility",
                            "M1",
                            "Madhya Pradesh Airtel",
                            "Maharashtra Airtel",
                            "Maharashtra BPL Mobile",
                            "Maharashtra Idea",
                            "Maharashtra Idea Cellular",
                            "Manitoba Telecom Systems",
                            "Más Móvil",
                            "MCI",
                            "Mediaburst",
                            "Meteor",
                            "Metro PCS",
                            "Metrocall",
                            "Metrocall 2-way",
                            "Microcell",
                            "Midwest Wireless",
                            "MiWorld",
                            "Mobilcomm",
                            "Mobileone",
                            "Mobilfone",
                            "Mobility Bermuda",
                            "Mobiltel",
                            "Mobistar Belgium",
                            "Mobitel (Sri Lanka)",
                            "Mobitel (Tanzania)",
                            "Mobitel (Srbija)",
                            "Movistar",
                            "Movistar (Argentina)",
                            "Movistar (Colombia)",
                            "Movistar (Latin America)",
                            "Movistar (Spain)",
                            "Movistar (Uruguay)",
                            "MTN",
                            "MTS Mobility",
                            "Mumbai Airtel",
                            "Mumbai BPL Mobile",
                            "My-Cool-SMS",
                            "Netcom",
                            "Nextech",
                            "Nextel",
                            "Nextel (Argentina)",
                            "Nextel (Mexico)",
                            "Ntelos",
                            "O2",
                            "OgVodafone",
                            "Ola",
                            "One Connect Austria",
                            "OnlineBeep",
                            "Optus Mobile",
                            "Orange",
                            "Orange (Netherlands)",
                            "Orange (UK)",
                            "Orange (Mumbai)",
                            "Oskar",
                            "P&T Luxembourg",
                            "Page Plus Cellular",
                            "PC Telecom",
                            "PCS One",
                            "Personal",
                            "Personal Communication",
                            "Pioneer Cellular",
                            "Plus",
                            "Pocket Wireless",
                            "Polkomtel",
                            "Pondicherry BPL Mobile",
                            "President’s Choice",
                            "Primtel",
                            "Project Fi",
                            "Public Service Cellular",
                            "Qwest Wireless",
                            "Red Pocket Mobile",
                            "Republic Wireless",
                            "Rogers AT&T Wireless",
                            "Rogers Wireless",
                            "Safaricom",
                            "SaskTel",
                            "Satelindo GSM",
                            "Satellink",
                            "SCS-900",
                            "Sendega",
                            "Setar Mobile email (Aruba)",
                            "SFR France",
                            "Síminn",
                            "Simple Freedom",
                            "Simple Mobile",
                            "Smart Telecom",
                            "SMS Broadcast",
                            "SMS Central",
                            "SMSPUP",
                            "Solo Mobile",
                            "South Central Communications",
                            "Southern LINC",
                            "Southwestern Bell",
                            "Spikko",
                            "Sprint",
                            "Straight Talk (AT&T)",
                            "Straight Talk (Other)",
                            "Straight Talk (Sprint)",
                            "Straight Talk (T-Mobile)",
                            "Straight Talk (Verizon)",
                            "Sumcom",
                            "Sunrise Communications",
                            "Sunrise Mobile",
                            "Surewest Communications",
                            "Swisscom",
                            "Syringa Wireless",
                            "T-Mobile (Australia)",
                            "T-Mobile (Austria)",
                            "T-Mobile (Croatia)",
                            "T-Mobile (Germany)",
                            "T-Mobile (Netherlands)",
                            "T-Mobile (USA)",
                            "T-Mobile (UK)",
                            "Tamil Nadu Aircel",
                            "Tamil Nadu Airtel",
                            "Tamil Nadu BPL Mobile",
                            "Telcel",
                            "Tele2",
                            "Tele2 Latvia",
                            "Telecom",
                            "Teleflip",
                            "Telefonica Movistar",
                            "Telenor",
                            "TeletopiaSMS",
                            "Teletouch",
                            "Telia Denmark",
                            "TellusTalk",
                            "Telstra",
                            "Telus",
                            "Telus Mobility",
                            "Tigo",
                            "TIM",
                            "Ting",
                            "Tracfone",
                            "TracFone (Prepaid)",
                            "Triton",
                            "TSR Wireless",
                            "Txtlocal",
                            "U.S. Cellular",
                            "UMC",
                            "Unicel",
                            "UniMóvil Corporation",
                            "Union Wireless",
                            "Uraltel",
                            "US Cellular",
                            "US West",
                            "USA Mobility",
                            "UTBox",
                            "Uttar Pradesh Escotel",
                            "Uttar Pradesh West Escotel",
                            "Verizon Wireless",
                            "Vessotel",
                            "Viaero",
                            "Virgin Mobile",
                            "Virgin Mobile (Canada)",
                            "Virgin Mobile (UK)",
                            "Virgin Mobile (USA)",
                            "Vivo",
                            "Vodacom",
                            "Vodafone (Germany)",
                            "Vodafone (Italy)",
                            "Vodafone (New Zealand)",
                            "Vodafone (Spain)",
                            "Vodafone (Japan)",
                            "Vodafone (UK)",
                            "Voyager Mobile",
                            "West Central Wireless",
                            "Western Wireless",
                            "Wind Mobile",
                            "Wyndtell",
                            "XIT Communications",
                        ];
                    };
                    return Providers;
                }());
                management.Providers = Providers;
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="../providers.ts" />
/// <reference path="login.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var RegisterRequest = org.usd232.robotics.management.apis.RegisterRequest;
                    var Providers = org.usd232.robotics.management.Providers; // VS doesn't like Liquid
                    var RegisterController = (function (_super) {
                        __extends(RegisterController, _super);
                        function RegisterController(name) {
                            var _this = _super.call(this, name) || this;
                            $(document).ready(function () {
                                var data = {};
                                var providers = Providers.getProviderNames();
                                for (var i = 0; i < providers.length; ++i) {
                                    data[providers[i]] = null;
                                }
                                $(".autocomplete-register-phone-provider").autocomplete({
                                    "data": data
                                });
                            });
                            return _this;
                        }
                        RegisterController.prototype.init = function () {
                            var _this = this;
                            this.$scope.back = function () { return management.HistoryController.back(); };
                            this.$scope.register = function () {
                                ApiController.instance.setServerUrl(_this.$scope.server);
                                ApiController.instance.register.request(new RegisterRequest(_this.$scope.username, _this.$scope.firstname, _this.$scope.lastname, _this.$scope.password, _this.$scope.email, _this.$scope.parentemail, _this.$scope.phonenumber, _this.$scope.provider), function (res) {
                                    if (res.success) {
                                        localStorage.setItem("serverUrl", _this.$scope.server);
                                        localStorage.setItem("username", _this.$scope.username);
                                        pages.LoginController.readStorage();
                                        management.HistoryController.load("/");
                                        Materialize.toast("Your account has been created!", 4000);
                                    }
                                    else {
                                        Materialize.toast("There was an error creating your account.", 4000);
                                    }
                                });
                            };
                            this.$scope.server = localStorage.getItem("serverUrl");
                        };
                        return RegisterController;
                    }(management.AbstractPage));
                    pages.RegisterController = RegisterController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var UserContact = org.usd232.robotics.management.apis.UserContact;
                    var Notifications = org.usd232.robotics.management.apis.notificiations.Notifications;
                    var SignInNotifications = org.usd232.robotics.management.apis.notificiations.SignInNotifications;
                    var MeetingNotifications = org.usd232.robotics.management.apis.notificiations.MeetingNotifications;
                    var AddContactRequest = org.usd232.robotics.management.apis.AddContactRequest;
                    var EditContactRequest = org.usd232.robotics.management.apis.EditContactRequest;
                    var RemoveContactRequest = org.usd232.robotics.management.apis.RemoveContactRequest;
                    var RsvpRequest = org.usd232.robotics.management.apis.RsvpRequest;
                    var ProfileController = (function (_super) {
                        __extends(ProfileController, _super);
                        function ProfileController() {
                            return _super.apply(this, arguments) || this;
                        }
                        ProfileController.show = function (user) {
                            if (user === void 0) { user = 0; }
                            ProfileController.newUser = user;
                            management.HistoryController.load("/profile");
                        };
                        ProfileController.prototype.init = function () {
                            var _this = this;
                            this.$scope.LoginController = pages.LoginController;
                            this.$scope.addEmail = function () {
                                var contact = new UserContact("email", _this.$scope.newEmail, null, null, new Notifications(new SignInNotifications(_this.$scope.newNotificationsManualSignIn, _this.$scope.newNotificationsAutoSignIn), _this.$scope.newNotificationsTeam, new MeetingNotifications(_this.$scope.newNotificationsMissedMeeting, _this.$scope.newNotificationsMeetingReminders)));
                                ApiController.instance.addContact.request(new AddContactRequest(contact), function (res) {
                                    if (res.success) {
                                        _this.$scope.$apply(function () {
                                            pages.LoginController.user.profile.contact.push(contact);
                                            _this.$scope.newEmail = "";
                                        });
                                    }
                                    else {
                                        Materialize.toast("An error occurred while adding email", 4000);
                                    }
                                });
                            };
                            this.$scope.addPhone = function () {
                                var contact = new UserContact("phone", null, _this.$scope.newPhone, _this.$scope.newPhoneProvider, new Notifications(new SignInNotifications(_this.$scope.newNotificationsManualSignIn, _this.$scope.newNotificationsAutoSignIn), _this.$scope.newNotificationsTeam, new MeetingNotifications(_this.$scope.newNotificationsMissedMeeting, _this.$scope.newNotificationsMeetingReminders)));
                                ApiController.instance.addContact.request(new AddContactRequest(contact), function (res) {
                                    if (res.success) {
                                        _this.$scope.$apply(function () {
                                            pages.LoginController.user.profile.contact.push(contact);
                                            _this.$scope.newPhone = "";
                                            _this.$scope.newPhoneProvider = "";
                                        });
                                    }
                                    else {
                                        Materialize.toast("An error occurred while adding phone number", 4000);
                                    }
                                });
                            };
                            this.$scope.changeContact = function ($index) {
                                var contact = pages.LoginController.user.profile.contact[$index];
                                ApiController.instance.editContact.request(new EditContactRequest(contact), function (res) {
                                    if (!res.success) {
                                        Materialize.toast("Unable to update notification settings", 4000);
                                    }
                                });
                            };
                            this.$scope.deleteContact = function ($index) {
                                var contact = pages.LoginController.user.profile.contact[$index];
                                ApiController.instance.removeContact.request(new RemoveContactRequest(contact), function (res) {
                                    if (res.success) {
                                        _this.$scope.$apply(function () { return pages.LoginController.user.profile.contact.splice($index, 1); });
                                    }
                                    else {
                                        Materialize.toast("An error occurred while removing contact", 4000);
                                    }
                                });
                            };
                            this.$scope.uploadImage = function () {
                                var file = $("input.profile-upload-image")[0].files[0];
                                var reader = new FileReader();
                                reader.onload = function () {
                                    ApiController.instance.setPicture.request(reader.result, function (res) {
                                        if (res.success) {
                                            _this.$scope.$apply(function () { return pages.LoginController.user.profile.picture = reader.result; });
                                        }
                                        else {
                                            Materialize.toast("An error occurred while setting picture", 4000);
                                        }
                                    });
                                };
                                reader.readAsDataURL(file);
                            };
                            this.$scope.startWebcam = function () {
                                navigator.mediaDevices.getUserMedia({
                                    "video": {
                                        "facingMode": "user"
                                    }
                                }).then(function (media) {
                                    _this.media = media;
                                    var video = $("video.profile-upload-image")[0];
                                    video.srcObject = media;
                                    video.play();
                                    $(".profile-take-picture").modal("open");
                                })["catch"](function (ex) {
                                    Materialize.toast("Unable to take picture", 4000);
                                });
                            };
                            this.$scope.cleanupWebcam = function () {
                                var video = $("video.profile-upload-image")[0];
                                video.pause();
                                _this.media.stop();
                            };
                            this.$scope.takePicture = function () {
                                var video = $("video.profile-upload-image")[0];
                                var canvas = $("canvas.profile-upload-image")[0];
                                canvas.width = video.videoWidth;
                                canvas.height = video.videoHeight;
                                var context = canvas.getContext("2d");
                                context.clearRect(0, 0, canvas.width, canvas.height);
                                context.drawImage(video, 0, 0, canvas.width, canvas.height);
                                var url = canvas.toDataURL();
                                ApiController.instance.setPicture.request(url, function (res) {
                                    if (res.success) {
                                        _this.$scope.$apply(function () { return pages.LoginController.user.profile.picture = url; });
                                    }
                                    else {
                                        Materialize.toast("An error occurred while setting picture", 4000);
                                    }
                                    _this.$scope.cleanupWebcam();
                                });
                            };
                            this.$scope.rsvp = function ($index, value) {
                                ApiController.instance.rsvp.request(new RsvpRequest(_this.$scope.events[$index].id, value), function (res) {
                                    if (!res.success) {
                                        Materialize.toast("An error occurred while rsvping", 4000);
                                    }
                                });
                            };
                            this.$scope.changePin = function () {
                                var pin = parseInt("" + _this.$scope.newPin);
                                ApiController.instance.changePin.request(pin, function (res) {
                                    if (res.success) {
                                        _this.$scope.$apply(function () {
                                            pages.LoginController.user.profile.pin = pin;
                                            _this.$scope.newPin = "";
                                        });
                                    }
                                    else {
                                        Materialize.toast("That pin has already been taken", 4000);
                                    }
                                });
                            };
                        };
                        ProfileController.prototype.open = function () {
                            var _this = this;
                            if (ProfileController.newUser == 0) {
                                this.$scope.ro = false;
                                this.$scope.user = pages.LoginController.user;
                                ApiController.instance.events.request(function (events) { return _this.$scope.$apply(function () {
                                    _this.$scope.events = events;
                                    setTimeout(function () {
                                        $(".indeterminate").prop("indeterminate", true);
                                        $(".profile-tabs").tabs("select_tab", "profile-tab-contact");
                                    });
                                }); });
                            }
                            else {
                                this.$scope.ro = true;
                                ApiController.instance.impersonate.request(ProfileController.newUser, function (res) { return _this.$scope.$apply(function () {
                                    _this.$scope.user = res;
                                }); });
                                ApiController.instance.attendance.request(ProfileController.newUser, function (events) { return _this.$scope.$apply(function () {
                                    _this.$scope.events = events;
                                    setTimeout(function () {
                                        $(".indeterminate").prop("indeterminate", true);
                                        $(".profile-tabs").tabs("select_tab", "profile-tab-contact");
                                    });
                                }); });
                            }
                        };
                        return ProfileController;
                    }(management.AbstractPage));
                    pages.ProfileController = ProfileController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var KioskController = (function (_super) {
                        __extends(KioskController, _super);
                        function KioskController() {
                            return _super.apply(this, arguments) || this;
                        }
                        KioskController.prototype.signIn = function (pin, verify) {
                            var _this = this;
                            ApiController.instance.kiosk.request(pin, function (user) { return _this.$scope.$apply(function () {
                                _this.$scope.user = user;
                                _this.$scope.confirm = user != null;
                                if (_this.$scope.confirm) {
                                    if (!verify) {
                                        _this.$scope.me();
                                    }
                                }
                                else {
                                    Materialize.toast("Invalid pin number", 4000);
                                }
                            }); });
                        };
                        KioskController.prototype.init = function () {
                            var _this = this;
                            this.$scope.go = function () { return _this.signIn(_this.$scope.pin, true); };
                            this.$scope.notme = function () {
                                _this.$scope.confirm = false;
                                _this.$scope.pin = '';
                            };
                            this.$scope.me = function () {
                                if (_this.$scope.user.unexcused > 10) {
                                    $("#kiosk-absence-error").modal("open");
                                }
                                else if (_this.$scope.user.unexcused > 5) {
                                    $("#kiosk-absence-warning").modal("open");
                                }
                                else {
                                    _this.$scope.finish();
                                }
                                _this.$scope.confirm = false;
                                _this.$scope.pin = '';
                            };
                            this.$scope.finish = function () {
                                ApiController.instance.kioskSignIn.request(_this.$scope.user.id, function (response) {
                                    if (response.success) {
                                        Materialize.toast(_this.$scope.user.name + " has been signed in!", 4000);
                                    }
                                    else {
                                        Materialize.toast("There was an error in signing in your account", 4000);
                                    }
                                });
                            };
                        };
                        KioskController.prototype.open = function () {
                            var _this = this;
                            var decoder = $(".kiosk-qr-video").WebCodeCamJQuery({
                                "DecodeQRCodeRate": 5,
                                "DecodeBarCodeRate": null,
                                "successTimeout": 500,
                                "codeRepetition": true,
                                "tryVertical": false,
                                "frameRate": 15,
                                "width": 640,
                                "height": 480,
                                "constraints": {
                                    "video": {
                                        "facingMode": "user"
                                    }
                                },
                                "flipVertical": false,
                                "flipHorizontal": false,
                                "zoom": -1,
                                "beep": "audio/beep.mp3",
                                "decoderWorker": "js/DecoderWorker.js",
                                "brightness": 0,
                                "autoBrightnessValue": false,
                                "grayScale": false,
                                "contrast": 0,
                                "threshold": 0,
                                "sharpness": [],
                                "resultFunction": function (result) { return _this.signIn(parseInt(result.code), false); },
                                "cameraSuccess": function () { },
                                "canPlayFunction": function () { },
                                "getDevicesError": function (error) { return Materialize.toast(error, 4000); },
                                "getUserMediaError": function (error) { return Materialize.toast(error, 4000); },
                                "cameraError": function (error) { return Materialize.toast(error, 4000); }
                            }).data().plugin_WebCodeCamJQuery;
                            decoder.init();
                            decoder.play();
                        };
                        return KioskController;
                    }(management.AbstractPage));
                    pages.KioskController = KioskController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="login.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var HomeController = (function (_super) {
                        __extends(HomeController, _super);
                        function HomeController() {
                            return _super.apply(this, arguments) || this;
                        }
                        HomeController.prototype.init = function () {
                            var _this = this;
                            this.$scope.LoginController = pages.LoginController;
                            this.$scope.trust = function (content) { return _this.$sce.trustAsHtml(content); };
                        };
                        HomeController.prototype.open = function () {
                            var _this = this;
                            ApiController.instance.recent.request(function (messages) { return _this.$scope.$apply(function () {
                                _this.$scope.messages = messages;
                            }); });
                        };
                        return HomeController;
                    }(management.AbstractPage));
                    pages.HomeController = HomeController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
/// <reference path="profile.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var UsersController = (function (_super) {
                        __extends(UsersController, _super);
                        function UsersController() {
                            return _super.apply(this, arguments) || this;
                        }
                        UsersController.prototype.init = function () {
                            var _this = this;
                            this.$scope.users = [];
                            this.$scope.openProfile = function (id) { return pages.ProfileController.show(id); };
                            this.$scope.updateVerification = function (user) {
                                if (user.verified) {
                                    ApiController.instance.verify.request(user.id, function (res) {
                                        if (!res.success) {
                                            Materialize.toast("Error verifying user", 4000);
                                            _this.$scope.$apply(function () { return user.verified = false; });
                                        }
                                    });
                                }
                                else {
                                    ApiController.instance.unverify.request(user.id, function (res) {
                                        if (!res.success) {
                                            Materialize.toast("Error unverifying user", 4000);
                                            _this.$scope.$apply(function () { return user.verified = true; });
                                        }
                                    });
                                }
                            };
                        };
                        UsersController.prototype.open = function () {
                            var _this = this;
                            ApiController.instance.users.request(function (users) { return _this.$scope.$apply(function () {
                                _this.$scope.users = users;
                            }); });
                        };
                        return UsersController;
                    }(management.AbstractPage));
                    pages.UsersController = UsersController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="../page.ts" />
/// <reference path="../navigation.ts" />
/// <reference path="../apis.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var pages;
                (function (pages) {
                    var ApiController = org.usd232.robotics.management.apis.ApiController;
                    var NotificationRequest = org.usd232.robotics.management.apis.NotificationRequest;
                    var HistoryController = org.usd232.robotics.management.HistoryController;
                    var MessageController = (function (_super) {
                        __extends(MessageController, _super);
                        function MessageController() {
                            return _super.apply(this, arguments) || this;
                        }
                        MessageController.prototype.init = function () {
                            var _this = this;
                            $("#send-notification-editor").on("materialnote.change", function (we, contents) {
                                _this.$scope.content = contents;
                            });
                            this.$scope.send = function () {
                                ApiController.instance.notify.request(new NotificationRequest(_this.$scope.content, _this.$scope.target), function (res) {
                                    if (res.success) {
                                        Materialize.toast("Message sent!", 4000);
                                        HistoryController.back();
                                    }
                                    else {
                                        Materialize.toast("Error sending message", 4000);
                                    }
                                });
                            };
                        };
                        return MessageController;
                    }(management.AbstractPage));
                    pages.MessageController = MessageController;
                })(pages = management.pages || (management.pages = {}));
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="page.ts" />
/// <reference path="pages/login.ts" />
/// <reference path="pages/404.ts" />
/// <reference path="pages/register.ts" />
/// <reference path="pages/profile.ts" />
/// <reference path="pages/kiosk.ts" />
/// <reference path="pages/home.ts" />
/// <reference path="pages/users.ts" />
/// <reference path="pages/send.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var LoginController = org.usd232.robotics.management.pages.LoginController;
                var NotFoundController = org.usd232.robotics.management.pages.NotFoundController;
                var RegisterController = org.usd232.robotics.management.pages.RegisterController;
                var ProfileController = org.usd232.robotics.management.pages.ProfileController;
                var KioskController = org.usd232.robotics.management.pages.KioskController;
                var HomeController = org.usd232.robotics.management.pages.HomeController;
                var UsersController = org.usd232.robotics.management.pages.UsersController;
                var MessageController = org.usd232.robotics.management.pages.MessageController;
                var PageFactory = (function () {
                    function PageFactory() {
                    }
                    PageFactory.construct = function () {
                        return [
                            new LoginController("login"),
                            new NotFoundController("404"),
                            new RegisterController("register"),
                            new ProfileController("profile"),
                            new KioskController("kiosk"),
                            new HomeController("home"),
                            new UsersController("users"),
                            new MessageController("send"),
                        ];
                    };
                    return PageFactory;
                }());
                management.PageFactory = PageFactory;
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
/// <reference path="angular.ts" />
/// <reference path="pageFactory.ts" />
/// <reference path="navigation.ts" />
/// <reference path="apis.ts" />
var org;
(function (org) {
    var usd232;
    (function (usd232) {
        var robotics;
        (function (robotics) {
            var management;
            (function (management) {
                var PageFactory = org.usd232.robotics.management.PageFactory; // VS doesn't like Liquid
                var Main = (function () {
                    function Main() {
                    }
                    Main.main = function () {
                        var nav = new management.NavBar();
                        var page = new management.PageController(nav);
                        $(document).ready(function () {
                            $("ul.tabs").tabs();
                            $(".modal").modal();
                            $(".dropdown-button").dropdown();
                            $("select").material_select();
                            $(".editor").materialnote({
                                "toolbar": [
                                    ["style", ["style", "bold", "italic", "underline", "strikethrough", "clear"]],
                                    ["fonts", ["fontsize", "fontname"]],
                                    ["color", ["color"]],
                                    ["undo", ["undo", "redo", "help"]],
                                    ["ckMedia", ["ckImageUploader", "ckVideoEmbeeder"]],
                                    ["misc", ["link", "picture", "table", "hr", "codeview", "fullscreen"]],
                                    ["para", ["ul", "ol", "paragraph", "leftButton", "centerButton", "rightButton", "justifyButton", "outdentButton", "indentButton"]],
                                    ["height", ["lineheight"]],
                                ],
                                "height": 550,
                                "minHeight": 100,
                                "defaultBackColor": "#e0e0e0"
                            });
                        });
                        management.HistoryController.setPageController(page);
                        PageFactory.construct();
                    };
                    return Main;
                }());
                management.Main = Main;
            })(management = robotics.management || (robotics.management = {}));
        })(robotics = usd232.robotics || (usd232.robotics = {}));
    })(usd232 = org.usd232 || (org.usd232 = {}));
})(org || (org = {}));
var _;
(function (_) {
    var Main = org.usd232.robotics.management.Main;
    Main.main();
})(_ || (_ = {}));
//# sourceMappingURL=main.js.map