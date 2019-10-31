import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class task1 {

    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        list = new ArrayList<>();
        readFile(args[0]);

        DecimalFormat df = new DecimalFormat("#####0.00");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();

        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);

        selectionSort();
        System.out.println(df.format(getPersentil()));
        System.out.println(df.format(getMedian()));
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

    public static double getMedian(){
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

    public static double getPersentil() {
        double n = (0.9 * (list.size() - 1 ) + 1);
        int intValue = (int) Math.floor(n) - 1;
        double fraction = n % 1;
        return list.get(intValue) + fraction*(list.get(intValue + 1) - list.get(intValue));
    }
}
