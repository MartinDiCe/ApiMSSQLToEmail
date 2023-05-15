package com.example.apiexportexceltoemail.service;

import com.example.apiexportexceltoemail.persistences.models.entities.DevoReport;

import java.util.List;

public interface GetReportService {

    List<DevoReport> reportDevo();

}
