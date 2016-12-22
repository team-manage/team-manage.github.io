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
                                return AngularController.app.controller(name, AngularController.app.controllers[name]);
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
                var Main = (function () {
                    function Main() {
                    }
                    Main.main = function () {
                        console.log("Hello, world!");
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