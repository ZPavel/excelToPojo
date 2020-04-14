package com.zpavel.excelToPojo.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelToPojoUtilsTest {
    @Test
    public void testToPojo() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/ExcelUtilsTest.xlsx");
        List<Pojo> pojos = ExcelToPojoUtils.toPojo(Pojo.class, is);
        assertEquals(pojos.size(), 1);
        Pojo pojo = pojos.get(0);
        assertEquals(pojo.getId(), 1L);
        assertEquals(pojo.getName(), "Tennis balls");
        assertEquals(pojo.getManufactured(), LocalDate.of(2020, 5, 1));
        assertEquals(pojo.getInStock(), Boolean.TRUE);
        assertEquals(pojo.getCount(), 4);
        assertEquals(pojo.getCategories(), Arrays.asList("Sports", "Leisure"));
        assertEquals(pojo.getUnitPrice(), new BigDecimal("5.55"));
        assertEquals(pojo.getLastUpdated(), LocalDateTime.of(2020, 5, 1, 12, 0, 0));
    }

    @Data
    @NoArgsConstructor
    static class Pojo {
        private Long id;
        private String name;
        private LocalDate manufactured;
        private Boolean inStock;
        private Integer count;
        private List<String> categories;
        private BigDecimal unitPrice;
        private LocalDateTime lastUpdated;
    }
}