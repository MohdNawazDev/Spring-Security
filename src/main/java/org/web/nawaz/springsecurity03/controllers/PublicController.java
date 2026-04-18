    package org.web.nawaz.springsecurity03.controllers;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/public")
    public class PublicController {

        @GetMapping("/hello")
        public String Hello()
        {
            return "hello";
        }

        @GetMapping("/info")
        public String info()
        {
            return "Something new started..";
        }



    }
