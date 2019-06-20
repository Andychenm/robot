package com.robot.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.robot.entity.Body;
import com.robot.entity.Request;
import com.robot.entity.Text;
import com.robot.handler.ContentHandler;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MainController {
    @Autowired
    private ContentHandler contentHandler;
    
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    String home(@RequestBody String requestStr) throws Exception {
        log.info(requestStr);
        Request request = JSON.parseObject(requestStr, Request.class);
        Body body = contentHandler.getBodyByRequest(request);
        String res = JSON.toJSONString(body);
        log.info(res);
        return res;
    }
    
    @RequestMapping(value = "/test/{content}", produces = "application/json; charset=utf-8")
    @ResponseBody
    String test(@PathVariable("content") String content) {
        Request r = new Request();
        r.setText(new Text());
        r.getText().setContent(content);
        r.setAtUsers(new ArrayList<>());
        r.setSenderId("1");
        Body body = contentHandler.getBodyByRequest(r);
        return JSON.toJSONString(body);
    }
}
