package accounts;

import exceptions.OrderNotFoundException;
import orders.Order;
import org.javatuples.Pair;
import products.Product;
import products.ProductCategory;
import products.ProductNotAvailableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vendor extends Account{

    private String brandName;
    private Map<String, Pair<Product, Integer>> inventoryQty;
    private Product[] showcase;
    private List<Order> orders;

    public Vendor(String brandName, String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.brandName = brandName;
        inventoryQty = new HashMap<>();
        showcase = new Product[5];
        orders = new ArrayList<>();
    }

    public void addProduct(Product product){
        if(inventoryQty.containsKey(product.toString())){
            Pair<Product, Integer> read = inventoryQty.get(product.toString());
            Pair<Product, Integer> temp = new Pair<>(product, read.getValue1()+1);
            inventoryQty.put(product.toString(), temp);

        }else{
            Pair<Product, Integer> temp = new Pair<>(product,1);
            inventoryQty.put(product.toString(), temp);
        }
    }

    //This should be part of some menu class that manages the flow of wuestions and options
    ///because this could mean modify price, Product name, or product category
    public Boolean modifyProduct(Product product){


        return false;
    }

    public Boolean removeProductByPurchase(Product product){
        if(inventoryQty.containsKey(product.toString())){
            Pair<Product, Integer> read = inventoryQty.get(product.toString());
            int qtyLeft = read.getValue1();
            //remove
            if(qtyLeft == 1){
                inventoryQty.remove(product.toString());
            }else{
                Pair<Product, Integer> temp = new Pair<>(product, read.getValue1());
                Pair<Product, Integer> toAdd = new Pair<>(product, temp.getValue1()-1);
                inventoryQty.put(product.toString(), toAdd);
            }
            return true;
        }
        return false;
    }

    public Boolean cancelOrder(Order order) throws OrderNotFoundException {
        Boolean orderFound = false;
        Order orderToRemove = null;
        for(Order eachOrder: orders) {
            if(order.toString().equals(eachOrder.toString())) {
                orderToRemove = eachOrder;
                orderFound = true;
                break;
            }
        }
        if(orderFound){
            removeProductByPurchase(order.getProduct());
            orders.remove(orderToRemove);
            return true;
        }else{
            throw new OrderNotFoundException("Order cannot be canceled. Order not canceled");
        }
    }

    public void addProductToShowcase(Product product, Integer position){
        showcase[position] = product;
    }

    public Product[] displayShowcase(){
        for(Product each: showcase) {
            if (each != null) {
                System.out.println(each.toString());
            }
        }
        return showcase;
    }


    public Integer getQtyInInventory(Product product) {
        if(inventoryQty.containsKey(product.toString())){
            Pair<Product, Integer> read = inventoryQty.get(product.toString());
            return read.getValue1();
        }else{
            return -1;
        }
    }

    public List<Product> searchByCategory(ProductCategory category){
        List<Product> results = new ArrayList<>();
        for(Map.Entry<String, Pair<Product, Integer>> entry : inventoryQty.entrySet()){
            Pair<Product, Integer> read = entry.getValue();
            if(read.getValue0().getCategory() == category){
                results.add((read.getValue0()));
            }
        }
        return results;
    }

    public List<Product> searchByName(String name) {
        List<Product> results = new ArrayList<>();
        for (Map.Entry<String, Pair<Product, Integer>> entry : inventoryQty.entrySet()) {
            Pair<Product, Integer> read = entry.getValue();
            if (read.getValue0().getName().equals(name) || read.getValue0().toString().contains(name)) {
                results.add((read.getValue0()));
            }
        }
        return results;
    }

    //New Incoming order m
    public Product addOrder(Order order)   {
        orders.add(order);
        return order.getProduct();
    }


    public List<Order> displayAllOrders() {
        if(orders.size() == 0 ){
            System.out.println("There are no orders to display.");
        }else {
            for (Order eachOrder : orders) {
                System.out.println(eachOrder.toString());
            }
        }
        return orders;
    }






}


