package logica;

import java.util.ArrayList;

import entities.Giocatore;

public class Start {
    public void start(){
        //initialize scanner
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int numGiocatori;
        int difficolta;
        int colore;
        Giocatore giocatori[];
        ArrayList<Integer> coloriUsati;

        System.out.println("Quanti giocatori siete?");

        do{
            System.out.println("Inserire un numero compreso tra 2 e 4");
            numGiocatori = scanner.nextInt();
        }while(numGiocatori < 2 || numGiocatori > 4);

        numGiocatori = scanner.nextInt();
        giocatori=new Giocatore[numGiocatori];
        coloriUsati=new ArrayList<Integer>();

        for (int i=0; i<numGiocatori; i++){
            do{
                do{
                System.out.println("Inserire un colore compreso tra 1 e 4 \n1=rosso \n2=blu \n3=giallo \n4=verde");
                colore = scanner.nextInt();
                }while(colore < 1 || colore > 4);
            }while(coloriUsati.contains(colore));


            
            System.out.println("Inserisci un nome");
            String nome= scanner.nextLine();
            if (colore==1){
                coloriUsati.add(colore);
                giocatori[i]=new Giocatore(nome, "#fc0303");
            }else if(colore==2){
                coloriUsati.add(colore);
                giocatori[i]=new Giocatore(nome, "#3366ff");
            }else if (colore==3){
                coloriUsati.add(colore);
                giocatori[i]=new Giocatore(nome, "#ffff00");
            }else if (colore==4){
                coloriUsati.add(colore);
                giocatori[i]=new Giocatore(nome, "#00ff00");
            }




        }
    }
}