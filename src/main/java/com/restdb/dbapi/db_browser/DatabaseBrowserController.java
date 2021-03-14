package com.restdb.dbapi.db_browser;

import com.restdb.dbapi.config.EntityToDtoMapper;
import com.restdb.dbapi.db_browser.dto.TableColumnsInfoDto;
import com.restdb.dbapi.db_browser.model.TableColumnsInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("database_browser")
@RestController
public class DatabaseBrowserController {

    @Autowired
    private DatabaseBrowserService databaseBrowserService;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    @GetMapping("/tables")
    public ResponseEntity<List<String>> getAllTables() {
        return new ResponseEntity<>(databaseBrowserService.getTables(), HttpStatus.OK);
    }

    @GetMapping("/tables/{tableName}")
    public ResponseEntity<List<TableColumnsInfoDto>> getTableColumnsInfo(@PathVariable(value = "tableName") String tableName) {
        List<TableColumnsInfoView> tableColumnsInfoView = databaseBrowserService.getTableColumnsInfoView(tableName);
        List<TableColumnsInfoDto> tableColumnsInfoDtos = entityToDtoMapper.tableColumnsInfoViewsToDtos(tableColumnsInfoView);
        return new ResponseEntity<>(tableColumnsInfoDtos, HttpStatus.OK);
    }

    @GetMapping("/tables/{tableName}/preview")
    public ResponseEntity<Map<String, List<String>>> getTableDataPreview(@PathVariable(value = "tableName") String tableName) {
        Map<String, List<String>> tableDataPreview = databaseBrowserService.getTableDataPreview(tableName);
        return new ResponseEntity<>(tableDataPreview, HttpStatus.OK);
    }

    @GetMapping("/schemas")
    public ResponseEntity<List<String>> getSchemas() {
        return new ResponseEntity<>(databaseBrowserService.getSchemas(), HttpStatus.OK);
    }
}
