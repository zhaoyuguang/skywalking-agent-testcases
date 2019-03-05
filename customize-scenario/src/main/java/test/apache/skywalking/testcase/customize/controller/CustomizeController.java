/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package test.apache.skywalking.testcase.customize.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.apache.skywalking.testcase.customize.model.Model0;
import test.apache.skywalking.testcase.customize.model.Model1;
import test.apache.skywalking.testcase.customize.service.TestService1;
import test.apache.skywalking.testcase.customize.service.TestService2;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/customize")
@PropertySource("classpath:application.properties")
public class CustomizeController {

    private Logger logger = LoggerFactory.getLogger(CustomizeController.class);

    @Autowired
    private TestService1 testService1;
    @Autowired
    private TestService2 testService2;

    @RequestMapping("/customize-case")
    @ResponseBody
    public String customizeCase() {
        testService1.method();
        testService1.method("str0", 123);
        Model0 m0 = new Model0("id", 123, new Model1(100, "name"), new HashMap() {{
            put("k1", "v1");
        }}, new ArrayList() {{
            add("a");
        }}, new Object[]{'1', 2, "3"});
        testService1.method(m0, "def_str_0", 123);
        TestService1.staticMethod();
        TestService1.staticMethod("id", 123, new HashMap() {{
            put("k1", "v1");
        }}, new ArrayList() {{
            add("a");
        }}, new Object[]{'1', 2, "3"});

        testService2.method();
        testService2.method("", 123);
        TestService2.staticMethod("s", 123);

        logger.info("Success");
        return "Success";
    }

}
