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
                            AngularController.app = window.angular.module("team-manage", []);
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
                        AngularController.registerController("page-" + name, function ($scope, $http) {
                            _this.$scope = $scope;
                            _this.$http = $http;
                            _this.init();
                        });
                    }
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
                            $scope.goBack = function () { return HistoryController.back(); };
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
                        this.nav = nav;
                        AngularController.registerController("pages", function ($scope) {
                            _this.$scope = $scope;
                            HistoryController.load("/");
                        });
                    }
                    PageController.prototype.loadPage = function (name) {
                        this.$scope.name = name;
                        this.nav.loadPage(name);
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
                    var ContactType;
                    (function (ContactType) {
                        ContactType[ContactType["email"] = 0] = "email";
                        ContactType[ContactType["phone"] = 1] = "phone";
                    })(ContactType = apis.ContactType || (apis.ContactType = {}));
                    var notificiations;
                    (function (notificiations) {
                        var SignInNotifications = (function () {
                            function SignInNotifications() {
                            }
                            return SignInNotifications;
                        }());
                        notificiations.SignInNotifications = SignInNotifications;
                        var MeetingNotifications = (function () {
                            function MeetingNotifications() {
                            }
                            return MeetingNotifications;
                        }());
                        notificiations.MeetingNotifications = MeetingNotifications;
                        var Notifications = (function () {
                            function Notifications() {
                            }
                            return Notifications;
                        }());
                        notificiations.Notifications = Notifications;
                    })(notificiations = apis.notificiations || (apis.notificiations = {}));
                    var UserContact = (function () {
                        function UserContact() {
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
                        function Event() {
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
                        SettingType[SettingType["int"] = 1] = "int";
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
                    var KioskSignInRequest = (function () {
                        function KioskSignInRequest(pin) {
                            this.pin = pin;
                        }
                        return KioskSignInRequest;
                    }());
                    apis.KioskSignInRequest = KioskSignInRequest;
                    var ApiBase = (function () {
                        function ApiBase(url, ctrlr) {
                            this.lastModified = 0;
                            this.url = url;
                            this.ctrlr = ctrlr;
                        }
                        ApiBase.prototype.handleSuccess = function (data, xhr, callback, lateCallback) {
                            var lastModified = Date.parse(xhr.getResponseHeader("Last-Modified"));
                            if (lastModified > this.lastModified) {
                                if (this.lastResponse) {
                                    lateCallback(data);
                                }
                                else {
                                    callback(data);
                                }
                                this.lastResponse = data;
                                this.lastModified = lastModified;
                            }
                        };
                        ApiBase.prototype.sendRequest = function (url, content, method, callback, lateCallback) {
                            var _this = this;
                            if (this.lastResponse) {
                                callback(this.lastResponse);
                            }
                            $.ajax(this.ctrlr.baseUrl + this.url + url, {
                                "cache": false,
                                "contentType": "application/json; charset=utf-8",
                                "data": content,
                                "dataType": "json",
                                "error": function (xhr, status, error) {
                                    console.error(error);
                                    if (!_this.lastResponse) {
                                        callback(null);
                                    }
                                },
                                "headers": {
                                    "X-Session-Token": this.ctrlr.sessionToken
                                },
                                "ifModified": this.lastResponse != null,
                                "jsonp": false,
                                "method": method,
                                "success": function (data, status, xhr) { return _this.handleSuccess(data, xhr, callback, lateCallback); }
                            });
                        };
                        return ApiBase;
                    }());
                    var ParameterizedApi = (function (_super) {
                        __extends(ParameterizedApi, _super);
                        function ParameterizedApi() {
                            return _super.apply(this, arguments) || this;
                        }
                        ParameterizedApi.prototype.request = function (param, callback, lateCallback) {
                            if (lateCallback === void 0) { lateCallback = callback; }
                            // TODO Change to POST when the real API server is used
                            this.sendRequest(".json", param, "GET", callback, lateCallback);
                        };
                        return ParameterizedApi;
                    }(ApiBase));
                    apis.ParameterizedApi = ParameterizedApi;
                    var LoginApi = (function (_super) {
                        __extends(LoginApi, _super);
                        function LoginApi(url, ctrlr) {
                            var _this = _super.call(this, url, ctrlr) || this;
                            var base = _this.handleSuccess;
                            _this.handleSuccess = function (data, xhr, callback, lateCallback) {
                                ctrlr.sessionToken = xhr.getResponseHeader("X-Session-Token");
                                base.apply(_this, [
                                    data, xhr, callback, lateCallback
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
                            return _super.apply(this, arguments) || this;
                        }
                        CollectionApi.prototype.request = function (id, callback, lateCallback) {
                            if (lateCallback === void 0) { lateCallback = callback; }
                            this.sendRequest("/" + id + ".json", null, "GET", callback, lateCallback);
                        };
                        return CollectionApi;
                    }(ApiBase));
                    apis.CollectionApi = CollectionApi;
                    var ApiController = (function () {
                        function ApiController() {
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
                        }
                        ApiController.prototype.setServerUrl = function (url) {
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
                        LoginController.prototype.init = function () {
                            var _this = this;
                            this.$scope.create = function () { return management.HistoryController.load("/create"); };
                            this.$scope.login = function () {
                                ApiController.instance.setServerUrl(_this.$scope.server);
                                ApiController.instance.login.request(new LoginRequest(_this.$scope.username, _this.$scope.password), function (res) {
                                    if (res.authentication == "success") {
                                        localStorage.setItem("serverUrl", _this.$scope.server);
                                        localStorage.setItem("username", _this.$scope.username);
                                        management.HistoryController.load("/home");
                                    }
                                    else {
                                        Materialize.toast("Invalid username or password!", 4000);
                                    }
                                });
                            };
                            this.$scope.server = localStorage.getItem("serverUrl");
                            this.$scope.username = localStorage.getItem("username");
                            if (this.$scope.server && this.$scope.username) {
                                setTimeout(function () { return $(".login-container input[name='pass']").select(); }, 0);
                            }
                        };
                        return LoginController;
                    }(management.AbstractPage));
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
/// <reference path="page.ts" />
/// <reference path="pages/login.ts" />
/// <reference path="pages/404.ts" />
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
                var PageFactory = (function () {
                    function PageFactory() {
                    }
                    PageFactory.construct = function () {
                        return [
                            new LoginController("login"),
                            new NotFoundController("404"),
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