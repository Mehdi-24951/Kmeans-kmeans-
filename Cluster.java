package Kmeans;

import java.util.HashMap;
import java.util.Vector;

public class Cluster extends Point {
    // numéro de cluster, permet de l'identifier
    private int number;
    // Un vecteur des points affilié à ce cluster
    private Vector<Point> myPoints;
    // Un vecteur de points affilié à ce cluster avant la mise à jour
    private Vector<Point> myOldPoints;

    public Cluster(double xCentroid, double yCentroid, int number) {
        super(xCentroid, yCentroid);
        this.number = number;
        myPoints = new Vector<>();
        myOldPoints = new Vector<>();

    }

    public Cluster(Point point, int number) {
        super(point);
        this.number = number;
        myPoints = new Vector<>();
        myOldPoints = new Vector<>();

    }

    /**
     * mettre à jour les coordonnees du centroide
     */
    public void miseAJour() {
        this.setX(this.averageX());
        this.setY(this.averageY());
    }

    /**
     * Retourne la valeur moyenne sur le coordonne x
     *
     * @return moyenne
     */

    private double averageX() {
        double averageX = 0;

        for (Point point : myPoints) {
            averageX = averageX + point.getX();
        }

        return averageX / myPoints.size();
    }

    /**
     * Retourne la valeur moyenne sur le coordonne y
     *
     * @return moyenne
     */

    private double averageY() {
        double averageY = 0;

        for (Point point : myPoints) {
            averageY = averageY + point.getY();
        }

        return averageY / myPoints.size();
    }

    /**
     * test sur le size et un test sur l'égalite des valeurs, vrai si le cluster est stable après une mise à jour
     *
     * @return Boolean
     */
    public boolean isItStable() {
        if (myPoints.size() != this.myOldPoints.size()) {
            return false;
        }

        for (int i = 0; i < myPoints.size(); i++) {
            if (!myPoints.get(i).equals(this.myOldPoints.get(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Retourne la distance au carrée entre le cluster et des points données
     *
     * @param points
     * @return vector
     */
    public Vector<Double> getDistanceSquareToPoints(Vector<Point> points) {
        Vector<Double> distanceData = new Vector<Double>();
        points.forEach(point -> distanceData.add(point.distanceEuclidienneSquare(this)));
        return distanceData;
    }

    /**
     * ajoute un point a notre vecteur de point myPoints
     */

    public void AddMypoint(Point point) {
        this.myPoints.add(point);
    }

    public void SwapMyPoints() {
        Vector<Point> Auxi = this.myOldPoints;
        this.myOldPoints = myPoints;
        this.myPoints = Auxi;
        this.myPoints.clear();
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public Vector<Point> getMyPoints() {
        return myPoints;
    }


    public void PrintMyPoints() {
        this.myPoints.forEach(point -> System.out.println(point.toString()));
    }

    @Override
    public String toString() {
        return "[xCentroid=" + super.getX() + ", yCentroid=" + super.getY() + ", clusterNumber=" + number + "]";
    }
}
