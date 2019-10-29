import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class task1 {

    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        list = new ArrayList<>();
        readFile("test.txt");

        DecimalFormat df = new DecimalFormat("#####0.00");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();

        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);

        selectionSort();
        System.out.println(df.format(findMedian()));
        System.out.println(df.format(list.get(list.size()-1)));
        System.out.println(df.format(list.get(0)));
        System.out.println(df.format(getAverage()));
    }

    public static void readFile(String fileName){
        try (BufferedReader reader = new BufferedReader( new
                FileReader( fileName ))) {
            String str;
            while ((str = reader.readLine()) != null ) {
                list.add(Integer.parseInt(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void selectionSort()
    {
        for (int i = 0; i < list.size(); i++)
        {
            int min = list.get(i);
            int min_i = i;
            for (int j = i + 1; j < list.size(); j++)
            {
                if (list.get(j) < min)
                {
                    min = list.get(j);
                    min_i = j;
                }
            }
            if (i != min_i)
            {
                int tmp = list.get(i);
                list.set(i,list.get(min_i));
                list.set(min_i,tmp);
            }
        }
    }

    public static double findMedian(){
        if (list.size() % 2 == 1){
            return list.get(list.size()/2);
        } else {
            return 0.5 *(list.get(list.size() / 2 - 1) + list.get(list.size()/2));
        }
    }

    public static double getAverage(){
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum+= list.get(i);
        }
        return sum/list.size();
    }
//    public static void calcPercentiles(LinkedHashMap hashMap) {
//        int size = hashMap.size();
//
//        List<Integer> value_list = new ArrayList(hashMap.values());
//
//        int sum = 0;
//        for (int i = 0; i < size; i++) {
//            sum += value_list.get(i);
//        }
//
//        for (int i = 0; i < size; i++) {
//            System.out.println(100 * value_list.get(i) / (float) sum);
//        }
//    }
//    public static void getPercentil(){
//        float[] mean = new float[3];
//
//        double[][] values = new double[3][list.size()];
//        int index = 0;
//
//        for (int axis : list) {
//            for (int i = 0; i < axis.length; i++) {
//                values[i][index] = axis[i];
//            }
//            index++;
//        }
//
//        for (int i = 0; i < mean.length; i++) {
//            mean[i] = (float) StatUtils.percentile(values[i], 50);
//        }
//
//        return mean;
//    }

}
