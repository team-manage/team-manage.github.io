/// <reference path="angular.ts" />

namespace org.usd232.robotics.management {
    import AngularController = org.usd232.robotics.management.ng.AngularController;

    export class NavBar {
        private $scope: any;

        public loadPage(name: string): void {
            let element: HTMLDivElement = document.getElementById("page-" + name) as HTMLDivElement;
            if ( element ) {
                document.title = $("input[name='pageTitle']", element).val();
            }
            setTimeout(() =>
                this.$scope.$apply(() =>
                    this.$scope.title = element ? $("input[name='navbarTitle']", element).val() : null
                )
            , 0);
            setTimeout(() =>
                $(".loading").addClass("done")
            , 0);
        }

        public constructor() {
            AngularController.registerController("header", $scope => {
                this.$scope = $scope;
                $scope.back = () => HistoryController.back();
                $scope.go = (url: string) => HistoryController.load(url);
                $scope.openProfile = () => (management as any).pages.ProfileController.show(); // Cyclic dependency
                $scope.LoginController = (management as any).pages.LoginController; // Cyclic dependency
            });
        }
    }

    export class HistoryController {
        private static history: string[] = [];
        private static page: PageController;

        public static setPageController(ctrlr: PageController): void {
            HistoryController.page = ctrlr;
        }

        public static getPageController(): PageController {
            return HistoryController.page;
        }

        public static load(url: string): void {
            let tmp = $("input[name='pageUrl'][value='" + url + "']").parent("div");
            let page: string = tmp.length > 0 ? tmp[0].id.substr(5) : "404";
            HistoryController.page.loadPage(page);
            HistoryController.history.push(url);
            history.replaceState({}, document.title, url);
        }

        public static back(): void {
            HistoryController.history.pop();
            this.load(HistoryController.history.pop());
        }
    }

    export class PageController {
        public static defaultPage: string = "/";
        private $scope;
        private nav: NavBar;
        private listeners: { [name: string]: () => void } = {};

        public loadPage(name: string): void {
            this.$scope.name = name;
            this.nav.loadPage(name);
            if ( this.listeners[name] ) {
                this.listeners[name]();
            }
        }

        public onLoad(name: string, callback: () => void): void {
            this.listeners[name] = callback;
        }

        public constructor(nav: NavBar) {
            this.nav = nav;
            AngularController.registerController("pages", $scope => {
                this.$scope = $scope;
                HistoryController.load(PageController.defaultPage);
            });
        }
    }
}
