package com.rivers.file.service;

import com.rivers.userservice.proto.User;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Log4j2
public class ExportService {

    public ByteArrayInputStream exportToUserExcel(List<User> list) {
        String[] columns = {"员工号", "用户名", "邮箱", "昵称"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("用户信息");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // Row for Header
            Row headerRow = sheet.createRow(0);
            // Header
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }
            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
            int rowIdx = 1;
            for (User customer : list) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(customer.getUserId());
                row.createCell(1).setCellValue(customer.getUsername());
                row.createCell(2).setCellValue(customer.getMail());
                row.createCell(3).setCellValue(customer.getNickname());
                Cell ageCell = row.createCell(3);
                ageCell.setCellStyle(ageCellStyle);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            log.error("EXPORT USER ERROR", e);
        }
        return null;
    }


}
