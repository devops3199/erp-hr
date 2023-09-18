package com.erp.hr.employee;

import com.erp.hr.employee.dto.EmployeeResponseDto;
import com.erp.hr.employee.model.Division;
import com.erp.hr.employee.model.Employee;
import com.erp.hr.employee.model.Role;
import com.erp.hr.employee.repository.EmployeeRepository;
import com.erp.hr.employee.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @DisplayName("직원 리스트 조회")
    @Test
    public void EmployeeService_getEmployees_ReturnsEmployeeDtoList() {
        // Arrange
        Employee ray = Employee.builder()
                .employeeId(1)
                .email("ray@erphr.com")
                .firstName("Ray")
                .lastName("Jeong")
                .phoneNumber("01012345678")
                .division(Division.builder().divisionId(1).name("executive").leaderId(null).build())
                .role(Role.builder().roleId(1).name("admin").build())
                .joinDate(LocalDate.parse("2023-08-15"))
                .build();
        Employee sam = Employee.builder()
                .employeeId(2)
                .email("sam@erphr.com")
                .firstName("Sam")
                .lastName("Eastwood")
                .phoneNumber("01087654321")
                .division(Division.builder().divisionId(7).name("software").leaderId(null).build())
                .role(Role.builder().roleId(2).name("leader").build())
                .joinDate(LocalDate.parse("2023-08-16"))
                .build();

        EmployeeResponseDto rayDto = EmployeeResponseDto.builder()
                .employeeId(1)
                .email("ray@erphr.com")
                .firstName("Ray")
                .lastName("Jeong")
                .phoneNumber("01012345678")
                .divisionName("executive")
                .roleName("admin")
                .joinDate(LocalDate.parse("2023-08-15"))
                .build();

        EmployeeResponseDto samDto = EmployeeResponseDto.builder()
                .employeeId(2)
                .email("sam@erphr.com")
                .firstName("Sam")
                .lastName("Eastwood")
                .phoneNumber("01087654321")
                .divisionName("software")
                .roleName("leader")
                .joinDate(LocalDate.parse("2023-08-16"))
                .build();

        List<Employee> employeeList = List.of(ray, sam);
        List<EmployeeResponseDto> employeeResponseDtoList = List.of(rayDto, samDto);

        when(this.employeeRepository.findAll()).thenReturn(employeeList);

        // Act
        var result = this.employeeService.getEmployees();

        // Assert
        assertEquals(result, employeeResponseDtoList);
    }
}
