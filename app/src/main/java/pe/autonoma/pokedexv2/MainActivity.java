package pe.autonoma.pokedexv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pe.autonoma.pokedexv2.models.Pokemon;
import pe.autonoma.pokedexv2.models.Pokemons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ListView lvListado;
    Retrofit retrofit;
    PokemonApi pokemonApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListado = findViewById(R.id.lvListado);
        //obtener el adaptador a la ruta de pokeapi
        retrofit = new PokemonAdapter().getAdapter();
        //inicializamos pokemonApi
        pokemonApi=retrofit.create(PokemonApi.class);

        Call<Pokemons> pokemonsCall = pokemonApi.getDataPokemons();

        pokemonsCall.enqueue(new Callback<Pokemons>() {
            @Override
            public void onResponse(Call<Pokemons> call, Response<Pokemons> response) {

                //almaceno los pokemones obtenidos de results
                List<Pokemon> pokemons = response.body().getResults();

                ArrayList<String> stringsList =
                        new ArrayList<>((pokemons.size()));

                for(Pokemon pokemon:pokemons){
                    stringsList.add(pokemon.getName());
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        R.layout.support_simple_spinner_dropdown_item,
                        stringsList);

                //asigno adaptador al listado
                lvListado.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Pokemons> call, Throwable t) {

            }
        });

        lvListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =
                        new Intent(
                                MainActivity.this,
                                PokemonDetalleActivity.class);

                //stringsList con la posicion
                String nombrePoke=lvListado.getItemAtPosition(i).toString();
                //Log.d("Poke",nombrePoke);
                //set extra nombre pokemon
                intent.putExtra("nombre",nombrePoke);

                startActivity(intent);
            }
        });



    }
}
