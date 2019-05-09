
package koe_2017_tehtava_1;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class Koe_2017_tehtava_1 {

    Scanner lukija = new Scanner(System.in);
    List<Ufo> ufoLista = new ArrayList<>();
    
    public static void main(String[] args) {
        
        new Koe_2017_tehtava_1().ohjelma();
    }
    
    
    
    public void ohjelma() {
    
        System.out.print("tiedosto: ");
        String nimi = lukija.nextLine();
        
        try(Scanner tiedostonLukija = new Scanner(new File(nimi))) {
            
            while(tiedostonLukija.hasNextLine()) {
                String rivi = tiedostonLukija.nextLine().toLowerCase();
                String[] osa = rivi.split(";");
                
                Ufo ufo = new Ufo(osa[0], osa[1], osa[2], osa[3], osa[4], Double.valueOf(osa[5]), Double.valueOf(osa[6]), Double.valueOf(osa[7]), osa[8]);
                
                ufoLista.add(ufo);
            }
            
            //Luodaan metodit tulostuksille:
            
            havaintoja();
            esineidenMuodot();
            havainnotVuosittain();
            havainnotTunneittain();
            muodotKestonMukaan();
            tunnitHavainnonMukaan();
            yleisimmatSanat();
            yleisimmatSanaParit();
            
        } catch(Exception e) {
            System.out.println("tiedostoa ei voi avata");
        }
    }
    
    public void havaintoja() {
        
        Double lyhin = this.ufoLista.get(0).getKesto();
        Double pisin = this.ufoLista.get(0).getKesto();
        Double keski = this.ufoLista.get(0).getKesto();
        for(int i=1;i<ufoLista.size();i++) {
            if(this.ufoLista.get(i).getKesto()<lyhin) {
                lyhin = this.ufoLista.get(i).getKesto();
            }
            if(this.ufoLista.get(i).getKesto()>pisin) {
                pisin = this.ufoLista.get(i).getKesto();
            }
            keski = keski + this.ufoLista.get(i).getKesto();
        }
        
        System.out.println("\nHavaintoja: " + ufoLista.size());
        System.out.println("Lyhimmän havainnon kesto sekunteina: " + lyhin);
        System.out.println("Pisimmän havainnon kesto sekunteina: " + pisin);
        System.out.println("Keskimääräinen havainnon kesto sekunteina: " + keski/ufoLista.size());
    }
    
    public void esineidenMuodot() {
        
        TreeMap<String, Integer> muotoLista = new TreeMap<>();
        
        for(int i=0;i<ufoLista.size();i++) {
            String muoto = this.ufoLista.get(i).getMuoto();
            if(!muotoLista.containsKey(muoto)) {
                muotoLista.put(muoto , 1);
            } else {
                int arvo = muotoLista.get(muoto).intValue();
                arvo++;
                muotoLista.put(muoto, arvo);
            }
        }
        
        System.out.println("\nEsineiden muodot");
        int suurin = 0;
        String suurinMuoto = "";
        
        for(String muoto : muotoLista.keySet()) {
            int arvo = muotoLista.get(muoto);
            String tulostus = muoto + ", havaintoja: " + arvo;
            
            if(arvo > suurin) {
                suurin = arvo;
                suurinMuoto = muoto;
            }
            System.out.println("      " + tulostus);
        }
        
        System.out.println("Eniten havaintoja: " + suurinMuoto + ", " + suurin + " kertaa");  
    }
    
    public void havainnotVuosittain() {
        TreeMap<String, Integer> muotoLista = new TreeMap<>();       
        
        for(int i=0;i<ufoLista.size();i++) {
            String muoto = "";
            String temp = this.ufoLista.get(i).getPaiva();
            String[] temp1 = temp.split("/");
            for(int j=0;j<temp1[2].length();j++) {
                if(j<=4) {
                    muoto = muoto + temp1[2].charAt(j);
                }
            }
            if(!muotoLista.containsKey(muoto)) {
                muotoLista.put(muoto , 1);
            } else {
                int arvo = muotoLista.get(muoto).intValue();
                arvo++;
                muotoLista.put(muoto, arvo);
            }
        }
        
        System.out.println("\nHavainnot vuosittain");
        for(String muoto : muotoLista.keySet()) {
            int arvo = muotoLista.get(muoto);
            String tulostus = muoto + ", havaintoja: " + arvo;
            System.out.println("      " + tulostus);
        }
    }
    
    public void havainnotTunneittain() {
        TreeMap<Integer, Integer> muotoLista = new TreeMap<>();
        List<Integer> aikaLista = new ArrayList<>();
        
        for(int i=0;i<ufoLista.size();i++) {
            String temp = this.ufoLista.get(i).getPaiva();
            String[] temp1 = temp.split("[ :/]");
            Integer aika = Integer.valueOf(temp1[3]);
            aikaLista.add(aika);
            if(!muotoLista.containsKey(aika)) {
                muotoLista.put(aika, 1);
            } else {
                int value = muotoLista.get(aika);
                value++;
                muotoLista.put(aika, value);
            }
        }
        
        System.out.println("\nHavainnot tunneittain");
        int kaikki = 0;
        for(Integer aika : muotoLista.keySet()) {
            int arvo = muotoLista.get(aika);
            kaikki = kaikki + arvo;
        }
        for(Integer aika : muotoLista.keySet()) {
            
            int arvo = muotoLista.get(aika);
           
            String tulostus = aika + "-" + (aika+1) + ", havaintoja " + arvo + ", " + (arvo/kaikki)*100;
            System.out.println("      " + tulostus);
        }
    }
    
    public void muodotKestonMukaan() {
        
        TreeMap<String, Integer> muotoLista = new TreeMap<>();

        int lyhyt = 0;
        int keski = 0;
        int pitka = 0;
        
        for(int i=0;i<ufoLista.size();i++) {
            Double temp = this.ufoLista.get(i).getKesto();
            String muoto1 = this.ufoLista.get(i).getMuoto();
            //lyhyt - keski - pitka
            if(temp<=60.0) {
                lyhyt++;
                if(!muotoLista.containsKey(muoto1 + ":lyhyt") && temp <=60.0) {
                    muotoLista.put(muoto1 + ":lyhyt", 1);
                } else {
                    int value = muotoLista.get(muoto1 + ":lyhyt");
                    value++;
                    muotoLista.put(muoto1+":lyhyt", value);
                }
                
            } else if(temp >=60.1 && temp < 1200.0) {
                keski++;
                if(!muotoLista.containsKey(muoto1 + ":keski") && temp >=60.1 && temp < 1200.0) {
                    muotoLista.put(muoto1+":keski", 1);
                } else {
                    int value = muotoLista.get(muoto1 + ":keski");
                    value++;
                    muotoLista.put(muoto1+":keski", value);
                }
            } else if (!muotoLista.containsKey(muoto1 + ":keski") && temp >=1200) {
                pitka++;
                if(!muotoLista.containsKey(muoto1 + ":pitka")) {
                    muotoLista.put(muoto1+":pitka", 1);
                } else {
                    int value = muotoLista.get(muoto1 + ":pitka");
                    value++;
                    muotoLista.put(muoto1+":pitka", value);
                }
                
            }
            
        }
        
        System.out.println("\nMuodot havainnon keston mukaan ryhmiteltynä");
        System.out.println("lyhyt: 0-60.0, havaintoja " + lyhyt);
        System.out.println("keskipitkä: 60.0-1200.0, havaintoja " + keski);
        System.out.println("pitkä: 1200.0-3600.0, havaintoja " + pitka );
        
        String rivi = "\n" + "      muoto lyhyt keskipitkä pitkä";

        System.out.println("      " + rivi);
        for(String avain : muotoLista.keySet()) {
            int value = muotoLista.get(avain);
            String muoto1 = avain;
            String[] muotoOsissa = muoto1.split(":");
            if(muotoOsissa[1].equals("lyhyt")) {
                String tulosta = muotoOsissa[0] + " " + value + " 0 0";
                System.out.println("      " + tulosta);
            } else if(muotoOsissa[1].equals("keski")) {
                String tulosta = muotoOsissa[0] + " 0 " + value + " 0 ";
                System.out.println("      " + tulosta);
            } else if(muotoOsissa[1].equals("pitka")) {
                String tulosta = muotoOsissa[0] + " 0 0 " +value;
                System.out.println("      " + tulosta);
            }
        } 
    }
    
    public void tunnitHavainnonMukaan() {
        List<Muoto> lista = new ArrayList<>();
        
        for(int i=0;i<this.ufoLista.size();i++) {
            double kesto = this.ufoLista.get(i).getKesto();
            String kuvaus = this.ufoLista.get(i).getMuoto();
            Muoto muoto = new Muoto(kuvaus, 0,0,0);
            int tarkista = 0;
            for(int j=0;j<lista.size();j++) {
                if(lista.get(j).getNimi().equals(kuvaus)) {
                    if(kesto <= 60.0) {
                        lista.get(j).setLyhyt();
                        tarkista++;
                    } else if(kesto >= 60.1 && kesto < 1200.0) {
                        lista.get(j).setKeski();
                        tarkista++;
                    } else if(kesto >= 1200.0) {
                        lista.get(j).setPitka();
                        tarkista++;
                    }
                }
            }
            if(tarkista == 0) {
                if(kesto <= 60.0) {
                        muoto.setLyhyt();
                        lista.add(muoto);
                    } else if(kesto >= 60.1 && kesto < 1200.0) {
                        muoto.setKeski();
                        lista.add(muoto);
                    } else if(kesto >= 1200.0) {
                        muoto.setPitka();
                        lista.add(muoto);
                    }
            }
        }
        
        System.out.println("\nTunnit havainnon keston mukaan ryhmiteltynä");
        System.out.println("      muoto lyhyt keskipitkä pitkä");
        Collections.sort(lista, new nimiVertaaja());
        for(int i=0;i<lista.size();i++) {
            System.out.println("      " + lista.get(i).getNimi() + " " + lista.get(i).getLyhyt() + " " + lista.get(i).getKeski() + " " + lista.get(i).getPitka());
        }
    }
    
    public void yleisimmatSanat() {
        HashMap<String, Integer> lista = new HashMap<>();
        
        for(int i=0;i<ufoLista.size();i++) {
            String rivit = this.ufoLista.get(i).getKuvaus().replaceAll("[0-9]", "").replaceAll("\\.", "").trim();
            String[] rivi = rivit.split(" ");
            for(int j=0;j<rivi.length;j++) {
                if(!lista.containsKey(rivi[j])) {
                    lista.put(rivi[j], 1);
                } else {
                    int value = lista.get(rivi[j]);
                    value++;
                    lista.put(rivi[j] , value);
                }
            }
        }
        
        Map<String, Integer> sortedLista = lista
        .entrySet()
        .stream()
        .sorted(comparingByValue())
        .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
        
        System.out.println("\nYleisimmät sanat esiintymiskertojen mukaan järjestettynä");
        int count = 0;
        if(lista.size()<15) {
            while(count < lista.size()) {
                for(String avain : sortedLista.keySet()) {
                    int value = sortedLista.get(avain);
                    String tulostus = avain + ", " + value;
                    System.out.println("      " + tulostus);
                    count++;
                }
            }
        } else {
            for(String avain : sortedLista.keySet()) {
                // JOs halutaan näyttää ensimäiset 15 - tällöin tosin suurin arvo 2 jää ulkopuolelle tulostuksesta
                //if(count==15) {
                //    break;
                //}
                int value = sortedLista.get(avain);
                String tulostus = avain + ", " + value;
                System.out.println("      " + tulostus);
                count++;
            }               
        }   
    }
    
    public void yleisimmatSanaParit() {
        
        int sanaMaara = 0;
        
        TreeMap<String, Integer> sanaLista = new TreeMap<>();
        List<String> testiLista = new ArrayList<>();
        
        String sanat = "";

        //Kaikki sanat haltuun
        for(int i=0;i<ufoLista.size();i++) {
            String rivit = this.ufoLista.get(i).getKuvaus().replaceAll("[0-9]", "").replaceAll("[,?!/:;\\-\\_\\.]", " ").trim();
            String[] rivi = rivit.split("[ \n\t\r]");
            for(int j=0;j<rivi.length;j++) {
                sanat = sanat + rivi[j] + " ";
                testiLista.add(rivi[j]);
                sanaMaara++;
            }  
        }
        
        System.out.println("\nYleisimmät sanaparit esiintymiskertojen mukaan järjestettynä");
        
        if((testiLista.size()/2) % 2 == 0) {
            for(int i=0;i<testiLista.size();i+=2) {
                String pari = "";
                pari = pari + testiLista.get(i) + " " + testiLista.get(i+1) + " ";
                if(!sanaLista.containsKey(pari)) {
                    sanaLista.put(pari, 1);
                } else {
                    int value = sanaLista.get(pari);
                    value++;
                    sanaLista.put(pari, value);
                }
            }
        } else if((testiLista.size()/2) % 2 != 0) {
            testiLista.remove(testiLista.size()-1);
            for(int i=0;i<testiLista.size();i+=2) {
                String pari = "";
                pari = pari + testiLista.get(i) + " " + testiLista.get(i+1) + " ";
                if(!sanaLista.containsKey(pari)) {
                    sanaLista.put(pari, 1);
                } else {
                    int value = sanaLista.get(pari);
                    value++;
                    sanaLista.put(pari, value);
                }
            }
        }
        
        Map<String, Integer> sortedLista = sanaLista.entrySet().stream().sorted(comparingByValue())
        .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        
        int count=0;
        for(String avain : sanaLista.keySet()) {
            if(testiLista.size()>=20) {
                if(count==10) {
                    break;
                }
                int value = sanaLista.get(avain);
                System.out.println("      " + avain.trim() + ", " + value);
                count++;
            } else {
                int value = sanaLista.get(avain);
                System.out.println("      " + avain.trim() + ", " + value);
            }
            
        }
    }
    
    //Käytetään nimien vertaamiseen muoto metodissa jossa tulostetaan arvot aakkosjärjestyksessä - List
    public class nimiVertaaja implements Comparator<Muoto> {
        public int compare(Muoto m1, Muoto m2) {
            return m1.getNimi().compareTo(m2.getNimi());
        }
    }
}
