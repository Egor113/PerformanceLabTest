import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class task3 {

    static class DataFile{
        ArrayList<Double> values;

        public DataFile() {
            this.values = new ArrayList<>();
        }
    }

    static ArrayList<DataFile> fileList;
    static ArrayList<Double> timeValues;

    public static void main(String[] args) {
        fileList = new ArrayList<>();
        getFiles(args[0]);
        System.out.println(getMinQueueTime());
    }

    public static void getFiles(String dirName){
        File dir = new File(dirName);
        if(dir.isDirectory())
        {
            for(File item : dir.listFiles()){

                if(!item.isDirectory()){

                    readFile(dirName + "/" + item.getName());
                }
            }
        }
    }

    public static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new
                FileReader(fileName))) {
            DataFile file = new DataFile();
            String str;
            while ((str = reader.readLine()) != null) {
                file.values.add(Double.parseDouble(str));
            }
            fileList.add(file);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getMinQueueTime(){
        timeValues = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            timeValues.add(0.0);
        }
        for (DataFile file: fileList) {
            for (int i = 0; i < file.values.size(); i++) {
                timeValues.set(i,timeValues.get(i)+file.values.get(i));
            }
        }
        return timeValues.indexOf(Collections.max(timeValues))+1;
    }
}
