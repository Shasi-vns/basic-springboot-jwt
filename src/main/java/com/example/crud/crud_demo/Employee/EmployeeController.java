package com.example.crud.crud_demo.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.crud_demo.Jwt.JwtService;
import com.example.crud.crud_demo.UserParites.User;
import com.example.crud.crud_demo.UserParites.UserService;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class EmployeeController {
	
	 @Autowired
	    private JwtService jwtService;

	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @Autowired
	    private UserService us;
	    
    @Autowired
    public EmployeeService empSer;
    @GetMapping("/employees")
    public List<Employee> getEmployee(){
        return empSer.getEmployee();
    }
    
    @GetMapping("/employees/{id}")
    public String getEmp(@PathVariable Integer id) throws Exception{
        return empSer.getOne(id);
    }

    @PostMapping("/employees")
    public String add(@RequestBody Employee e) {
        return empSer.insertEmp(e);
    }
    
    @PutMapping("/employees/{id}")
    public String update(@RequestBody Employee e, @PathVariable Integer id){

       return empSer.updateEmp(e, id);
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable Integer id){
        return empSer.deleteEmp(id);
    }
	
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            return "No User found in DataBase";
        }

    }
    
    @PostMapping("/user")
    public String add(@RequestBody User u) {
    	u.setPassword(us.encodePassword(u.getPassword()));
       return us.insertUser(u);
    }
   
	
}
