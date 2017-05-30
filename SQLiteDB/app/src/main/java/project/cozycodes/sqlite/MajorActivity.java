package project.cozycodes.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MajorActivity extends AppCompatActivity implements View.OnClickListener{
    String id;
    private SQLiteDatabase db;
    Cursor c;
    private EditText editTextId, editTextName, editTextEmail, editTextPhone, editTextAddress;
    private Button btnSave, btnDelete;
    private static final String DELETE_SQL ="SELECT * FROM contents";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);
        openDatabase();
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        id= extras.getString("id");
//        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        displayRecords();

    }

    public void openDatabase(){
        db = openOrCreateDatabase("SampleDb", Context.MODE_PRIVATE, null);
    }

    public void displayRecords(){
         c = db.rawQuery("SELECT * FROM contents WHERE id ="+ id, null);
        c.moveToFirst();
        String id = c.getString(0);
        String name = c.getString(1);
        String email = c.getString(2);
        String phone = c.getString(3);
        String address = c.getString(4);

        editTextId.setText(id);
        editTextName.setText(name);
        editTextEmail.setText(email);
        editTextPhone.setText(phone);
        editTextAddress.setText(address);
    }

    public void updateRecord(){

        String id = editTextId.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if(name.equals("") ||  email.equals("") || phone.equals("") ||address.equals("")){
            Toast.makeText(getApplicationContext(), "Fill All Fields" , Toast.LENGTH_LONG).show();
        }
        else {
            String updateSql = "UPDATE contents SET name ='" + name + "', email='" + email + "', phone='" + phone + "', address='" + address + "' WHERE id='" + id + "'";
            db.execSQL(updateSql);
            finish();
        }
    }


    private void deleteRecord() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want delete this person?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String id = editTextId.getText().toString().trim();

                        String sql = "DELETE FROM contents WHERE id=" + id + ";";
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();

                        finish();


                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onClick(View v) {
        if(v == btnSave){
            updateRecord();
        }
        else if(v == btnDelete){
            deleteRecord();
        }
    }

    @Override
    public void onBackPressed() {
       finish();
    }
}
