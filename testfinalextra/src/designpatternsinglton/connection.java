package designpatternsinglton;



public class connection {
	private String Url;
	public static connection connect;
	private connection(String url) {
		super();
		Url = url;
	}
	public static connection getInstance(String Url) {
		if (connect==null) {
			connect=new connection(Url);
		}
		return connect;
		
		
	}
}
