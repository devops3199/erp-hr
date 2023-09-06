package com.erp.hr.holiday.repository;

import com.erp.hr.holiday.model.Holiday;
import com.erp.hr.holiday.model.HolidayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    List<Holiday> findByEmployeeId(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Holiday h SET h.status = ?2, h.approvedBy = ?3 WHERE h.holidayId = ?1")
    int updateApprovedByAndStatusByHolidayId(int holidayId, HolidayStatus status, int approvedBy);

    @Transactional
    @Modifying
    @Query("UPDATE Holiday h SET h.status = ?2, h.rejectedBy = ?3 WHERE h.holidayId = ?1")
    int updateRejectedByAndStatusByHolidayId(int holidayId, HolidayStatus status, int rejectedBy);
}
