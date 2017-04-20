package vikas.arraay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by VikasN on 9/13/16.
 */
public class KClosestPoint {

    public CPoint[] kClosest(CPoint[] points, int k){

        ArrayList<CPoint> list =  new ArrayList<>();
        for(CPoint p : points){
            list.add(p);
        }
        Collections.sort(list,new DistanceComparator());
        CPoint[] result = new CPoint[k];
        for(int i = 0;i<k;i++){
            result[i] = list.get(i);
        }
        return result;
    }

    class DistanceComparator implements Comparator<CPoint> {
        @Override
        public int compare(CPoint o1, CPoint o2) {
            return (int) ((int)distanceFromOrigin(o1) - distanceFromOrigin(o2));
        }
    }


    public double distanceFromOrigin(CPoint p){
        return Math.abs(Math.sqrt(Math.pow(p.x,2)+Math.pow(p.y,2)));
    }

    public static void main(String... args){
        CPoint a = new CPoint(1.0,1.0);
        CPoint[] points = new CPoint[]{(new CPoint(2.0,2.0)), (new CPoint(1.0,1.0)), (new CPoint(3.0,3.0))};
        KClosestPoint loader = new KClosestPoint();
        for(CPoint p : loader.kClosest(points,2)){
            System.out.print("("+p.x+","+p.y+")");
        }
        System.out.println();
    }
}
class CPoint{
    double x;
    double y;

    CPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
