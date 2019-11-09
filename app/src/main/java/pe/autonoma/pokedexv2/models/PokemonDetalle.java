package pe.autonoma.pokedexv2.models;

import java.util.List;

public class PokemonDetalle {
    private Integer id;
    private String name;
    private Integer base_experience;
    private Integer height;
    private Integer weight;
    private List<PokemonDetalleEstadistica> stats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<PokemonDetalleEstadistica> getStats() {
        return stats;
    }

    public void setStats(List<PokemonDetalleEstadistica> stats) {
        this.stats = stats;
    }
}
