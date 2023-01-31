package com.example.crud.crud_demo.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="www.org.com")
public class EmployeeController {
	
	
    @Autowired
    public EmployeeService empSer;
    @GetMapping("/employees")
    public List<Employee> getEmployee(){
        return empSer.getEmployee();
    }

    @GetMapping("/employee/{id}")
    public String getEmp(@PathVariable Integer id){
        return empSer.getOne(id);
    }

    @PostMapping("/employee")
    public String add(@RequestBody Employee e) {
        return empSer.insertEmp(e);
    }
    
    @PutMapping("/employee/{id}")
    public String update(@RequestBody Employee e, @PathVariable Integer id){

       return empSer.updateEmp(e, id);
    }

    @DeleteMapping("/employee/{id}")
    public String delete(@PathVariable Integer id){
        return empSer.deleteEmp(id);
    }
	
	
	
}
