package anshul.software_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class PayPalReg extends AppCompatActivity {

    TextView tvResponse;

    PayPalConfiguration pcConfiguration ;
    String paypalClientId = "AfAtkWltfT66EFYY6-UXiUak5nfWgK_dEjVyPTQ0-dswUMHkh-yYPyzwX02YzdxHa6NCXm5II9o4CGkz";
    Intent service;
    int paypalRequestCode = 999;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.activity_paypalreg);

        tvResponse = (TextView) findViewById(R.id.respose);
        pcConfiguration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(paypalClientId);

        service = new Intent(getBaseContext(), PayPalService.class);

        service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,pcConfiguration);


        startService(service);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == paypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirm != null)
                {
                    String state = confirm.getProofOfPayment().getState();

                    if(state.equals("approved"))
                        tvResponse.setText("payment approved");
                    else
                        tvResponse.setText("error in payment");
                }
                else
                    tvResponse.setText("Confirmation is null");
            }
        }
    }

    public void pay(View view) {
        PayPalPayment payment = new PayPalPayment(new BigDecimal(10),"USD","Test paypal",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getBaseContext(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,pcConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,paypalRequestCode);
    }
}

