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
            $(document).ready(() => {
                ($("ul.tabs") as any).tabs();
                ($(".modal") as any).modal();
                $(".dropdown-button").dropdown();
                $("select").material_select();
                $(".button-collapse").sideNav();
                $(".collapsible").collapsible();
                ($(".editor") as any).materialnote({
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
            HistoryController.setPageController(page);
            PageFactory.construct();
        }
    }
}

namespace _ {
    import Main = org.usd232.robotics.management.Main;
    Main.main();
}
