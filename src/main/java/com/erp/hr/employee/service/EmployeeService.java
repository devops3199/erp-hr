package com.erp.hr.employee.service;

import com.erp.hr.employee.dto.EmployeeRequestDto;
import com.erp.hr.employee.dto.EmployeeResponseDto;
import com.erp.hr.employee.model.Employee;
import com.erp.hr.employee.repository.DivisionRepository;
import com.erp.hr.employee.repository.EmployeeRepository;
import com.erp.hr.employee.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DivisionRepository divisionRepository;

    @Autowired
    public EmployeeService(PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository, RoleRepository roleRepository, DivisionRepository divisionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.divisionRepository = divisionRepository;
    }

    public List<EmployeeResponseDto> getEmployees() {
        var employees = this.employeeRepository.findAll();
        return employees.stream().map(employee -> EmployeeResponseDto.builder()
                    .employeeId(employee.getEmployeeId())
                    .email(employee.getEmail())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .phoneNumber(employee.getPhoneNumber())
                    .lastLogin(employee.getLastLogin())
                    .joinDate(employee.getJoinDate())
                    .roleName(employee.getRole().getName())
                    .divisionName(employee.getDivision().getName())
                    .build()).collect(Collectors.toList());
    }

    public EmployeeResponseDto getEmployee(int id) {
        var employee = this.employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("No such employee found"));

        return EmployeeResponseDto.builder()
                .employeeId(employee.getEmployeeId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .lastLogin(employee.getLastLogin())
                .joinDate(employee.getJoinDate())
                .roleName(employee.getRole().getName())
                .divisionName(employee.getDivision().getName())
                .build();
    }

    @Transactional
    public void registerEmployee(EmployeeRequestDto employeeRequestDto) {
        String encodedPassword = this.passwordEncoder.encode(employeeRequestDto.getPassword());
        var division = this.divisionRepository.findById(employeeRequestDto.getDivisionId());
        var role = this.roleRepository.findById(employeeRequestDto.getRoleId());

        if (division.isPresent() && role.isPresent()) {
            var employee = Employee.builder()
                    .email(employeeRequestDto.getEmail())
                    .password(encodedPassword)
                    .firstName(employeeRequestDto.getFirstName())
                    .lastName(employeeRequestDto.getLastName())
                    .phoneNumber(employeeRequestDto.getPhoneNumber())
                    .division(division.get())
                    .role(role.get())
                    .joinDate(employeeRequestDto.getJoinDate())
                    .build();

            this.employeeRepository.save(employee);
        }
    }
}
