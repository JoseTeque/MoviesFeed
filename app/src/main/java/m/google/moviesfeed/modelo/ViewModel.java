package m.google.moviesfeed.modelo;

public class ViewModel {

    private String name;
    private String pais;

    public ViewModel() {
    }

    public ViewModel(String name, String pais) {
        this.name = name;
        this.pais = pais;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
