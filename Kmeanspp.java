package Kmeans;


import java.util.Vector;

public class Kmeanspp extends Kmeans {

    public Kmeanspp() {
        super();
    }

    /**
     * Permet de lancer le traitement KmeansPP
     *
     * @param nbPoint : Le nombre de points à généré
     * @param low:    borne inférieure inclus
     * @param high    : borne supérieure exclus
     * @param k       : Le nombre de cluster à trouver
     */
    public void kmeanPPTreatment(int nbPoint, double low, double high, int k) {
        System.out.println("##########################  Data initiale  ##########################");
        this.genererPoints(nbPoint, low, high);
        this.printPoints(this.data);
        System.out.println();
        System.out.println("##########################  Premier Centroid générer aléatoirement  ##########################");
        this.initialiseCluster(1);
        System.out.println(this.clusters.get(0).toString());
        System.out.println();
        System.out.println("##########################  Liste de Centroids initiale  ##########################");
        this.findClusters(k);
        this.printClusters();
        System.out.println("########################## Lancement du Kmeans jusqu'a stabilisation ########################## ");

        this.traitement();

    }

    /**
     * Permet de lancer le traitement KmeansPP
     * Lance le Kmeanspp avec l'exemple du rapport
     */


    public void kmeanPPTreatment() {
        this.genererPointsExempleRapport();
        System.out.println("##########################  Data initiale  ##########################");
        this.printPoints(this.data);
        this.clusters.add(new Cluster(this.data.get(0), 1));
        this.findClusters(3);
        System.out.println("##########################  Liste de Centroids initiale  ##########################");
        this.printClusters();
        System.out.println("########################## Lancement du Kmeans jusqu'a stabilisation ########################## ");
        this.traitement();

    }


    /**
     * Permet de calculer les differents clusters en utilisant le premier cluster trouvé aléatoirement qui se trouve dans l'attribut clusters
     *
     * @param k : le nombre de clusters à calculer
     */
    private void findClusters(int k) {

        Vector<Double> distanceSquareToCluster = this.clusters.get(0).getDistanceSquareToPoints(this.data);
        for (int i = 1; i < k; i++) {

            int inter2 = this.getPointWithMaxDistance(distanceSquareToCluster);
            this.clusters.add(new Cluster(this.data.get(inter2), i + 1));
            this.TableDistanceCentroidPlusPres(distanceSquareToCluster, this.clusters.get(i));
        }
    }

    /**
     * Prend un vecteur en paramétre et un cluster, et modifie notre vecteur en gardant la distanceSquare au centroid le plus près.
     *
     * @param distanceOne : tableau de distance
     * @param :           cluster : Cluster
     */
    private void TableDistanceCentroidPlusPres(Vector<Double> distanceOne, Cluster cluster) {

        for (int i = 0; i < distanceOne.size(); i++) {
            double d = cluster.distanceEuclidienneSquare(this.data.get(i));
            if (distanceOne.get(i) > d) {
                distanceOne.set(i, d);
            }
        }
    }

    /**
     * Récupère l'indice du point avec la plus grande distance
     *
     * @param distanceSquareToClusterOne
     * @return
     */
    public int getPointWithMaxDistance(Vector<Double> distanceSquareToClusterOne) {
        int inter = 0;

        double max = Double.MIN_VALUE;
        Point farthestPoint = null;

        for (int i = 0; i < distanceSquareToClusterOne.size(); i++) {
            if (max < distanceSquareToClusterOne.get(i)) {
                max = distanceSquareToClusterOne.get(i);
                inter = i;
            }

        }
        return inter;
    }

    /**
     * Lance le traitement du kmeans ++
     *
     * @param nbPoint : Le nombre de points à généré
     * @param low:    borne inférieur inclus
     * @param high    : borne supérieur exclus
     * @param k       : nombre de cluster à généré
     */
    public void findSolution(int nbPoint, double low, double high, int k) {
        this.kmeanPPTreatment(nbPoint, low, high, k);
    }

    /**
     * Lance le traitement du kmeans ++ avec l'exemple du rapport
     */
    public void findSolution1() {
        this.kmeanPPTreatment();
    }
}
















