package Aereoporto.Common;

import Persona.Turista;
import Utils.Coda;
import org.jetbrains.annotations.Nullable;

public class ZonaAeroporto {
     public @Nullable ZonaAeroporto zonaSuccessiva;
     public @Nullable ZonaAeroporto zonaPrecedente;

     public void impostaZonaSuccessiva(ZonaAeroporto zonaSuccessiva) {
         this.zonaSuccessiva = zonaSuccessiva;
     }
     public void impostaZonaPrecedente(ZonaAeroporto zonaPrecedente) {
        this.zonaPrecedente = zonaPrecedente;
     }
}
