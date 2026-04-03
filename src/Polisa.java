import java.util.Objects;

public class Polisa {
    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;
    private static int liczbaUtworzonychPolis;
    private static final double OPLATA_ADMINISTRACYJNA = 150.0;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient){
        this.numerPolisy=numerPolisy;
        this.klient=klient;
        this.skladkaBazowa=skladkaBazowa;
        this.poziomRyzyka=poziomRyzyka;
        this.wartoscPojazdu=wartoscPojazdu;
        this.czyMaAlarm=czyMaAlarm;
        this.czyBezszkodowyKlient=czyBezszkodowyKlient;
        liczbaUtworzonychPolis++;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public double getSkladkaBazowa() {
        return skladkaBazowa;
    }

    public int getPoziomRyzyka() {
        return poziomRyzyka;
    }

    public double obliczSkladkeKoncowa(){
        double skladkaKoncowa=this.skladkaBazowa+(this.poziomRyzyka*120);
        if(this.wartoscPojazdu>150000){
            skladkaKoncowa+=500;
        }
        if(this.czyMaAlarm){
            skladkaKoncowa-=100;
        }
        if(this.czyBezszkodowyKlient){
            skladkaKoncowa*=0.95;
        }
        if(skladkaKoncowa<skladkaBazowa){
            return skladkaBazowa;
        } else{
            return skladkaKoncowa;
        }

    }
    public double obliczSkladkeOdnowienia(){
        double skladkaKoncowa = obliczSkladkeKoncowa();
        double skladkaOdnowienia=skladkaKoncowa;
        if(poziomRyzyka==4){
            skladkaOdnowienia*=1.1;
        }
        if(poziomRyzyka>4){
            skladkaOdnowienia*=1.2;
        }
        if(wartoscPojazdu>60000){
            skladkaOdnowienia+=150;
        }
        if(czyBezszkodowyKlient){
            skladkaOdnowienia*=0.92;
        }
        if(czyMaAlarm){
            skladkaOdnowienia*=0.95;
        }
        if(skladkaOdnowienia<skladkaKoncowa*0.9){
            skladkaOdnowienia=skladkaKoncowa*0.9;
        }
        if(skladkaOdnowienia>skladkaKoncowa*1.25){
            skladkaOdnowienia=skladkaKoncowa*1.25;
        }
        return (double) Math.round(skladkaOdnowienia * 100) /100;
    }
    public String pobierzPodsumowanieRyzyka(){
        String podsumowanieRyzyka="";
        double mnoznik=1.0;
        if(poziomRyzyka<4){
            podsumowanieRyzyka+="Ryzyko niskie, ";
        } else if(poziomRyzyka==4){
            podsumowanieRyzyka+="Ryzyko srednie, ";
            mnoznik*=1.1;
        } else{
            podsumowanieRyzyka+="Ryzyko wysokie, ";
            mnoznik*=1.2;
        }
        if(czyBezszkodowyKlient){
            podsumowanieRyzyka+="bezszkodowy, ";
            mnoznik*=0.92;
        } else {
            podsumowanieRyzyka+="z szkodami, ";
        }
        if(czyMaAlarm){
            podsumowanieRyzyka+="posiada alarm, ";
            mnoznik*=0.95;
        } else {
            podsumowanieRyzyka+="nie posiada alarmu, ";
        }
        podsumowanieRyzyka+="mnoznik calkowity: "+mnoznik;
        return podsumowanieRyzyka;
    }
    public static int pobierzLiczbeUtworzonychPolis(){
        return liczbaUtworzonychPolis;
    }

    @Override
    public String toString() {
        return "NumerPolisy: "+numerPolisy
                + " Klient: "+klient
                + " Skladka bazowa: "+skladkaBazowa
                +" Poziom ryzyka: "+poziomRyzyka
                +" WartoscPojazdu: "+wartoscPojazdu
                +" Czy ma alarm: "+czyMaAlarm
                +" Czy bezszkodowy: "+czyBezszkodowyKlient;
    }
    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        Polisa that = (Polisa) o;
        return Objects.equals(numerPolisy, that.numerPolisy);
    }
    @Override
    public int hashCode() {
        return Objects.hash(numerPolisy);
    }
}
