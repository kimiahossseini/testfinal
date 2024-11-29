package designpatternBUILDER;

public class MainHouse {
	public static void main(String[] args) {
        House validHouse = 
       		 new House.Builder()
       		 			.area(150)
       		 			.location("Tehran")
       		 			.name("Family Home")
       		 			.postalCode("123456")
       		 			.build();

        House invalidHouse = new House.Builder()
	                 .area(10)
	                 .location("") // This will trigger validation
	                 .name("Invalid House")
	                 .postalCode("234237")
	                 .build();

}
}
