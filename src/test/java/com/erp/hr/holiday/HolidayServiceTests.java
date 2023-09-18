package com.erp.hr.holiday;

import com.erp.hr.holiday.model.HolidayStatus;
import com.erp.hr.holiday.repository.HolidayRepository;
import com.erp.hr.holiday.service.HolidayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}
