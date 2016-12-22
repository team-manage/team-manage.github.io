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

        public constructor(history: HistoryController) {
            AngularController.registerController("header", $scope => {
                this.$scope = $scope;
                $scope.goBack = () => history.back();
            });
        }
    }

    export class HistoryController {
        public back(): void {
            // TODO
            history.back();
        }
    }

    export class PageController {
        private $scope;
        private nav: NavBar;

        public loadPage(name: string): void {
            this.$scope.name = name;
            this.nav.loadPage(name);
        }

        public constructor(nav: NavBar) {
            this.nav = nav;
            AngularController.registerController("pages", $scope => {
                this.$scope = $scope;
                let tmp = $("input[name='pageUrl'][value='" + location.pathname + "']").parent("div");
                this.loadPage(tmp.length > 0 ? tmp[0].id.substr(5) : "404");
            });
        }
    }

    export class Main {
        public static main(): void {
            let history: HistoryController = new HistoryController();
            let nav: NavBar = new NavBar(history);
            let page: PageController = new PageController(nav);
        }
    }
}

namespace _ {
    import Main = org.usd232.robotics.management.Main;
    Main.main();
}
