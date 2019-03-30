/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBox.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();

        int price1 = calculatePrice(hasWhippedCream, hasChocolate);
        String s = createOrderSummery(hasWhippedCream, hasChocolate, name);
        String priceMessage = s + "Total: $" + price1 + "\nThankYou!";


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java coffee order for"+name);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }



    }

    public String createOrderSummery(boolean addWhippedCream, boolean addChocolate, String name) {
        String s = "Name :" + name + "\n" + "Add whipped Cream? " + addWhippedCream + "\n"
                + "Add Chocolate? " + addChocolate + "\n" + "Quantity :" + quantity + "\n";
        return s;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return basePrice * quantity;
    }

    public void increment(View view) {
        if(quantity ==100){
            Toast.makeText(this,"You cannot order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;}

        quantity = quantity + 1;
        display(quantity);

    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this,"You cannot order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;}

            quantity = quantity - 1;
            display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method displays the given text on the screen.
     */


}

