package streams;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


public class Inventory {
	 private List<Product> products;

	    public Inventory(List<Product> products) {
	        this.products = products;
	    }

	    public void printNameSortedProducts() {
            products.stream()
            .sorted(Comparator.comparing(Product::getName))
            .map(p->p.getName())
            .forEach(System.out::println);
	    }

        public void printNOrderSortedProducts() {
            products.stream()
            .sorted(Comparator.comparing(p->p.getOrderCount()))
            .map(Product::getName)
            .forEach(System.out::println);
	    }

        public void orderAllProductsOnce(){
            products.stream()
            .forEach(p->p.placeOrder(1));
        }

        public void orderSpecificProduct(String productName, Integer nPieces){
            products.stream()
            .filter(p->p.getName().equals(productName))
            .forEach(p->p.placeOrder(nPieces));
        }

        public boolean atLeastOneRequestPerProduct(){
            return products.stream()
            .anyMatch(p -> p.getOrderCount()>0);
        }

        public long countOrderedMoreThanOnce(){
            return products.stream()
                .filter(p -> p.getOrderCount()>1)
                .count();
        }

        public String mostPopularProductByNOrders(){
            Optional<Product> p= products.stream()
                    .max(Comparator.comparing(Product::getOrderCount));
            
            if (p.isEmpty()){
                return "There are no orders for any product";
            }else{
                    return p.get().getName();
                }
        }
        public String mostPopularProductByNPieces(){
            Optional<Product> popP = products.stream()
                        //.forEach((p)->{System.out.println(p.getName() + " " + p.getOrders().stream().reduce(0, (a, b) -> (a+b)));});}        
                        .max(Comparator.comparing((Product p)->p.getOrders().stream().reduce(0, (a, b) -> (a+b))));
            
            if (popP.isEmpty()){
                return "There are no order with more than 0 pieces placed for any product";
            }else{
                    return popP.get().getName();
                }
            }
            
            
        public Map<String, List<String>> groupProductsByType(){
            return products.stream()
                            .collect(groupingBy(Product::getType, TreeMap::new, mapping(p->p.getName(), toList())));
        }

        //pieces requested per each product
        public Map<String, Long> countPiecesPerProduct(){
            return products.stream()
                            .collect(toMap(Product::getName, p->p.getOrders().stream().mapToLong(Long::new).sum()));
        }

        public Map<Integer, List<String>> groupProductsByNOrders(){
            return products.stream()
                            .collect(groupingBy(Product::getOrderCount, TreeMap::new, mapping(Product::getName, toList())));
        }

        
            
}

        