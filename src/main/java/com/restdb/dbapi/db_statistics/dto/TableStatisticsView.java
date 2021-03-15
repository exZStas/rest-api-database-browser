package com.restdb.dbapi.db_statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableStatisticsView {
    private Long recordsCount;
    private Long tableAttributesCount;
}
