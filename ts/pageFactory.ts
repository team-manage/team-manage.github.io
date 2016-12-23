/// <reference path="page.ts" />

    /// <reference path="pages/login.ts" />

    /// <reference path="pages/404.ts" />


namespace org.usd232.robotics.management {
    
        import LoginController = org.usd232.robotics.management.pages.LoginController;
    
        import NotFoundController = org.usd232.robotics.management.pages.NotFoundController;
    

    export class PageFactory {
        public static construct(): AbstractPage[] {
            return [
                
                    new LoginController("login"),
                
                    new NotFoundController("404"),
                
            ];
        }
    }
}
