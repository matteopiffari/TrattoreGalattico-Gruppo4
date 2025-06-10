package cards;

public abstract class Carta {
   private String nome;
   private String descrizione;

   public Carta(String nome, String descrizione){
      this.nome=nome;
      this.descrizione=descrizione;
   }
}
