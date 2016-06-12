package org.evilcamp.v.controller.user;


import org.evilcamp.v.testuitls.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestUserController extends TestBase{

    @Test
    public void testShowMe() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user?name=xiaoming").servletPath("/user"))
                .andExpect(status().isOk()).andExpect(content().string("xiaoming is welcome!"));
    }

    @Test
    public void testAdd() throws Exception {
        String current = System.currentTimeMillis()+"";
        String actionPath = "/user/add";
        String requestPath = actionPath +"?userName=tu_"+current+"&password=1234&nickName=tn_"+current;
        this.mockMvc.perform(MockMvcRequestBuilders.get(requestPath).servletPath(actionPath))
                .andExpect(status().isOk()).andExpect(content().string("{\"code\":\"200\",\"msg\":\"success\"}"));
    }


    @Test
    public void testDelete() throws Exception {
        String current = System.currentTimeMillis()+"";
        String actionPath = "/user/add";
        String requestPath = actionPath +"?userName=tu_"+current+"&password=1234&nickName=tn_"+current;
        this.mockMvc.perform(MockMvcRequestBuilders.get(requestPath).servletPath(actionPath))
                .andExpect(status().isOk()).andExpect(content().string("{\"code\":\"200\",\"msg\":\"success\"}"));

        actionPath = "/user/delete";
        requestPath = actionPath +"?userName=tu_"+current;
        this.mockMvc.perform(MockMvcRequestBuilders.get(requestPath).servletPath(actionPath))
                .andExpect(status().isOk()).andExpect(content().string("{\"code\":\"200\",\"msg\":\"success\"}"));

    }

    @Test
    public void testQuery() throws Exception {
        String current = System.currentTimeMillis()+"";
        String actionPath = "/user/add";
        String requestPath = actionPath +"?userName=tu_"+current+"&password=1234&nickName=tn_"+current;
        this.mockMvc.perform(MockMvcRequestBuilders.get(requestPath).servletPath(actionPath))
                .andExpect(status().isOk()).andExpect(content().string("{\"code\":\"200\",\"msg\":\"success\"}"));

        actionPath = "/user/query";
        requestPath = actionPath +"?userName=tu_"+current;
        String result = this.mockMvc.perform(MockMvcRequestBuilders.get(requestPath).servletPath(actionPath))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertTrue(result.contains("200")&&result.contains("success"));

    }
}
