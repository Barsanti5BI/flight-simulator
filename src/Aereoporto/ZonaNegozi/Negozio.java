package Aereoporto.ZonaNegozi;

import Persona.ImpiegatoNegozi;
import Persona.Prodotto;
import Persona.Turista;
import Utils.Coda;

import java.util.ArrayList;

public class Negozio{
   int idNegozio;
    String nome;
    String categoria;
    Coda<Turista> personeInAttesa;
    ImpiegatoNegozi impiegatoNegozi;
    ArrayList<Prodotto> oggettiInVendita;

        public Negozio(String nome, String categoria, ArrayList<Prodotto> oggettiInVendita,int idNegozio){
        	  this.nome = nome;
        	  this.categoria = categoria;
        	  personeInAttesa = new Coda<Turista>();
              this.oggettiInVendita = oggettiInVendita;
              this.idNegozio = idNegozio;
              impiegatoNegozi = new ImpiegatoNegozi("Impiegato Vendita",idNegozio, oggettiInVendita,personeInAttesa);
              impiegatoNegozi.start();
        }

        public String getNome()
        {
            return nome;
        }

        public Coda<Turista> getCodaCassa()
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
