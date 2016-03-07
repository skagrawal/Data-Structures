package ska.ds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CalculateTimeDifferenceBetweenTwoStrings {
	public static void main(String[] args){
		String time1 = "(02/03/2002-11:00:00)::START";
		String time2 = "(02/03/2002-12:01:10)::STOP";
		timeDifference(time1,time2);

	}

	/**
	 * Calculate Time Difference
	 * @param time1
	 * @param time2
	 * @return
	 */
	private static long timeDifference(String time1, String time2) {

		String[] split1 = time1.split("::");
		String[] split2 = time2.split("::");
		long time = 0;;
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy-HH:mm:ss");
		Date date1,date2;
		try {
			int len = split1[0].length();
			date1 = format.parse(split1[0].substring(1, len-1));
			date2 = format.parse(split2[0].substring(1, len-1));
			time = date2. getTime() - date1.getTime(); 

		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(time/1000 +" minutes");
		return time;
	}


}