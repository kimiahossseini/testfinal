package designpatternBUILDER;


public class httpconnection {
	private String Url;
	private String port;
	private String user;
	private String pass;
	private int timeout;
	
	private httpconnection(Builder builder) {
		this.Url=builder.Url;
		this.port=builder.port;
		this.user=builder.user;
		this.pass=builder.pass;
		this.timeout=builder.timeout;
		
	}
	
	public static class Builder{
		private String Url;
		private String port;
		private String user;
		private String pass;
		private int timeout;
		
		
		public Builder Url(String Url) {
			this.Url=Url;
			return this;
		}
		public Builder port(String port) {
			this.port=port;
			return this;
		}
		public Builder user(String user) {
			this.user=user;
			return this;
		}
		public Builder pass(String pass) {
			this.pass=pass;
			return this;
		}
		public Builder timeout(int timeout) {
			this.timeout=timeout;
			return this;
		}
		public httpconnection build() {
			//validate Url start with http------
			if (Url == null || !Url.startsWith("http")) {
				throw new IllegalArgumentException("Invalid URL: Must start with 'http'");
				}
			
			//validate user
			 if (user == null || !user.equals("kimia")) {
	                throw new IllegalArgumentException("Invalid user: User must be 'kimia'.");
	            }

			return new httpconnection(this);
			
		}
	
	}
}
