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

    }
}
