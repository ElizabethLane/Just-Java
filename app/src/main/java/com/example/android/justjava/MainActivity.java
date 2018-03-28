/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

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

        boolean hasWhip = whippedCream.isChecked();
        int price = calculatePrice();
        String message = createOrderSummary(price, hasWhip);
        displayMessage(message);
    }

    /**
     * This method displays the order summary
     */

    public String createOrderSummary(int price, boolean whip){
        return "Name:Kaptain Kunal" + "\nQuantity:" + quantity + "\nTotal:" + price +
                "\nThank you!" + "\nHas whipped cream" + whip;
    }

    /**
     * Calculates the price of the order.
     * @return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method is called when the + button is clocked.
     */
    public void increment(View view){
        quantity += 2;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view){
        quantity = quantity - 1;
        displayQuantity(quantity);
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