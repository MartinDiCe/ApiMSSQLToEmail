package com.example.apiexportexceltoemail.persistences.repositories;

import com.example.apiexportexceltoemail.persistences.models.entities.DevoReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<DevoReport, Long> {

    @Modifying
    @Procedure(name="DevoReport.SP_ReporteDevolucionesPorFechaEntity")
    List<DevoReport> getReport(@Param("date") LocalDateTime date);

}
