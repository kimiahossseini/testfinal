package Q234readjsonprocesswritedb;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SuppressWarnings("unused")
public class processproductmain {

	public static void main(String[] args) throws productexception {
		List<product> productss=readjsonwriteDB.readjson("ProductData.json");	
	//average price on digital product--------------
	 double average=productss.stream()
			 .filter(t -> t.getProductType()==ProductType.DIGITAL)
			 .collect(Collectors.averagingDouble(product::getPrice));
	 System.out.println("average price on digital product  "+average);
	 
	 //group products based on type and sort them based on price----------
	 Map<ProductType,List<product>> mapproduct=productss.stream()
			 .collect(Collectors.groupingBy(
				 product::getProductType,
				 Collectors.collectingAndThen
				 (Collectors.toList(),list->list.stream()
						 .sorted((o1, o2) ->Float.compare(o1.getPrice(), o2.getPrice()) )
						 .collect(Collectors.toList())) ));
	 mapproduct.forEach((t, u) -> System.out.println(t+"    " +u  ));

	 //grouping specifically ------------------------------------------	 
	 Map<Object, Object> mapproduct2=productss.stream()
			     .collect(Collectors.groupingBy(
				 (t ->t.getProductType()==ProductType.DIGITAL ),
				 Collectors.collectingAndThen
				 (Collectors.toList(),list->list.stream()
						 .sorted((o1, o2) ->Float.compare(o1.getPrice(), o2.getPrice()) )
						 .collect(Collectors.toList())) ));
	 System.out.println(mapproduct2.get(true));
	 
	//partitioning products to lux and non lux price>100 and no sale
	Map<Boolean, List<product>> mapproduct3=productss.stream()
			.filter(t -> t.getPrice()>=100)
			.collect(Collectors.partitioningBy(t -> t.isSale()==false));
	mapproduct3.forEach((t, u) -> System.out.println(t+"  "+u));
	
	//creat list products have sale and reverse sort name-------
	List<product> productlist=productss.stream()
			.filter(t -> t.isSale()==true )
			.sorted((o1, o2) -> o2.getProductName().compareTo(o1.getProductName())) 
			.collect(Collectors.toList());
	System.out.println(productlist);
	//search based on name and get code product--------------
	String name="skirt";
	 Optional<product> codename=productss.stream()
			.filter(t -> t.getProductName().equals(name)).findFirst();
      System.out.println(codename.get().getProductCode());
    //remove duplicated product with same name-----USE DISTINCT------
      List<product> productlist2=productss.stream()
    		           .distinct().collect(Collectors.toList());
    		  
      productlist2.forEach(t -> System.out.println(t));
      
      
      
	}

}
