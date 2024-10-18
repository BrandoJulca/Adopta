package com.example.consumoapirest.UserDATA;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consumoapirest.R;
import com.example.consumoapirest.UResponse;
import com.example.consumoapirest.UserDATA.USEResponse;
import com.example.consumoapirest.UserDATA.User;
import com.example.consumoapirest.UserDATA.UserE;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecActivity extends AppCompatActivity implements Callback<USEResponse> {

    int currentRow = 0;
    private RecyclerView recyclerView;
    private ArrayList<UserE> arregloMain;
    private MyLocalAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_activity);

        /// Instancias del RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /// Llamada al servicio mediante Retrofit
        Call<USEResponse> call = MyAPIAdapter.getApiService().getUsers();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<USEResponse> call, Response<USEResponse> response) {
        if (response.isSuccessful()){
            USEResponse respuesta = response.body();
            Log.d("onResponse RETRO", "Tamaño del array => "+ respuesta.getUsers().size());

            List<User> Users = respuesta.getUsers();
            ArrayList<UserE> lista = new ArrayList<>();
            Integer i=1;
            for (User User: Users) {
                lista.add( new UserE(i, User.getCorreo().toString(),
                        User.getPass().toString(), User.getUsuario().toString(), User.getNumeroContacto()));
                i=i+1;
            }

            if(arregloMain!=null)
                arregloMain.clear();
            arregloMain = lista;
            listAdapter = new MyLocalAdapter(this, arregloMain);
            recyclerView.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<USEResponse> call, Throwable t) {

    }

    class MyLocalAdapter extends RecyclerView.Adapter<MyLocalAdapter.LocalViewHolder> {

        private Context context;
        private List<UserE> data;

        public MyLocalAdapter(Context context, ArrayList<UserE> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_user, null);
            LocalViewHolder customViewHolder = new LocalViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(LocalViewHolder holder, int position) {

            UserE fila = data.get(position);
            holder.labelCorreo.setText(fila.getCorreo());
            holder.labelPass.setText(fila.getPass());
            holder.labelUsuario.setText(fila.getUsuario());
            holder.labelNumeroContacto.setText(fila.getNumeroContacto());

            if(fila.getPass().trim().length()==0)
                holder.iconoTelefono.setVisibility(View.GONE);
            else
                holder.iconoTelefono.setVisibility(View.VISIBLE);
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        class LocalViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener, View.OnLongClickListener {

            protected View rowView;
            protected TextView labelCorreo;
            protected TextView labelPass, labelUsuario;
            protected ImageView iconoTelefono;
            protected TextView labelNumeroContacto;

            public LocalViewHolder(View itemView) {
                super(itemView);
                this.labelCorreo = (TextView) itemView.findViewById(R.id.Correo);
                this.labelPass = (TextView) itemView.findViewById(R.id.Pass);
                this.labelUsuario = (TextView) itemView.findViewById(R.id.User);
                this.labelNumeroContacto = (TextView) itemView.findViewById(R.id.NumeroContacto);
                this.iconoTelefono = (ImageView) itemView.findViewById(R.id.iconotelefono);
                itemView.setOnClickListener(this);
                this.iconoTelefono.setOnLongClickListener(this);
                this.rowView = itemView;
            }

            @Override
            public void onClick(View v) {
                currentRow = getAdapterPosition();
                if(currentRow < 0)
                    currentRow = 0;
                UserE objE = data.get(currentRow);
                Toast.makeText(getApplicationContext(), objE.getCorreo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(View v) {
                currentRow = getAdapterPosition();
                if(currentRow < 0)
                    currentRow = 0;
                UserE objE = data.get(currentRow);

                /// Llamar por telefono
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", objE.getUsuario(), null));
                startActivity(intent);

                return false;
            }
        }
    }
}
