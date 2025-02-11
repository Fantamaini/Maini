import java.util.Arrays;

public class StatistiquesTableau {

    public static void main(String[] args) {
        
        int[] tableau = {3, 5, 1, 7, 9, 2, 4, 6, 8, 10};

       
        System.out.println("Tableau initial: " + Arrays.toString(tableau));

      
        double moyenne = calculerMoyenne(tableau);
        System.out.println("Moyenne : " + moyenne);

        double mediane = calculerMediane(tableau);
        System.out.println("Médiane : " + mediane);

        double ecartType = calculerEcartType(tableau, moyenne);
        System.out.println("Écart-type : " + ecartType);

   
        Arrays.sort(tableau);
        System.out.println("Tableau trié : " + Arrays.toString(tableau));

       
        int min = tableau[0];
        int max = tableau[tableau.length - 1];
        System.out.println("Valeur minimale : " + min);
        System.out.println("Valeur maximale : " + max);
    }

    public static double calculerMoyenne(int[] tableau) {
        int somme = 0;
        for (int i = 0; i < tableau.length; i++) {
            somme += tableau[i];
        }
        return (double) somme / tableau.length;
    }

    public static double calculerMediane(int[] tableau) {
        Arrays.sort(tableau);
        int n = tableau.length;
        if (n % 2 == 0) {
            
            return (tableau[n / 2 - 1] + tableau[n / 2]) / 2.0;
        } else {
            
            return tableau[n / 2];
        }
    }

    public static double calculerEcartType(int[] tableau, double moyenne) {
        double sommeCarresDiff = 0;
        for (int i = 0; i < tableau.length; i++) {
            sommeCarresDiff += Math.pow(tableau[i] - moyenne, 2);
        }
        return Math.sqrt(sommeCarresDiff / tableau.length);
    }
}
