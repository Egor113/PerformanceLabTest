import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class task4 {

    static ArrayList<Visitor> visitors;
    static ArrayList<TimePeriod> timePeriods;

    static class TimePeriod {
        Date startTime;
        Date finishTime;
        int visitorCounter;

        public TimePeriod(Date startTime, Date finishTime, int visitorCounter) {
            this.startTime = startTime;
            this.finishTime = finishTime;
            this.visitorCounter = visitorCounter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TimePeriod period = (TimePeriod) o;
            return visitorCounter == period.visitorCounter &&
                    Objects.equals(startTime, period.startTime) &&
                    Objects.equals(finishTime, period.finishTime);
        }

        @Override
        public int hashCode() {

            return Objects.hash(startTime, finishTime, visitorCounter);
        }
    }


    static class Visitor{
        String id;
        Date startTime;
        Date finishTime;

        public Visitor(Date startTime, Date finishTime) {
            id = UUID.randomUUID().toString();
            this.startTime = startTime;
            this.finishTime = finishTime;
        }

    }

    public static void main(String[] args) throws ParseException {
        visitors = new ArrayList<>();
        timePeriods = new ArrayList<>();
        readFile(args[0]);
        for (TimePeriod period: getMaxBusyTimePeriod()) {
            SimpleDateFormat formatter = new SimpleDateFormat("h:mm");
            String strStartTime = formatter.format(period.startTime);
            String strFinishTime = formatter.format(period.finishTime);
            System.out.println(strStartTime + " " + strFinishTime);
        }
    }
    public static void readFile(String fileName) throws ParseException {
        try (BufferedReader reader = new BufferedReader( new
                FileReader( fileName ))) {
            String str;
            SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm");
            while ((str = reader.readLine()) != null ) {
                String [] timeArray = str.split(" ");
                visitors.add(new Visitor(dateFormat.parse(timeArray[0]),dateFormat.parse(timeArray[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<TimePeriod> getMaxBusyTimePeriod(){
        Set<TimePeriod> maxBusyTimePeriods = new LinkedHashSet<>();

        Set<Date> dateSet = putVisitorsToSet();
        Date maxStartTime = null;
        Date maxFinishTime = null;
        int count = 0;
        int finishVisitorCount = 0;
        int max = 0;
        boolean isPeriodFinish = false;
        for (Date time: dateSet) {
            for (Visitor visitor: visitors) {
                if (visitor.startTime.getTime() < time.getTime() && visitor.finishTime.getTime() > time.getTime()){
                    count++;
                }
                else if (visitor.startTime.getTime() == time.getTime()){
                    count++;
                }
                else if (visitor.finishTime.getTime() == time.getTime()){
                    finishVisitorCount++;
                }
            }
            if (count > max){
                max = count;
                maxStartTime = time;
                maxFinishTime = time;
            }
            if (count == max){
                maxFinishTime = time;
                if (finishVisitorCount == 0 && isPeriodFinish) {
                    maxStartTime = time;
                    timePeriods.add(new TimePeriod(maxStartTime,maxFinishTime,max));
                };
            }
            else if ((count + finishVisitorCount) >= max){
                maxFinishTime = time;
                isPeriodFinish = true;
                timePeriods.add(new TimePeriod(maxStartTime,maxFinishTime,max));
            }
            count = 0;
            finishVisitorCount = 0;
        }
        for (TimePeriod period: timePeriods) {

            if (period.visitorCounter == max && !period.startTime.equals(period.finishTime)){
                maxBusyTimePeriods.add(period);
            }
        }

        return maxBusyTimePeriods;
    }

    private static Set<Date> putVisitorsToSet() {
        TreeSet<Date> set = new TreeSet<>();
        for (Visitor visitor: visitors) {
            set.add(visitor.startTime);
            set.add(visitor.finishTime);
        }
        return set;
    }
}
