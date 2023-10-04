
package com.ford.devsecops.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController  
public class HelloWorldController {
@GetMapping("/")
public String hello()   
{  
return "Hello javaTpoint Welcome to Ford GTBC Chennai";
}  
}
