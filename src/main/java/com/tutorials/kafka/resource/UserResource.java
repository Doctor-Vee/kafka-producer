package com.tutorials.kafka.resource;

import com.tutorials.kafka.model.Employee;
import com.tutorials.kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Employee> employeeKafkaTemplate;

    private static final String MESSAGE_TOPIC = "MESSAGE_TOPIC";
    private static final String USER_TOPIC = "USER_TOPIC";
    private static final String EMPLOYEE_TOPIC = "EMPLOYEE_TOPIC";

    @GetMapping("/publish/message/{message}")
    public String postMessage(@PathVariable("message") final String message) {
        stringKafkaTemplate.send(MESSAGE_TOPIC, message);
        return "Published message details successfully";
    }

    @GetMapping("/publish/user/{name}")
    public String postUser(@PathVariable("name") final String name) {
        userKafkaTemplate.send(USER_TOPIC, new User(name, "Engineering", "Hello, I am " + name));
        return "Published user details successfully";
    }

    @GetMapping("/publish/employee/{name}")
    public String postEmployee(@PathVariable("name") final String name) {
        employeeKafkaTemplate.send(EMPLOYEE_TOPIC, new Employee(name, "Software Engineer", (double) name.length() * 500));
        return "Published employee details successfully";
    }
}
