package api.endpoints;
/*
swagger uri:https://petstore.swagger.io/

create user(post):https://petstore.swagger.io/v2/user
                  |         baseurl             |endpoint|=URL
get user(Get):https://petstore.swagger.io/v2/user/{username}
Update user(put):https://petstore.swagger.io/v2/user/{username}
Delete user(delete):https://petstore.swagger.io/v2/user/{username}
*/
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//Usermodule
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//strore modules
	
	//pet modules
}
