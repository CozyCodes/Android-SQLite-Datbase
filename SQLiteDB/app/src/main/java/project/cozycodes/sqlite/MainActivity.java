package project.cozycodes.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd, btnView;
    private EditText editTextName, editTextEmail, editTextPhone, editTextAddress;
    private SQLiteDatabase db;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDatabase();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    public void createDatabase(){
        db = openOrCreateDatabase("SampleDb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS contents(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, email VARCHAR, phone VARCHAR, address VARCHAR)");
    }

    public void insertDatabase(){
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if(name.equals("") ||  email.equals("") || phone.equals("") ||address.equals("")){
            Toast.makeText(getApplicationContext(), "Fill All Fields" , Toast.LENGTH_LONG).show();
        }
        else {
            String query = "INSERT INTO contents (name, email, phone, address) VALUES ('" + name + "', '" + email + "', '" + phone + "', '" + address + "');";
            db.execSQL(query);
            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();

            editTextName.setText("");
            editTextEmail.setText("");
            editTextPhone.setText("");
            editTextAddress.setText("");
        }
    }

    @Override
    public void onClick(View v) {

        if(v == btnAdd){
            insertDatabase();
//            Toast.makeText(getApplicationContext(), "btnAdd" , Toast.LENGTH_LONG).show();
        }
        if(v == btnView){
         Intent intent = new Intent(MainActivity.this, ViewDataRecyc.class);
            startActivity(intent);
        }

    }


}
