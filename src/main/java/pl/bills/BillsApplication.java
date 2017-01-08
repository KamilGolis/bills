package pl.bills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.bills.services.IBillsService;

@SpringBootApplication
public class BillsApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BillsApplication.class, args);


		IBillsService billsService = ctx.getBean("billsService", IBillsService.class);
		System.out.println(billsService.getBillsByTitle("Zmywarka"));
//    	  IUserService userService =
//    		  ctx.getBean("userService", IUserService.class);
//    	LoginModel loginModel =new LoginModel();
//    	loginModel.setPassword("dbase123");
//    	loginModel.setUserName("bharat0126");
//    	 System.out.println(userService.getUser(loginModel).toString());

	}
}
