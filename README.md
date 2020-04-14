# excelToPojo

## Simple convertor from Excel xls/xlsx files to Java pojo class

Having an xlsx extension file we want to parse in Java :

| Id | Name | Manufactured | In Stock | Count | Categories | Unit Price | Last Updated |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | Tennis balls | 2020-05-01 | 1 | 4 | Sports, Leisure | 5.55 | 2020-05-01T12:00:00 |

One of the most advanced and popular library for parsing Excel files in Java is Apache Poi.
This library allows you to read this kind of files but doesn't provide a simple way to parse an xls/xlsx files to a Pojo class.

ExcelToPojoUtils is simple utility class that can help you to fill this gap.
It uses Apache Poi an Lombok library, generics and reflection to be the most flexible.

## To parse an Excel file and to get a list of pojos :

### First create a pojo class you would get :

```
@Data
@NoArgsConstructor
public class Pojo {
	private Long id;
	private String name;
	private LocalDate manufactured;
	private Boolean inStock;
	private Integer count;
	private List<String> categories;
	private BigDecimal unitPrice;
	private LocalDateTime lastUpdated;
}
```
The field names of your pojo should use camel case. If needed it could be changed by modifying strToFieldName method.

Only these types are managed :
- Long
- String
- LocalDate
- LocalDateTime
- Boolean
- Integer
- List
- BigDecimal

The boolean flag to true in cell is : 1
The list separator is : ,

These two could be changed to your convenience.


```
InputStream is = this.getClass().getResourceAsStream("/ExcelUtilsTest.xlsx");
List<Pojo> pojos = ExcelToPojoUtils.toPojo(Pojo.class, is);
```

These types can be parsed :
public static final String BOOLEAN_TRUE = "1";
    public static final String LIST_SEPARATOR = ",";

Here is the unit test in ExcelToPojoUtilsTest class : 

```
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
```

Any pull requests or other features could be added in future.


