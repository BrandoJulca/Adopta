package com.example.consumoapirest;

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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<UResponse> {

    int currentRow = 0;
    private RecyclerView recyclerView;
    private ArrayList<UniversidadE> arregloMain;
    private MyLocalAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Instancias del RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /// Llamada al servicio mediante Retrofit
        Call<UResponse> call = MyAPIAdapter.getApiService().getUniversidades();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UResponse> call, Response<UResponse> response) {
        if (response.isSuccessful()){
            UResponse respuesta = response.body();
            Log.d("onResponse RETRO", "Tamaño del array => "+ respuesta.getUniversidades().size());

            List<Universidad> universidades = respuesta.getUniversidades();
            ArrayList<UniversidadE> lista = new ArrayList<>();
            Integer i=1;
            for (Universidad universidad: universidades) {
                lista.add( new UniversidadE(i, universidad.getNombre().toString(),
                        universidad.getDireccion().toString(), universidad.getDistrito().toString()));
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
    public void onFailure(Call<UResponse> call, Throwable t) {

    }

    class MyLocalAdapter extends RecyclerView.Adapter<MyLocalAdapter.LocalViewHolder> {

        private Context context;
        private List<UniversidadE> data;

        public MyLocalAdapter(Context context, ArrayList<UniversidadE> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public MyLocalAdapter.LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_universidades, null);
            MyLocalAdapter.LocalViewHolder customViewHolder = new MyLocalAdapter.LocalViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(MyLocalAdapter.LocalViewHolder holder, int position) {

            UniversidadE fila = data.get(position);
            holder.labelNombre.setText(fila.getNombre());
            holder.labelDireccion.setText(fila.getDireccion());
            holder.labelDistrito.setText(fila.getDistrito());

            if(fila.getDistrito().trim().length()==0)
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
            protected TextView labelNombre;
            protected TextView labelDireccion, labelDistrito;
            protected ImageView iconoTelefono;

            public LocalViewHolder(View itemView) {
                super(itemView);
                this.labelNombre = (TextView) itemView.findViewById(R.id.nombre);
                this.labelDireccion = (TextView) itemView.findViewById(R.id.direccion);
                this.labelDistrito = (TextView) itemView.findViewById(R.id.distrito);
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
                UniversidadE objE = data.get(currentRow);
                Toast.makeText(getApplicationContext(), objE.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(View v) {
                currentRow = getAdapterPosition();
                if(currentRow < 0)
                    currentRow = 0;
                UniversidadE objE = data.get(currentRow);

                /// Llamar por telefono
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", objE.getDistrito(), null));
                startActivity(intent);

                return false;
            }
        }
    }
}
