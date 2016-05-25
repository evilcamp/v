package org.evilcamp.v.controller.user;


import org.evilcamp.v.testuitls.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestUserController extends TestBase{

    @Test
    public void testShowMe() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user?name=xiaobai").servletPath("/user"))
                .andExpect(status().isOk()).andExpect(content().string("xiaobai is welcome!"));
    }
}
