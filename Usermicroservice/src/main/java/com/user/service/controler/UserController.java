package com.user.service.controler;

import java.util.List;

//import org.hibernate.internal.EntityManagerMessageLogger_.logger;
//import org.hibernate.internal.CoreMessageLogger_.logger;
//import org.hibernate.cache.spi.SecondLevelCacheLogger_.logger;
//import org.hibernate.internal.log.ConnectionPoolingLogger_.logger;
//import org.hibernate.internal.log.UrlMessageBundle_.logger;
//import org.hibernate.bytecode.enhance.spi.interceptor.BytecodeInterceptorLogging_.logger;
//import org.hibernate.internal.log.ConnectionAccessLogger_.logger;
//import org.hibernate.annotations.common.util.impl.Log_.logger;
//import org.hibernate.resource.beans.internal.BeansMessageLogger_.logger;
//import org.hibernate.internal.log.DeprecationLogger_.logger;
//import org.hibernate.internal.log.UnsupportedLogger_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entities.User;
import com.user.service.entities.User.UserBuilder;
import com.user.service.services.Impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserServiceImpl userServiceImpl;
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
          User user1 =   userServiceImpl.saveUser(user);
          return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //getting single user
    //this is the api which calls other service like (ratings,hotel,) so we have to implement circuit breaker here
    //first circuit name followed by which method to call if other services will not work, and have to create that fallback method
    
    
    int retryCount = 1;
    
    @GetMapping("/{userId}")
   // @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    
    //here we are commenting CircuitBreaker annotation just to check Retry module of Resilience4J b/c sometimes 
    //it is possible that service is UP but a bit slow(network issue etc) that is why we don't get desired response
    //using retry we can try again and again to check if it's really working
    
   // @Retry(name="ratingHotelServive",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
    	
    	//for retry module and should be removed when using circuit breaker
    	logger.info("Retry count {}",retryCount);
    	retryCount++;//removed till here for C.B.
    	
        User user2 =  userServiceImpl.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user2);
    }
    
    //creating fallback method for circuit breaker(here return type should be same as that of method where circuit breaker
    //applied
    
   
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
    	//for circuit breaker
    	//logger.info("Fallback is executed because service is down : ",ex.getMessage());//comment circuit breaker to check retry
    	
    	
    	
    	//dummy info is given if service is down
    	User dummyUser = User.builder()
    			.email("dummy@gmail.com")
    			.about("This is dummy user because service is down")
    			.name("Dummy").userId("123").build();
    	return new ResponseEntity<>(dummyUser,HttpStatus.OK);
    	
    }
    
    

    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users =  userServiceImpl.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);

    }
}
