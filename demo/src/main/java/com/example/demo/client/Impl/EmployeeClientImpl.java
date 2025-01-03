package com.example.demo.client.Impl;

import com.example.demo.advice.ApiResponse;
import com.example.demo.client.EmployeeClient;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImpl implements  EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try{
            ApiResponse<List<EmployeeDTO>> employeeDtoList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDtoList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDTO> employeeDTO = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            ApiResponse<EmployeeDTO> employeeDTO1 = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO1.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
