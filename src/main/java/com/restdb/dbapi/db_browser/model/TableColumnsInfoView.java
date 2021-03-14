package com.restdb.dbapi.db_browser.model;

import com.restdb.dbapi.constant.ConstraintType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableColumnsInfoView {
    private String columnName;
    private String dataType;
    private String dataLength;
    private String nullable;
    private String constraintName;
    private ConstraintType constraintType;
}
