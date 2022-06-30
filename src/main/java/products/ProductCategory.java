package products;

public enum ProductCategory {
    ELECTRONICS(1, "Electronics"),
    ATHLETICS(2, "Athletics"),
    CLOTHING(3, "Clothing"),
    HOME_APPLIANCE(4, "Home-Applicance");

    private final Integer code;
    private final String name;

    ProductCategory(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
