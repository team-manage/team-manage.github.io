/// <reference path="page.ts" />

    /// <reference path="pages/login.ts" />

    /// <reference path="pages/404.ts" />

    /// <reference path="pages/register.ts" />


namespace org.usd232.robotics.management {
    
        import LoginController = org.usd232.robotics.management.pages.LoginController;
    
        import NotFoundController = org.usd232.robotics.management.pages.NotFoundController;
    
        import RegisterController = org.usd232.robotics.management.pages.RegisterController;
    

    export class PageFactory {
        public static construct(): AbstractPage[] {
            return [
                
                    new LoginController("login"),
                
                    new NotFoundController("404"),
                
                    new RegisterController("register"),
                
            ];
        }
    }
}
