package org.evilcamp.v.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by suxiaofei on 2016-05-23
 */

@Controller
public class UserController {

    @RequestMapping("/user")
    @ResponseBody
    public String showMe(String name){
        return name+" is welcome!";
    }
}
