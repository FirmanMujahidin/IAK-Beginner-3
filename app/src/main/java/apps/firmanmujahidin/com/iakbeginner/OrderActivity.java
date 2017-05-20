package apps.firmanmujahidin.com.iakbeginner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    EditText mTextNama;
    TextView mTextHarga,mTextQty;
    Button mButtonOrder,mButtonPlus,mButtonMin;
    String item = null;
    // spinner
    Spinner mSpinnerMenu;
    List<String> mListMenu = new ArrayList<>();
    int harga = 0;
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);

        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("-------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok Bakar");
        mListMenu.add("Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);
    }

    public void onClickOrder(View view){
        String to = "firmanmujahidin@gmail.com";
        String subject = mTextNama.getText().toString();
        String message = "Saya pesan "
                + mSpinnerMenu.getSelectedItem()
                + " sebanyak "
                + mTextQty.getText()
                + "buah, seharga "
                + mTextHarga.getText()
                + ", dengan topping "
                + item;

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Kirim email dengan"));
    }

    public void onClickPlus(View view){
        harga = harga + 5;
        qty = qty + 1;
        mTextQty.setText(qty+"");
        mTextHarga.setText("$"+harga);
    }

    public void onClickMin(View view){
        if(harga != 0) {
            harga = harga - 5;
            qty = qty - 1;
            mTextQty.setText(qty+"");
            mTextHarga.setText("$"+harga);
        }
        else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty+"");
            mTextHarga.setText("$"+harga);
        }
    }

    public void onClickReset(View v){
        harga = 0;
        qty = 0;
        mTextNama.setText("");
        mTextHarga.setText("$"+harga);
        mTextQty.setText(qty+"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
