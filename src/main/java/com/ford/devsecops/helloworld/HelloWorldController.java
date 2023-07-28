
package com.ford.devsecops.helloworld;

@RestController  
public class HelloWorldController {
@RequestMapping("/")  
public String hello()   
{  
return "Hello welcome to javaTpoint";
}  
}
