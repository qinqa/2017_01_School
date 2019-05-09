
package koe_2017_tehtava_1;

public class Ufo {
    private String paiva;
    private String paikka;
    private String osavaltio;
    private String maa;
    private String muoto;
    private Double kesto;
    private Double pituus;
    private Double leveys;
    private String kuvaus;
    
    public Ufo(String paiva, String paikka, String osavaltio, String maa, String muoto, Double kesto, Double pituus, Double leveys, String kuvaus) {
        this.paiva=paiva;
        this.paikka=paikka;
        this.osavaltio=osavaltio;
        this.maa=maa;
        this.muoto=muoto;
        this.kesto=kesto;
        this.pituus=pituus;
        this.leveys=leveys;
        this.kuvaus=kuvaus;
    }
    
    public String getPaiva() {
        return this.paiva;
    }
    
    public String getMuoto() {
        return this.muoto;
    }
    
    public Double getKesto() {
        return this.kesto;
    }
    
    public String getKuvaus() {
        return this.kuvaus;
    }
}
