package Kmeans;

import java.util.HashMap;

import java.util.Vector;

public class Point {
    protected double x;
    protected double y;
    // Le numéro du cluster auquel ce point est affilié
    private int clusterNumber;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, int clusterNumber) {
        this.x = x;
        this.y = y;
        this.clusterNumber = clusterNumber;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }


    /**
     * Recherche  le cluster le plus proche de ce point et , et affecte au point le numero du cluster
     * * @param  clusters
     *
     * @return nearestCluster
     */
    public Cluster SetNearestCluster(Vector<Cluster> clusters) {

        double min = Double.MAX_VALUE;
        Cluster nearestCluster = null;
        for (Cluster cluster : clusters) {
            double d = this.distanceEuclidienne(cluster);

            if (d < min) {
                min = d;
                nearestCluster = cluster;
            }
        }

        this.clusterNumber = nearestCluster.getNumber();
        return nearestCluster;
    }

    /**
     * Calcul la distance euclidienne avec un autre point donné
     *
     * @param point
     * @return distanceEuclidienne
     */
    public double distanceEuclidienne(Point point) {
        return Math.sqrt(Math.pow((this.getX() - point.getX()), 2) + Math.pow((this.getY() - point.getY()), 2));
    }

    /**
     * Calcul la distance euclidienne au carre avec un point donne
     *
     * @param point
     * @return distanceEuclidienneSquare
     */
    public double distanceEuclidienneSquare(Point point) {
        return Math.pow(this.distanceEuclidienne(point), 2);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }


    public boolean equals(Point point) {
        return this.getX() == point.getX() && this.getY() == point.getY();
    }

    @Override
    public String toString() {
        return "[X = " + this.getX() + " Y =" + this.getY() + " clusterNumber= " + this.clusterNumber + "]";

    }
}