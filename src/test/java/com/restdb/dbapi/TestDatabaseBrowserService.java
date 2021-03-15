package com.restdb.dbapi;

import com.restdb.dbapi.db_browser.DatabaseBrowserRepository;
import com.restdb.dbapi.db_browser.DatabaseBrowserService;
import com.restdb.dbapi.db_browser.dto.TableDataPreview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestDatabaseBrowserService {

    @Spy
    @InjectMocks
    private DatabaseBrowserService databaseBrowserService;

    @Mock
    private DatabaseBrowserRepository databaseBrowserRepository;

    private String id_1 = "1234";
    private String id_2 = "1237";
    private String address_1 = "columbuslaan";
    private String address_2 = "washington dc";
    private String columnName_1 = "ID";
    private String columnName_2 = "ADDRESS";

    @BeforeEach
    public void setUp() {
        List<TableDataPreview> tableDataPreviews = new ArrayList<>();
        tableDataPreviews.add(createTableDataPreview("ID", "1234"));
        tableDataPreviews.add(createTableDataPreview("ID", "1237"));
        tableDataPreviews.add(createTableDataPreview("ADDRESS", "columbuslaan"));
        tableDataPreviews.add(createTableDataPreview("ADDRESS", "washington dc"));

        Mockito.when(databaseBrowserRepository.getTableDataPreview(Mockito.anyString())).thenReturn(tableDataPreviews);
    }

    @Test
    void test_getTableColumnsInfoView() {
        //given
        List<String> idRecords = new ArrayList<>();
        idRecords.add(id_1);
        idRecords.add(id_2);
        List<String> addressRecords = new ArrayList<>();
        addressRecords.add(address_1);
        addressRecords.add(address_2);

        Map<String, List<String>> expectedTableDataPreview = new LinkedHashMap<>();
        expectedTableDataPreview.put(columnName_1, idRecords);
        expectedTableDataPreview.put(columnName_2, addressRecords);
        //when
        Map<String, List<String>> actualTableDataPreview = databaseBrowserService.getTableDataPreview("PERSON");
        //then
        assertThat(actualTableDataPreview).containsAllEntriesOf(expectedTableDataPreview);
    }

    private TableDataPreview createTableDataPreview(String columnName, String record) {
        Map<String, String> columnRecordMap = new LinkedHashMap<>();
        columnRecordMap.put(columnName, record);
        return new TableDataPreview(columnRecordMap);
    }
}
