package com.example1.springWeek2.services;


import com.example1.springWeek2.dto.EmployeeDTO;
import com.example1.springWeek2.entities.EmployeeEntity;
import com.example1.springWeek2.repositories.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper){
        this.employeeRepo=employeeRepo;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepo.findById(id).map(employeeEntity->modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity employeeToSave = employeeRepo.save(employeeEntity);
        return modelMapper.map(employeeToSave, EmployeeDTO.class);
    }


    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEntity = employeeRepo.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public boolean isExistsById(Long Id){
        return employeeRepo.existsById(Id);
    }

    public boolean deleteById(Long employeeId) {
        boolean exists =  isExistsById(employeeId);
        if(!exists) return false;
        employeeRepo.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> update, Long employeeId) {
        boolean exists =  isExistsById(employeeId);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).get();
        update.forEach((field, value)->{
            Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, employeeEntity, value);
        });

        return modelMapper.map(employeeRepo.save(employeeEntity), EmployeeDTO.class);

    }
}
