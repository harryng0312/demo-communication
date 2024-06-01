package org.harryng.demo.controller.rs;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.util.ResponseWrapper;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.TextUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@Slf4j
public class HelloController {
//    @Value("test-value")
//    private String[] testValues;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(SessionHolder sessionHolder, @RequestParam(name = "name", required = false, defaultValue = "World") String name) throws Exception {
        log.info("into /hello:{}", name);
//        return "Hello " + name+ " " + testValues.length;
        return TextUtil.objToJson(ResponseWrapper.<String>builder()
                .data(MessageFormat.format("Hello {0}", name))
                .build());
    }

}
