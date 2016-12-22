/// <reference path="../typings/index.d.ts" />

namespace org.usd232.robotics.management.ng {
    export class AngularController {
        // TODO: Typings are broken for Angular
        // All of the `any` should be strongly typed once the Angular repository gets fixed
        private static app: any;
        private static controllers: { [name: string]: ($scope: any, $http: any) => void } = {};

        public static registerController(name: string, callback: ($scope: any, $http: any) => void): void {
            AngularController.controllers[name] = callback;
        }

        public static init(): void {
            AngularController.app = (window as any).angular.module("team-manage", []);
            Object.getOwnPropertyNames(AngularController.controllers).forEach(name => 
                AngularController.app.controller(name, AngularController.app.controllers[name])
            );
        }
    }
}
