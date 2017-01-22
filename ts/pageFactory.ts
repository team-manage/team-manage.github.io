/// <reference path="page.ts" />

    /// <reference path="pages/login.ts" />

    /// <reference path="pages/404.ts" />

    /// <reference path="pages/register.ts" />

    /// <reference path="pages/profile.ts" />

    /// <reference path="pages/kiosk.ts" />

    /// <reference path="pages/home.ts" />

    /// <reference path="pages/users.ts" />

    /// <reference path="pages/send.ts" />

    /// <reference path="pages/events.ts" />

    /// <reference path="pages/event.ts" />

    /// <reference path="pages/attendance.ts" />


namespace org.usd232.robotics.management {
    
        import LoginController = org.usd232.robotics.management.pages.LoginController;
    
        import NotFoundController = org.usd232.robotics.management.pages.NotFoundController;
    
        import RegisterController = org.usd232.robotics.management.pages.RegisterController;
    
        import ProfileController = org.usd232.robotics.management.pages.ProfileController;
    
        import KioskController = org.usd232.robotics.management.pages.KioskController;
    
        import HomeController = org.usd232.robotics.management.pages.HomeController;
    
        import UsersController = org.usd232.robotics.management.pages.UsersController;
    
        import MessageController = org.usd232.robotics.management.pages.MessageController;
    
        import EventsController = org.usd232.robotics.management.pages.EventsController;
    
        import EventController = org.usd232.robotics.management.pages.EventController;
    
        import AttendanceController = org.usd232.robotics.management.pages.AttendanceController;
    

    export class PageFactory {
        public static construct(): AbstractPage[] {
            return [
                
                    new LoginController("login"),
                
                    new NotFoundController("404"),
                
                    new RegisterController("register"),
                
                    new ProfileController("profile"),
                
                    new KioskController("kiosk"),
                
                    new HomeController("home"),
                
                    new UsersController("users"),
                
                    new MessageController("send"),
                
                    new EventsController("events"),
                
                    new EventController("event"),
                
                    new AttendanceController("attendance"),
                
            ];
        }
    }
}
