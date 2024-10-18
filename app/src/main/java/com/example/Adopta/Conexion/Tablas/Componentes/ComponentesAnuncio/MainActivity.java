package com.example.Adopta.Conexion.Tablas.Componentes.ComponentesAnuncio;

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
import com.example.Adopta.Conexion.Tablas.Componentes.ComponentesAnuncio.UResponse;

import com.example.Adopta.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<UResponse> {

    int currentRow = 0;
    private RecyclerView recyclerView;
    private ArrayList<AnuncioE> arregloMain;
    private MyLocalAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);

        Log.d("MainActivity", "Inicializando la actividad y configurando el RecyclerView.");
        // Instancias del RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Llamada al servicio mediante Retrofit
        Log.d("MainActivity", "Llamando al servicio para obtener usuarios.");
        Call<UResponse> call = MyAPIAdapter.getApiService().getAnuncios();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UResponse> call, Response<UResponse> response) {
        if (response.isSuccessful()) {
            Log.d("MainActivity", "Respuesta exitosa de la API.");
            UResponse respuesta = response.body();

            if (respuesta != null) {
                // Comprobar si la lista de usuarios no es nula
                List<Anuncio> anuncios = respuesta.getAnuncios();
                if (anuncios != null) {
                    Log.d("MainActivity", "Tamaño del array => " + anuncios.size());
                    ArrayList<AnuncioE> lista = new ArrayList<>();
                    Integer i = 1;

                    // Comprobar si la lista de usuarios no está vacía
                    if (!anuncios.isEmpty()) {
                        for (Anuncio anuncio : anuncios) {
                            lista.add(new AnuncioE(i,
                                    anuncio.getNombre(),
                                    anuncio.getEspecie(),
                                    anuncio.getEdad(),
                                    anuncio.getUnidadEdad(),
                                    anuncio.getTamaño(),
                                    anuncio.getPeso(),
                                    anuncio.getUnidadPeso(),
                                    anuncio.getCaracter(),
                                    anuncio.getUbicacion(),
                                    anuncio.getUsuario(),
                                    anuncio.getFoto()));
                            Log.d("MainActivity", "Anuncio añadido al array: " + anuncio.getNombre());
                            i = i + 1;
                        }
                    } else {
                        Log.d("MainActivity", "La lista de usuarios está vacía.");
                    }

                    if (arregloMain != null) {
                        arregloMain.clear();
                        Log.d("MainActivity", "arregloMain limpiado.");
                    }
                    arregloMain = lista;
                    Log.d("MainActivity", "Asignando la lista de anuncios al adaptador.");
                    listAdapter = new MyLocalAdapter(this, arregloMain);
                    recyclerView.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                    Log.d("MainActivity", "Datos del adaptador actualizados.");
                } else {
                    Log.d("MainActivity", "La lista de usuarios es nula.");
                }
            } else {
                Log.d("MainActivity", "La respuesta de la API es nula.");
            }
        } else {
            Log.d("MainActivity", "Respuesta fallida de la API: " + response.code());
        }
    }


    @Override
    public void onFailure(Call<UResponse> call, Throwable t) {
        Log.d("MainActivity", "Fallo en la llamada a la API: " + t.getMessage());
    }

    class MyLocalAdapter extends RecyclerView.Adapter<MyLocalAdapter.LocalViewHolder> {

        private Context context;
        private List<AnuncioE> data;

        public MyLocalAdapter(Context context, ArrayList<AnuncioE> data) {
            this.context = context;
            this.data = data;
        }

        // AQUI DEFINIMOS LA VISTA EN LOS LAYOUTS

        @Override
        public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_anuncios, null);
            LocalViewHolder customViewHolder = new LocalViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(LocalViewHolder holder, int position) {
            AnuncioE fila = data.get(position);

            Log.d("MyLocalAdapter", "Posición: " + position);
            Log.d("MyLocalAdapter", "Nombre: " + fila.getNombre());
            Log.d("MyLocalAdapter", "Ubicación: " + fila.getUbicacion());
            Log.d("MyLocalAdapter", "Carácter: " + fila.getCaracter());

            holder.labelNombre.setText(fila.getNombre());
            holder.labelEspecie.setText(fila.getEspecie());
            holder.labelEdad.setText(String.valueOf(fila.getEdad()) + " " + fila.getUnidadEdad());
            holder.labelTamaño.setText(fila.getTamaño());
            holder.labelPeso.setText(String.valueOf(fila.getPeso()) + " " + fila.getUnidadPeso());
            holder.labelCaracter.setText(fila.getCaracter());
            holder.labelUbicacion.setText(fila.getUbicacion());
            holder.labelUsuario.setText(fila.getUsuario());

            // Cargar la foto si es necesario
            // Glide.with(holder.itemView.getContext()).load(fila.getFoto()).into(holder.imageViewFoto);

            Log.d("MyLocalAdapter", "Datos del anuncio en posición " + position + " vinculados al ViewHolder.");
        }



        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        class LocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            protected View rowView;
            protected TextView labelNombre;
            protected TextView labelEspecie;
            protected TextView labelEdad;
            protected TextView labelTamaño;
            protected TextView labelPeso;
            protected TextView labelCaracter;
            protected TextView labelUbicacion;
            protected TextView labelUsuario;
            protected ImageView imageViewFoto;

            public LocalViewHolder(View itemView) {
                super(itemView);
                this.labelNombre = (TextView) itemView.findViewById(R.id.nombre);
                this.labelEspecie = (TextView) itemView.findViewById(R.id.especie);
                this.labelEdad = (TextView) itemView.findViewById(R.id.edad);
                this.labelTamaño = (TextView) itemView.findViewById(R.id.tamaño);
                this.labelPeso = (TextView) itemView.findViewById(R.id.peso);
                this.labelCaracter = (TextView) itemView.findViewById(R.id.caracter);
                this.labelUbicacion = (TextView) itemView.findViewById(R.id.ubicacion);
                this.labelUsuario = (TextView) itemView.findViewById(R.id.usuario);
                this.imageViewFoto = (ImageView) itemView.findViewById(R.id.foto);
                this.rowView = itemView;
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View v) {
                currentRow = getAdapterPosition();
                if (currentRow < 0) currentRow = 0;
                AnuncioE objE = data.get(currentRow);
                Toast.makeText(getApplicationContext(), objE.getNombre(), Toast.LENGTH_SHORT).show();
                Log.d("LocalViewHolder", "Click en el nombre: " + objE.getNombre());
            }

            @Override
            public boolean onLongClick(View v) {
                currentRow = getAdapterPosition();
                if (currentRow < 0) currentRow = 0;
                AnuncioE objE = data.get(currentRow);
                Log.d("LocalViewHolder", "Long click en el nombre: " + objE.getNombre());

                // Llamar por telefono
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", objE.getUsuario(), null));
                startActivity(intent);
                Log.d("LocalViewHolder", "Iniciando llamada al número: " + objE.getUsuario());

                return false;
            }
        }
    }
}

