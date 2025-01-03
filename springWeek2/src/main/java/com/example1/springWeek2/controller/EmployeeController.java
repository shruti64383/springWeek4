package com.example1.springWeek2.controller;

import com.example1.springWeek2.dto.EmployeeDTO;
import com.example1.springWeek2.entities.EmployeeEntity;
import com.example1.springWeek2.repositories.EmployeeRepo;
import com.example1.springWeek2.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getMessage")
//    public String getMessage(){
//        return "Super Tasty Chewing Gum Recipe";
//    }

//    @GetMapping(path = "/{employeeid}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeid") Long id) {
//        return new EmployeeDTO(id, 23, "Shruti", "a.@gmail.com", LocalDate.of(2024, 12, 20));
//    }
//
//    @GetMapping
//    public String getAllEmployee(@RequestParam(required = false, name = "inputAge") int age,
//                             @RequestParam(required = false) String sortBy){
//        return "Hi your age is "+age+" "+sortBy;
//    }
//
//    @PostMapping
//    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO input){
//        input.setId(134L);
//        return input;
//    }
//
//    @PutMapping
//    public String updateEmployee(){
//        return "updated";
//    }










//    private final EmployeeRepo employeeRepo;
//
//    public EmployeeController(EmployeeRepo employeeRepo){
//        this.employeeRepo = employeeRepo;
//    }
//
//    @GetMapping(path = "/{employeeid}")
//    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeid") Long id) {
//        return employeeRepo.findById(id).orElse(null);
//    }
//
//    @GetMapping
//    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false, name = "inputAge") int age,
//                                               @RequestParam(required = false) String sortBy){
//        return employeeRepo.findAll();
//    }
//
//    @PostMapping
//    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployee){
//        return employeeRepo.save(inputEmployee);
//    }









    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeid}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeid") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false, name = "inputAge") Integer age,
                                               @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId ){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> update, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(update, employeeId);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}


