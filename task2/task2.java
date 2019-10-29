import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task2 {

    static Quadrangle quadrangle;
    static ArrayList<Point> points;

    static class Point {
        float x;
        float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Quadrangle {
        Point a, b, c, d;

        public Quadrangle(Point a, Point b, Point c, Point d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) {
        readFile("Quadrangle",true);
        readFile("Points",false);
        System.out.println(quadrangle.a.x + " " + quadrangle.a.y);
        System.out.println(quadrangle.b.x + " " + quadrangle.b.y);
        System.out.println(quadrangle.c.x + " " + quadrangle.c.y);
        System.out.println(quadrangle.d.x + " " + quadrangle.d.y);

        System.out.println();

        for (Point point:points) {
            System.out.println(point.x + " " + point.y);
        }

        for (Point point: points) {
            System.out.println(f(quadrangle.a,quadrangle.b,quadrangle.d,point) && f(quadrangle.b,quadrangle.c,quadrangle.d,point)
                    && f(quadrangle.b,quadrangle.c,quadrangle.a,point) && f(quadrangle.c,quadrangle.d,quadrangle.a,point)
                    && f(quadrangle.c,quadrangle.d,quadrangle.b,point) && f(quadrangle.d,quadrangle.a,quadrangle.b,point)
                    && f(quadrangle.d,quadrangle.a,quadrangle.c,point) && f(quadrangle.a,quadrangle.b,quadrangle.c,point)
                    ? "yes" : "no");
        }

    }

    public static void readFile(String fileName, boolean isQuadrangle){
        try (BufferedReader reader = new BufferedReader( new
                FileReader( fileName ))) {
            String str;
            if (isQuadrangle){
                ArrayList<Point> pointList = new ArrayList<>();
                while ((str = reader.readLine()) != null ) {
                    String [] coordinates = str.split(" ");
                    pointList.add(new Point(Float.parseFloat(coordinates[0]),Float.parseFloat(coordinates[1])));
                }
                quadrangle = new Quadrangle(pointList.get(0),pointList.get(1),pointList.get(2),pointList.get(3));
            }
            else {
                points = new ArrayList<>();
                while ((str = reader.readLine()) != null ) {
                    String [] coordinates = str.split(" ");
                    points.add(new Point(Float.parseFloat(coordinates[0]),Float.parseFloat(coordinates[1])));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Вычисляет положение точки D(xd,yd) относительно прямой AB
    public static float g(Point a, Point b, Point d){
        return (d.x - a.x) * (b.y - a.y) - (d.y - a.y) * (b.x - a.x);
    }

    // Лежат ли точки C и D с одной стороны прямой (AB)?
    public static boolean f(Point a, Point b, Point c, Point d)
    {
        return g(a, b, c) * g(a, b, d) >= 0;
    }

}
