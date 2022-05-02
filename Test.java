package Kmeans;

public class Test {

    public static void main(String[] args) {

        //Kmeanspp demo = new Kmeanspp();
        //demo.findSolution1(); //Lancer Kmeanspp avec les données du rapport (chapitre3 section 3.1.1 Visualisation)
        Kmeans demo = new Kmeans();
        //demo.findSolution();//Lancer Kmeans avec les données du rapport (chapitre3 section 3.1.1 Visualisation)
         demo.findSolution(7,1,28,3);// Lancer Kmeans ou Kmeanspp avec un jeu de données aléatoires.
        //demo.findSolution(100,25,50,10);//Lancer Kmeanspp avec des données aléatoires

    }
}
