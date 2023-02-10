package com.example.crud.crud_demo.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRep;
    
    public List<Employee> getEmployee(){
        return empRep.findAll();
    }

    public String getOne(Integer id){
    	if (empRep.existsById(id)) {
        return empRep.findById(id).toString();
        }
    	else {
    		return "No Employee with "+id;
    	}
    }

    public String insertEmp(Employee e) {
        empRep.save(e);
        return "Employee details save Successfully";
        }
    
    public String updateEmp(Employee e, Integer id){
    	if (empRep.existsById(id)) {
        empRep.save(e);
        return "Employee with Id: "+id+" is Updated Successfully";
    }else {
    	return "Employee do not Exist with Id: "+id;
    }
    }

    public String deleteEmp(Integer id){
    	if (empRep.existsById(id)) {
        empRep.deleteById(id);
        return "Employee with Id: "+id+" is Deleted Successfully";}
    	else {
    		return "Employee with Id: "+id+" do not exist";
    	}
    }

}