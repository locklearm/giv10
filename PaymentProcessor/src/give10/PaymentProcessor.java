/**
 * 
 */
package give10;

import com.simplify.payments.PaymentsApi;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.Payment;
import com.simplify.payments.exception.ApiCommunicationException;
import com.simplify.payments.exception.AuthenticationException;
import com.simplify.payments.exception.InvalidRequestException;
import com.simplify.payments.exception.NotAllowedException;
import com.simplify.payments.exception.SystemException;

/**
 * @author Martin Locklear (locklear.martin@gmail.com)
 *
 */
public class PaymentProcessor {
	
	public PaymentProcessor() {

		PaymentsApi.PUBLIC_KEY = "sbpb_ZjlmNmY0NTYtYjkxNi00MGU4LWFmOWMtYTAxYjU5Y2U5YjBk";
		PaymentsApi.PRIVATE_KEY = "zp96oMcWFdFlfD3pVn5q64zJG6iZVQFPbb9Z9pQ9jst5YFFQL0ODSXAOkNtXTToq";
		
	}
	
	public boolean processPayment(User u) {

//		PaymentsApi.PUBLIC_KEY = "sbpb_ZjlmNmY0NTYtYjkxNi00MGU4LWFmOWMtYTAxYjU5Y2U5YjBk";
//		PaymentsApi.PRIVATE_KEY = "zp96oMcWFdFlfD3pVn5q64zJG6iZVQFPbb9Z9pQ9jst5YFFQL0ODSXAOkNtXTToq";
		
		Payment payment = null;
		try {
			payment = Payment.create(new PaymentsMap()
				    .set("currency", "USD")
				    .set("token", u.customerid)
				    .set("amount", 1000) // In cents e.g. $10.00
				    .set("description", "giv10 Payment"));
			
			if ("APPROVED".equals(payment.get("paymentStatus"))) {
			    System.out.println("Payment approved");
			    return true;
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: Problem processing payment");
			return false;
		}
		
		return false;
	}
	
}
