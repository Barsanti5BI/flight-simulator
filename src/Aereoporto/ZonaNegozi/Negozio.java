package Aereoporto.ZonaNegozi;

import Persona.Persona;
import Persona.Prodotto;
import  Persona.ImpiegatoNegozi;
import Utils.Coda;

import java.util.ArrayList;

public class Negozio extends Thread{
   int idNegozio;
    String nome;
    String categoria;
    Coda<Persona> personeInAttesa;
    ImpiegatoNegozi impiegatoNegozi;
    ArrayList<Prodotto> oggettiInVendita;

        public Negozio(String nome, String categoria, ArrayList<Prodotto> oggettiInVendita,int idNegozio){
        	  this.nome = nome;
        	  this.categoria = categoria;
        	  personeInAttesa = new Coda<Persona>();
              this.oggettiInVendita = oggettiInVendita;
              this.idNegozio = idNegozio;
              impiegatoNegozi = new ImpiegatoNegozi("Impiegato Vendita",idNegozio, oggettiInVendita,personeInAttesa);
        }
        public void run(){
          while(true){
             try {
                   Thread.sleep(1000);
             } catch (InterruptedException ex){
                System.out.println(ex);
             }
          }
        }

        public String getNome()
        {
            return nome;
        }

        public Coda<Persona> getCodaCassa()
        {
            return personeInAttesa;
        }

        public ImpiegatoNegozi getImpiegatoNegozi()
        {
            return impiegatoNegozi;
        }

        public ArrayList<Prodotto> getOggettiInVendita()
        {
            return oggettiInVendita;
        }
        public String getCategoria(){
            return categoria;
        }
        public int getIdNeg(){return idNegozio;}
}
