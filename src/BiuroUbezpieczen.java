import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;
    public BiuroUbezpieczen(String nazwa){
        this.nazwa = nazwa;
        polisy = new ArrayList<>();
    }
    public void dodajPolise(Polisa polisa){
        polisy.add(polisa);
    }
    public void wypiszRaport(){
        System.out.println("Raport biura ubezpieczen");
        System.out.println("Nazwa: "+nazwa);
    }
    public double policzLacznaSkladke(){
        double lacznaSkladka=0.0;
        for(Polisa polisa:polisy){
            lacznaSkladka+=polisa.obliczSkladkeKoncowa();
        }
        return lacznaSkladka;
    }
    public double policzLacznaPrognozeOdnowien(){
        double lacznaPrognozaOdnowien=0.0;
        for(Polisa polisa:polisy){
            lacznaPrognozaOdnowien+=polisa.obliczSkladkeOdnowienia();
        }
        return lacznaPrognozaOdnowien;
    }
    public int policzPolisyWysokiegoRyzyka(){
        int polisyWysokiegoRyzyka=0;
        for(Polisa polisa:polisy){
            if(polisa.getPoziomRyzyka()>=4){
                polisyWysokiegoRyzyka++;
            }
        }
        return polisyWysokiegoRyzyka;
    }
    public Polisa znajdzPoNumerze(String numerPolisy){
        for(Polisa polisa:polisy){
            if(polisa.getNumerPolisy().equals(numerPolisy)){
                return polisa;
            }
        }
        return null;
    }
    public void wypiszTanszeNiz(double prog){
        for(Polisa polisa:polisy){
            if(polisa.obliczSkladkeKoncowa()>prog){
                System.out.println(polisa);
            }
        }
    }
}

