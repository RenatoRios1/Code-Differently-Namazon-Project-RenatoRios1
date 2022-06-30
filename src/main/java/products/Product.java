package products;

public class Product {

    private static Long idCount = 1L;
    private Long id;
    private String name;
    private ProductCategory category;
    private Double price;

    public Product(String name, ProductCategory category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = idCount++;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", name, category.getName(), price);
    }
}
