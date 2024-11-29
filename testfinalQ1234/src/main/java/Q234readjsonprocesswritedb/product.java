package Q234readjsonprocesswritedb;

import java.util.Objects;

public class product {
	private String productName;
	private ProductType productType;
	private String productCode;
	private float price;
	private boolean sale;
	public product(){};
	

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isSale() {
		return sale;
	}
	public void setSale(boolean sale) {
		this.sale = sale;
	}
	@Override
	public String toString() {
		return "product [name=" + productName + ", productType=" + productType + ", productCode=" + productCode + ", price="
				+ price + ", sale=" + sale + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(productName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		product other = (product) obj;
		return Objects.equals(productName, other.productName);
	}




}
