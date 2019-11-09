package pe.autonoma.pokedexv2;

import pe.autonoma.pokedexv2.models.PokemonDetalle;
import pe.autonoma.pokedexv2.models.Pokemons;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {
    //get pokemon
    @GET("pokemon")
    Call<Pokemons> getDataPokemons();

    //get pokemon por nombre
    @GET("pokemon/{nombre}")
    Call<PokemonDetalle> getPokemonDetalle(@Path("nombre") String nombre);

    //get name types pokemon
    //@GET("pokemon/{nombre}/")
  //  Call<PokemonDetalle> getPokemonDetalle(@Path("nombre") String nombre);
}

