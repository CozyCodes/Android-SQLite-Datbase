package project.cozycodes.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Cozycodes on 3/18/2017.
 */

 public class SqliteRecyclerAdapter extends RecyclerView.Adapter<SqliteRecyclerAdapter.MyViewHolder> {

    private List<SqliteModel> mSqliteBeanses;
    static Context context;
    String u_id;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private EditText  editTextEmail, editTextPhone, editTextAddress;
        private TextView editTextId, editTextName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.editTextId = (TextView) itemView.findViewById(R.id.editTextId);
            this.editTextName = (TextView) itemView.findViewById(R.id.editTextName);
            this.editTextEmail = (EditText) itemView.findViewById(R.id.editTextEmail);
            this.editTextPhone = (EditText) itemView.findViewById(R.id.editTextPhone);
            this.editTextAddress = (EditText) itemView.findViewById(R.id.editTextAddress);

        }

    }

    public SqliteRecyclerAdapter(List<SqliteModel> mSqliteBeanses) {
        this.mSqliteBeanses = mSqliteBeanses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final  SqliteModel sqliteMod = mSqliteBeanses.get(position);
        holder.editTextId.setText(sqliteMod.getId() + ".");
        holder.editTextName.setText(sqliteMod.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MajorActivity.class);
                intent.putExtra("id",sqliteMod.getId());
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), sqliteMod.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       return mSqliteBeanses.size();
    }



}
