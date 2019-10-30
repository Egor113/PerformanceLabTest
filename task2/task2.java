import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class task2 {

    static Quadrangle quadrangle;
    static ArrayList<Point> points;
    static int zeroCounter;

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
        getPointType();
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
    public static float f(Point a, Point b, Point c, Point d)
    {
        return g(a, b, c) * g(a, b, d);
    }

    public static boolean isPointInside(float value){
        if (value < 0.0){
            System.out.println(3);
            return false;
        }
        else if (value == 0.0) zeroCounter++;
        return true;
    }

    public static void getPointType(){
        zeroCounter = 0;
        for (Point point: points) {
            if(isPointInside(f(quadrangle.a,quadrangle.b,quadrangle.d,point))){
                if (isPointInside(f(quadrangle.b,quadrangle.c,quadrangle.d,point))){
                    if (isPointInside(f(quadrangle.b,quadrangle.c,quadrangle.a,point))){
                        if (isPointInside(f(quadrangle.c,quadrangle.d,quadrangle.a,point))){
                            if (isPointInside(f(quadrangle.c,quadrangle.d,quadrangle.b,point))){
                                if (isPointInside(f(quadrangle.d,quadrangle.a,quadrangle.b,point))){
                                    if (isPointInside(f(quadrangle.d,quadrangle.a,quadrangle.c,point))){
                                        if (isPointInside(f(quadrangle.a,quadrangle.b,quadrangle.c,point))){
                                            if (zeroCounter == 4){
                                                System.out.println(0);
                                                zeroCounter = 0;
                                            }
                                            else if (zeroCounter == 2){
                                                System.out.println(1);
                                                zeroCounter = 0;
                                            }
                                            else {
                                                System.out.println(2);
                                                zeroCounter = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
