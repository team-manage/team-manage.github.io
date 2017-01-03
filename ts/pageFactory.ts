/// <reference path="page.ts" />

    /// <reference path="pages/login.ts" />

    /// <reference path="pages/404.ts" />

    /// <reference path="pages/register.ts" />

    /// <reference path="pages/profile.ts" />

    /// <reference path="pages/kiosk.ts" />

    /// <reference path="pages/home.ts" />


namespace org.usd232.robotics.management {
    
        import LoginController = org.usd232.robotics.management.pages.LoginController;
    
        import NotFoundController = org.usd232.robotics.management.pages.NotFoundController;
    
        import RegisterController = org.usd232.robotics.management.pages.RegisterController;
    
        import ProfileController = org.usd232.robotics.management.pages.ProfileController;
    
        import KioskController = org.usd232.robotics.management.pages.KioskController;
    
        import HomeController = org.usd232.robotics.management.pages.HomeController;
    

    export class PageFactory {
        public static construct(): AbstractPage[] {
            return [
                
                    new LoginController("login"),
                
                    new NotFoundController("404"),
                
                    new RegisterController("register"),
                
                    new ProfileController("profile"),
                
                    new KioskController("kiosk"),
                
                    new HomeController("home"),
                
            ];
        }
    }
}
