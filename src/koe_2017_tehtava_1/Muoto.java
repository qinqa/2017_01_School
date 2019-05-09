
package koe_2017_tehtava_1;

import java.util.Comparator;

public class Muoto{
    private String nimi;
    private Integer lyhyt;
    private Integer keski;
    private Integer pitka;
    
    public Muoto(String nimi, Integer lyhyt, Integer keski, Integer pitka) {
        this.nimi=nimi;
        this.lyhyt=lyhyt;
        this.keski=keski;
        this.pitka=pitka;
    }
    
    public void setLyhyt() {
        this.lyhyt = lyhyt +1;
    }
    
    public void setKeski() {
        this.keski = keski +1;
    }
    
    public void setPitka() {
        this.pitka = pitka +1;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public Integer getLyhyt() {
        return this.lyhyt;
    }
    
    public Integer getKeski() {
        return this.keski;
    }
    
    public Integer getPitka() {
        return this.pitka;
    }
}
