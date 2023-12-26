package library.connect;

public interface Lconnect {
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	void connect(String user, String pass);
	void execute();
	void close();
	
}
