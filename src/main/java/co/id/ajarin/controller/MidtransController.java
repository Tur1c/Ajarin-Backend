package co.id.ajarin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midtrans.Midtrans;
import com.midtrans.httpclient.SnapApi;
import com.midtrans.httpclient.error.MidtransError;

@RestController
@RequestMapping("/api/payment")
public class MidtransController {

    private String serverKey = "SB-Mid-server-si2zmE8KK3eYQ0Ym7dnY62mk";
    
    @SuppressWarnings("rawtypes")
    @PostMapping("")
    public ResponseEntity payment(@RequestParam(name = "price")     String price) throws MidtransError {

        Midtrans.serverKey = serverKey;

        Midtrans.isProduction = false;

        UUID idRand = UUID.randomUUID();
        Map<String, Object> params = new HashMap<>();

        Map<String, String> transactionDetails = new HashMap<>();
        transactionDetails.put("order_id", idRand.toString());
        transactionDetails.put("gross_amount", price);

        Map<String, String> creditCard = new HashMap<>();
        creditCard.put("secure", "true");

        params.put("transaction_details", transactionDetails);
        params.put("credit_card", creditCard);

        String transactionToken = SnapApi.createTransactionToken(params);

        return ResponseEntity.status(HttpStatus.OK).body(transactionToken);
    }
}
