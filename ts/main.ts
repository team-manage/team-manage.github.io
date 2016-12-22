/// <reference path="angular.ts" />

namespace org.usd232.robotics.management {
    export class Main {
        public static main(): void {
            console.log("Hello, world!");
        }
    }
}

namespace _ {
    import Main = org.usd232.robotics.management.Main;
    Main.main();
}
