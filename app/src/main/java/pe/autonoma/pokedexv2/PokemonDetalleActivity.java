package pe.autonoma.pokedexv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.autonoma.pokedexv2.models.PokemonDetalle;
import pe.autonoma.pokedexv2.models.PokemonDetalleEstadistica;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonDetalleActivity extends AppCompatActivity {
    TextView tvNombre,tvBase,tvAlto,tvPeso,tvId,tvEstadistica;
    ListView lvListadoTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detalle);
        //
        tvId=findViewById(R.id.tvId);
        tvNombre = findViewById(R.id.tvNombre);
        tvBase = findViewById(R.id.tvBase);
        tvAlto= findViewById(R.id.tvAlto);
        tvPeso = findViewById(R.id.tvPeso);
        tvEstadistica=findViewById(R.id.tvEstadistica);
        lvListadoTipo = findViewById(R.id.lvListadoTipo);
        // retroit
        Retrofit retrofit = new PokemonAdapter().getAdapter();
        //instanciamos restClient
        PokemonApi pokemonAPI=retrofit.create(PokemonApi.class);
        //get Extra nombre pokemon y almacenomos en nombrePoke
        final String nombrePoke = getIntent().getStringExtra("nombre");

        Call<PokemonDetalle> pokemonDetalleCall =
                pokemonAPI.getPokemonDetalle(nombrePoke);


        pokemonDetalleCall.enqueue(new Callback<PokemonDetalle>() {
            @Override
            public void onResponse(Call<PokemonDetalle> call, Response<PokemonDetalle> response) {

                tvId.setText("Id Pokemon: " + response.body().getId().toString() );
                tvNombre.setText("Nombre: " + response.body().getName() );
                tvBase.setText("Base: " + response.body().getBase_experience().toString() );
                tvAlto.setText("Alto: " + response.body().getHeight().toString() );
                tvPeso.setText("Peso: " + response.body().getWeight().toString() );
               tvEstadistica.setText("Estadisticas del pokemon "+nombrePoke);
                //almacenamos los datos de estadistica obtenida
               List<PokemonDetalleEstadistica> pokemonDetalleTipos = response.body().getStats();


                ArrayList<String> stringsList =
                        new ArrayList<>((pokemonDetalleTipos.size()));

                for(PokemonDetalleEstadistica type:pokemonDetalleTipos){
                    stringsList.add("Base de Estadistica: "+String.valueOf(type.getBase_stat()));
                    stringsList.add("Esfuerzo: "+String.valueOf(type.getEffort()));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        PokemonDetalleActivity.this,
                        R.layout.support_simple_spinner_dropdown_item,
                        stringsList);

               // asigno adaptador al listado
                lvListadoTipo.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PokemonDetalle> call, Throwable t) {

            }
        });


    }

}
