package Q234readjsonprocesswritedb;

import java.util.List;

public class appamazon {

	public static void main(String[] args) throws Exception {
		List<product> productss=readjsonwriteDB.readjson("ProductData.json");
		productss.forEach(t ->System.out.println(t));
		readjsonwriteDB.writedb(productss);
		
		//-------select from db and use ORM-----
		List<product> products2=readjsonwriteDB.selectdb();
		System.out.println(products2);

	}

}
