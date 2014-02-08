package zbynek.remotehouseholdcontrol.nettools;

import android.content.Context;
import android.content.SharedPreferences;

public class ConnectionCredentialsManager {

	private static final String HTTP_PROTOCOL = "http";
	
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String DIR = "dir";
	private static final String PORT = "port";
	private static final String DOMAIN = "domain";

	private SharedPreferences sharedPref;
	
	public ConnectionCredentialsManager(Context context) {
		sharedPref = context.getSharedPreferences("ConnectionPreferences", Context.MODE_PRIVATE);
	}
	
	public void saveCredentials(String domain, String port, String dir, String username, String password) {
		
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(DOMAIN, domain);
		editor.putString(PORT, port);
		editor.putString(DIR, dir);
		editor.putString(USERNAME, username);
		editor.putString(PASSWORD, password);
		editor.commit();	
	}
	
	public String getDomain() {
		return sharedPref.getString(DOMAIN, "192.168.111.1");		
	}
	
	public String getPort() {
		return sharedPref.getString(PORT, "80");		
	}

	public String getDir() {
		return sharedPref.getString(DIR, "");		
	}

	public String getUsername() {
		return sharedPref.getString(USERNAME, "");		
	}

	public String getPassword() {
		return sharedPref.getString(PASSWORD, "");		
	}
	
	public String constructUrl(String file) {
		return HTTP_PROTOCOL+"://"+getDomain()+":"+getPort()+"/"+getDir()+"/"+file;
		
	}

}
