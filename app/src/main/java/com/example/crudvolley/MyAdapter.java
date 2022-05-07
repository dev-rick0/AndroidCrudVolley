package com.example.crudvolley;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Model> listItems;
    private Context context;
    private ProgressDialog dialog;

    public MyAdapter(List<Model> listItems, Context context){
        this.listItems = listItems;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView gender;
        public TextView fullName;
        public TextView age;
        public TextView email;
        public TextView profession;
        public TextView address;
        public CardView card_view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            gender = (TextView) itemView.findViewById(R.id.gender);
            fullName = (TextView) itemView.findViewById(R.id.fullName);
            age = (TextView) itemView.findViewById(R.id.age);
            email = (TextView) itemView.findViewById(R.id.email);
            profession = (TextView) itemView.findViewById(R.id.profession);
            address = (TextView) itemView.findViewById(R.id.address);
            card_view =(CardView) itemView.findViewById(R.id.card_view);
        }
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final Model listItem = listItems.get(position);
        holder.id.setText(listItem.getId());
        holder.fullName.setText(listItem.getFullName());
        holder.age.setText(listItem.getAge());
        holder.email.setText(listItem.getEmail());
        holder.profession.setText(listItem.getProfession());
        holder.address.setText(listItem.getAddress());


        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final ProgressDialog dialog = new ProgressDialog(view.getContext());
                dialog.setMessage("Loading Delete Data");
                final CharSequence[] dialogitem = {"View Data","Edit Data","Delete Data"};
                builder.setTitle(listItem.getFullName());
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 :
                                Intent intent = new Intent(view.getContext(), DetailData.class);
                                intent.putExtra("id", listItem.getId());
                                intent.putExtra("gender",listItem.getGender());
                                intent.putExtra("fullname",listItem.getFullName());
                                intent.putExtra("age",listItem.getAge());
                                intent.putExtra("email", listItem.getEmail());
                                intent.putExtra("profession", listItem.getProfession());
                                intent.putExtra("address", listItem.getAddress());
                                view.getContext().startActivity(intent);
                                break;
                            case 1 :
                                Intent intent2 = new Intent(view.getContext(), EditActivity.class);
                                intent2.putExtra("id", listItem.getId());
                                intent2.putExtra("gender",listItem.getGender());
                                intent2.putExtra("fullname",listItem.getFullName());
                                intent2.putExtra("age",listItem.getAge());
                                intent2.putExtra("email", listItem.getEmail());
                                intent2.putExtra("profession", listItem.getProfession());
                                intent2.putExtra("address", listItem.getAddress());
                                view.getContext().startActivity(intent2);
                                break;
                            case 2 :

                                AlertDialog.Builder builderDel = new AlertDialog.Builder(view.getContext());
                                builderDel.setTitle(listItem.getFullName());
                                builderDel.setMessage("Are You Sure, You Want to Delete Data?");
                                builderDel.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialog.show();

                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_DELETE, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                dialog.hide();
                                                dialog.dismiss();
                                                Toast.makeText(view.getContext(),"Successfully Deleted Data "+ listItem.getFullName(),Toast.LENGTH_LONG).show();
                                                ListActivity.ma.refresh_list();

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                dialog.hide();
                                                dialog.dismiss();
                                            }
                                        }){
                                            protected HashMap<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> params= new HashMap<>();
                                                params.put("id",listItem.getId());
                                                return (HashMap<String, String>) params;

                                            }
                                        };
                                        RequestHandler.getInstance(view.getContext()).addToRequestQueue(stringRequest);
                                        dialogInterface.dismiss();
                                    }
                                });

                                builderDel.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override

                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });


                                builderDel.create().show();
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}