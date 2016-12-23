/// <reference path="angular.ts" />
/// <reference path="pageFactory.ts" />
/// <reference path="navigation.ts" />
/// <reference path="apis.ts" />

namespace org.usd232.robotics.management {
    import AngularController = org.usd232.robotics.management.ng.AngularController;
    let PageFactory = (org.usd232.robotics.management as any).PageFactory; // VS doesn't like Liquid

    export class Main {
        public static main(): void {
            let nav: NavBar = new NavBar();
            let page: PageController = new PageController(nav);
            HistoryController.setPageController(page);
            PageFactory.construct();
        }
    }
}

namespace _ {
    import Main = org.usd232.robotics.management.Main;
    Main.main();
}
