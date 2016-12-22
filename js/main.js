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
                var NavBar = (function () {
                    function NavBar(history) {
                        var _this = this;
                        AngularController.registerController("header", function ($scope) {
                            _this.$scope = $scope;
                            $scope.goBack = function () { return history.back(); };
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
                    HistoryController.prototype.back = function () {
                        // TODO
                        history.back();
                    };
                    return HistoryController;
                }());
                management.HistoryController = HistoryController;
                var PageController = (function () {
                    function PageController(nav) {
                        var _this = this;
                        this.nav = nav;
                        AngularController.registerController("pages", function ($scope) {
                            _this.$scope = $scope;
                            var tmp = $("input[name='pageUrl'][value='" + location.pathname + "']").parent("div");
                            _this.loadPage(tmp.length > 0 ? tmp[0].id.substr(5) : "404");
                        });
                    }
                    PageController.prototype.loadPage = function (name) {
                        this.$scope.name = name;
                        this.nav.loadPage(name);
                    };
                    return PageController;
                }());
                management.PageController = PageController;
                var Main = (function () {
                    function Main() {
                    }
                    Main.main = function () {
                        var history = new HistoryController();
                        var nav = new NavBar(history);
                        var page = new PageController(nav);
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