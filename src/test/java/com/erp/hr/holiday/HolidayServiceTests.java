package com.erp.hr.holiday;

import com.erp.hr.employee.model.Division;
import com.erp.hr.employee.model.Employee;
import com.erp.hr.employee.model.Role;
import com.erp.hr.holiday.model.HolidayStatus;
import com.erp.hr.holiday.repository.HolidayRepository;
import com.erp.hr.holiday.service.HolidayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTests {

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private HolidayService holidayService;

    @DisplayName("연차 상태 변경 - 권한 체크")
    @Test
    public void HolidayService_modifyStatus_Throws401() {
        // Arrange
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Collection authorities = List.of(new SimpleGrantedAuthority("staff"));

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getAuthorities())
                .thenReturn(authorities);
        // Act
        // Assert
        Assertions.assertThrows(ResponseStatusException.class,
                () -> this.holidayService.modifyStatus(1, HolidayStatus.APPROVED));
    }

    @DisplayName("연차 상태 변경")
    @Test
    public void HolidayService_modifyStatus_ReturnsVoid() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2023, 9, 24, 12, 0, 0);
        MockedStatic<LocalDateTime> localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(date);

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

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Collection authorities = List.of(new SimpleGrantedAuthority("leader"));

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getAuthorities())
                .thenReturn(authorities);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .thenReturn(sam);
        // Act
        when(this.holidayRepository.updateStatusByHolidayId(5, HolidayStatus.APPROVED, 2, LocalDateTime.now()))
                .thenReturn(1);

        // Assert
        this.holidayService.modifyStatus(5, HolidayStatus.APPROVED);
    }
}
