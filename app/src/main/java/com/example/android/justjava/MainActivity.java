/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        EditText name = (EditText) findViewById(R.id.search);

        boolean hasWhip = whippedCream.isChecked();
        boolean hasChoc = chocolate.isChecked();
        int price = calculatePrice(hasWhip, hasChoc);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, hasWhip, hasChoc, name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        }

//        String message = createOrderSummary(price, hasWhip, hasChoc, userName);
//        displayMessage(message);
    }

    /**
     * This method displays the order summary
     */

    public String createOrderSummary(int price, boolean whip, boolean choc, String userName){
        return "Name:" + userName + "\nQuantity:" + quantity + "\nTotal:" + price +
                "\nThank you!" + "\nHas whipped cream" + whip + "\nHas chocolate" + choc;
    }

    /**
     * Calculates the price of the order.
     * @return total price
     */
    private int calculatePrice(boolean whip, boolean choc) {
        int price = 5;

        if (whip){
            price += 1;
        }
        if (choc){
            price += 2;
        }

        price = price * 5;
        return price;
    }

    /**
     * This method is called when the + button is clocked.
     */
    public void increment(View view){
        if (quantity == 100){

            Context context = getApplicationContext();
            CharSequence text = "Cannot order more than 100 coffees!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            displayQuantity(quantity);

        }
        else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view){
        if (quantity == 1){

            Context context = getApplicationContext();
            CharSequence text = "Cannot order less than 1 coffee!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            displayQuantity(quantity);

        }
        else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}