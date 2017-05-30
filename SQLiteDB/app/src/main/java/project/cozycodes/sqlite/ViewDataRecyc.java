package project.cozycodes.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ViewDataRecyc extends AppCompatActivity {
    private EditText editTextId, editTextName, editTextEmail, editTextPhone, editTextAddress;
    private SQLiteDatabase db;
    private static String SELECT_TABLE= "SELECT * from contents";
    private LinearLayout layoutrecylerflex;
    private RecyclerView recycler_flex;
    SqliteRecyclerAdapter sqliteRecycler;
    private ArrayList<SqliteModel> msqlitebeans = new ArrayList<>();
    private Cursor c;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        openDatabase();

        this.recycler_flex = (RecyclerView) findViewById(R.id.recycler_flexible);


        recycler_flex.setHasFixedSize(true);
        LinearLayoutManager mLayoutManagerOne = new LinearLayoutManager(this);

        mLayoutManagerOne.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_flex.addItemDecoration(new project.cozycodes.sqlite.DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recycler_flex.setItemAnimator(new DefaultItemAnimator());
        recycler_flex.setLayoutManager(mLayoutManagerOne);
        recycler_flex.setAdapter(sqliteRecycler);


        c = db.rawQuery(SELECT_TABLE, null);
        c.moveToFirst();
        showRecords();
//        updateFunCreationRecycle();

    }

    public void openDatabase(){
        db = openOrCreateDatabase("SampleDb", Context.MODE_PRIVATE, null);
    }

    public  void showRecords(){

        if (c.moveToFirst()){
            do {
                SqliteModel sqlitebeans_child = new SqliteModel();
                sqlitebeans_child.setId(c.getString(0));
                sqlitebeans_child.setName(c.getString(1));
                sqlitebeans_child.setEmail(c.getString(2));
                sqlitebeans_child.setPhone(c.getString(3));
                sqlitebeans_child.setAddress(c.getString(4));
                msqlitebeans.add(sqlitebeans_child);

            }
            while (c.moveToNext());
            callAdapterFlex();
        }

    }


    private void callAdapterFlex() {
        sqliteRecycler = new SqliteRecyclerAdapter(msqlitebeans);
        recycler_flex.setAdapter(sqliteRecycler);
    }


/* To Add Dummy values to the view holder*/
    private void updateFunCreationRecycle() {
        SqliteModel sqlBeans = new SqliteModel("1", "32", "16","Flexible","wddf");
        msqlitebeans.add(sqlBeans);

        sqlBeans = new SqliteModel("1", "32", "16","Flexible","wddf");
        msqlitebeans.add(sqlBeans);

    }
}
