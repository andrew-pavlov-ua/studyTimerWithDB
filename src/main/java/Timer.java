import org.joda.time.*;

public class Timer {
    public static Long timeStart;
    public static Long result = 0L;
    private static Long timeNow;

    public static String formatDuration(Long duration) {
        Long seconds = (duration / 1000) % 60 ;
        Long minutes = ((duration / (1000*60)) % 60);
        Long hours   = ((duration / (1000*60*60)) % 24);


        StringBuilder formattedDuration = new StringBuilder();
        if (hours > 0) {
            formattedDuration.append(hours).append("h. ");
        }
        if (minutes > 0) {
            formattedDuration.append(minutes).append("m. ");
        }
        if (seconds > 0) {
            formattedDuration.append(seconds).append("s");
        }
        return formattedDuration.toString();
    }

    public static String Pause(){
        timeNow = System.currentTimeMillis();
        result += new Duration(timeStart, timeNow).getMillis();
        return formatDuration(result);
    }

    public static void clearResult(){
        result = 0L;
    }

    public static void stopRecord(){
        db.writeData(result);
    }
}