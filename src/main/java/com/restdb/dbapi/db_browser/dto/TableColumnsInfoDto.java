package com.restdb.dbapi.db_browser.dto;

import lombok.Data;

@Data
public class TableColumnsInfoDto {
    private String columnName;
    private String dataType;
    private String dataLength;
    private String nullable;
    private String constraintName;
    private String constraintType;
}
