package designpatternBUILDER;


public class mainhttp {
	public static void main(String[] args) {
		try {
            httpconnection connection = new httpconnection.Builder()
                .Url("http://example.com")
                .port("8080")
                .user("kimia")
                .pass("password123")
                .timeout(5000)
                .build();

            System.out.println("Connection successfully created!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
	}

}
