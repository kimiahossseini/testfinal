package Q234readjsonprocesswritedb;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
@SuppressWarnings("unused")

public class readjsonwriteDB {
	private static final Logger log= Logger.getLogger(readjsonwriteDB.class.getName());
	
	 //----(1)-----method read json and return List<product>---------------------
	public static List<product> readjson(String filename) throws productexception{
		File myfile=new File(filename);	
		log.info("Reading file: " + filename);
		ObjectMapper objectmapper=new ObjectMapper();
			List<product> productlist=new ArrayList<>();
			try {
				productlist = objectmapper.readValue(myfile, new TypeReference<List<product>>(){});
				log.info("your json file is read" + myfile);
				return productlist;
			} catch (StreamReadException e) {
				log.warning("your json file has syntax error");
				throw new productexception("Error while reading the JSON stream: ", e);		
			} catch (DatabindException e) {
				log.severe("json elements couldn't be mapped to java object");
				throw new productexception("Error while mapping JSON to Java object ", e);
			} catch (IOException e) {
				log.warning("Could not read file: " + filename);
				throw new productexception("I/O error occurred:", e);
			}
			
		}
	   
	   //-------(2)method create table/inset product into table----------------------------
	   public static void writedb(List<product> products) throws productexception { 
		   String sqlcreate="""
				   CREATE TABLE product_types (
					       id INT AUTO_INCREMENT PRIMARY KEY,
					       type_name VARCHAR(255) NOT NULL   
					   );

					   CREATE TABLE products (
					       id INT AUTO_INCREMENT PRIMARY KEY, 
					       name VARCHAR(255) NOT NULL,        
					       product_type_id INT NOT NULL,  
					       product_code VARCHAR(50) NOT NULL UNIQUE,
					       price FLOAT NOT NULL,              
					       sale BOOLEAN NOT NULL,          
					       FOREIGN KEY (product_type_id) REFERENCES product_types(id) 
					   );""";
		   		
			String sqlinserttyp1="""
				   MERGE INTO product_types (id, type_name)
					KEY (type_name)
					VALUES (DEFAULT, 'CLOTHES');
					MERGE INTO product_types (id, type_name)
					KEY (type_name)
					VALUES (DEFAULT, 'DIGITAL');
					""";	   
		   	String sqlinsertproduct="""
		   			INSERT INTO products(name,product_type_id,product_code,price,sale)
		   			VALUES(?,(SELECT id from product_types where type_name=?)
		   			,?,?,?);
		   			"""	;
		//----------------------------------------	
		 try(Connection connection=connectionmanager.getConnection()){
		 try (PreparedStatement preparedstatement1 = connection.prepareStatement(sqlcreate)) 
		 {
			 int resultset1=preparedstatement1.executeUpdate();
		 } catch (SQLException e) {
		  throw new productexception("sql exception has occured", e);
		 }
		 System.out.println();
		 //----------------------------------------
		 try (PreparedStatement preparedStatement2 = connection.prepareStatement(sqlinserttyp1)) {
			int resultset2=preparedStatement2.executeUpdate();
		} catch (SQLException e) {
			throw new productexception("sql exception has occured", e);	
		}
		//--------------------------------------------- 
		try (PreparedStatement preparedstatement3 = connection.prepareStatement(sqlinsertproduct)) {
			int rowaffect=0;
			 for (product productt : products) {
				 preparedstatement3.setString(1, productt.getProductName());
				 preparedstatement3.setString(2, productt.getProductType().name());
				 preparedstatement3.setString(3, productt.getProductCode());
				 preparedstatement3.setFloat(4, productt.getPrice());
				 preparedstatement3.setBoolean(5, productt.isSale());
				 preparedstatement3.executeUpdate();
				 rowaffect++;
				 log.info("insert product to table"+ productt.getProductName());
			 }	
			}
		} catch (SQLException e) {
			throw new productexception("sql exception has occurred", e);
			
		}
		System.out.println("we have inserted your products list");
		   				   
	   }
	   //-----------(3)method select-------ORM-------
	   public static List<product> selectdb() throws productexception{
		  float price=100;
		  String sqlselect="""
		  			SELECT 
    			e.NAME ,e.SALE ,e.PRODUCT_CODE ,d.TYPE_NAME 
    		FROM 
    			PRODUCTS  e
    		JOIN 
                PRODUCT_TYPES  d
    		ON 
    			e.PRODUCT_TYPE_ID  = d.ID  
    		WHERE 
    		e.PRICE >  ?
		  		""";
		   ResultSet resultset4;
		try (Connection connection1 = connectionmanager.getConnection();
				PreparedStatement preparedStatement4 = connection1.prepareStatement(sqlselect)) 
		{   List<product> productlist4 = new ArrayList<>();
			preparedStatement4.setFloat(1, price);
			resultset4 = preparedStatement4.executeQuery();
			 while (resultset4.next()) {
				 product productdb=new product();
				 productdb.setProductName(resultset4.getNString("NAME"));
				 productdb.setSale((resultset4.getBoolean("SALE")));
				 productdb.setProductCode(resultset4.getNString("PRODUCT_CODE"));
				 productdb.setProductType(ProductType.valueOf(resultset4.getString("TYPE_NAME").toUpperCase()));
				 productlist4.add(productdb)
				 ;	
			}  
			   return productlist4;
		} catch (SQLException e) {
			throw new productexception("sql exception has happened", e);
			
		}
		   
	   }
	  
}


