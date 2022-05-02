package Kmeans;

import javax.swing.text.html.HTML;
import java.util.Random;
import java.util.Vector;


public class Kmeans {

    protected Vector<Point> data = new Vector<>();
    protected Vector<Cluster> clusters = new Vector<>();


    /**
     * Permet de lancer l'algorithme du Kmeans (avec génération de data)
     *
     * @param nbPoint       : Le nombre de points à généré
     * @param low:          borne inférieur inclus
     * @param high          : borne supérieur exclus
     * @param clusterNumber : Le nombre de cluster souhaiter
     */
    public void findSolution(int nbPoint, double low, double high, int clusterNumber) {
        this.genererPoints(nbPoint, low, high);
        System.out.println("##########################  Data initiale  ##########################");
        this.printPoints(this.data);
        System.out.println();
        this.initialiseCluster(clusterNumber);
        System.out.println("##########################  Liste de Centroids initiale  ##########################");
        this.printClusters();
        System.out.println("########################## Lancement du Kmeans jusqu'a stabilisation ########################## ");

        this.traitement();
    }

    /**
     * Permet de lancer l'algorithme du Kmeans avec les données du rapport (Section Kmeans++)
     * Pour faire une comparaison du nombre d'itération du kmeans et le Kmeans++
     */
    public void findSolution() {
        this.genererPointsExempleRapport();
        System.out.println("##########################  Data initiale  ##########################");
        this.printPoints(this.data);
        this.initialiseCluster(3);
        System.out.println("##########################  Liste de Centroids initiale  ##########################");
        this.printClusters();
        System.out.println("########################## Lancement du Kmeans jusqu'a stabilisation ########################## ");
        this.traitement();
    }

    /**
     * Génère un jeu de données aléatoire de points 2D
     *
     * @param nbPoint
     * @param low
     * @param high
     */
    protected void genererPoints(int nbPoint, double low, double high) {

        for (int i = 0; i < nbPoint; i++) {
            int x = (int) this.generateRandomly(low, high);
            int y = (int) this.generateRandomly(low, high);
            Point point = new Point(x, y);
            data.add(point);
        }
    }

    /**
     * Génère les data vu au rapport section Kmeanspp
     */

    protected void genererPointsExempleRapport() {
        this.data.add(new Point(7, 4));
        this.data.add(new Point(8, 3));
        this.data.add(new Point(5, 9));
        this.data.add(new Point(3, 3));
        this.data.add(new Point(1, 3));
        this.data.add(new Point(10, 1));
    }


    /**
     * Initialise un ensemble de clusters choisi aléatoirement dans l'ensemble des points
     *
     * @param clusterNumber
     */
    void initialiseCluster(int clusterNumber) {
        Vector<Point> DataCopie = (Vector<Point>) data.clone();
        for (int i = 0; i < clusterNumber; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(DataCopie.size());
            Point point = DataCopie.get(randomIndex);
            DataCopie.set(randomIndex, DataCopie.lastElement());
            DataCopie.remove(DataCopie.lastElement());
            Cluster cluster = new Cluster(point, i + 1);
            this.clusters.add(cluster);
        }
    }

    /**
     * traiement du Kmeans
     */
    protected void traitement() {
        int nbIteration = -1;


        do {
            clusters.forEach(cluster -> cluster.SwapMyPoints());
            data.forEach(point -> point.SetNearestCluster(clusters).AddMypoint(point));
            step();
            nbIteration++;
            this.printClustersAndTheirPoints();
            System.out.println();
        } while (!isClustersStable());
        System.out.println("Nombre iteration : " + nbIteration);
    }

    /**
     * Regarde si l'ensemble des clusters ont été stabilisé lors de la derniere mise à jour
     *
     * @return boolean
     */
    private boolean isClustersStable() {

        int i = 0;
        while (i < clusters.size() && clusters.get(i).isItStable()) {
            i++;
        }
        return i == clusters.size();
    }

    /**
     * Mise à jour : calcul du centre de masses des differents clusters
     */
    private void step() {
        this.clusters.forEach(cluster -> cluster.miseAJour());
    }

    /**
     * Génére un double de facon aléatoire entre [low,high[
     *
     * @param low
     * @param high
     * @return double
     */
    protected double generateRandomly(double low, double high) {
        Random r = new Random();
        return low + (high - low) * r.nextDouble();
    }

    protected void printPoints(Vector<Point> points) {
        points.forEach(point -> System.out.println(point.toString()));
    }

    void printClusters() {
        for (Cluster cluster : this.clusters) {
            System.out.println(cluster.toString());
            System.out.println();
            cluster.PrintMyPoints();
            System.out.println();

        }
    }

    private void printClustersAndTheirPoints() {
        System.out.println();
        this.printClusters();
        System.out.println();
    }

}