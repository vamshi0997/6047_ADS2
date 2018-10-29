import java.util.Scanner;
import java.util.ArrayList;
/**
 * log class for all methods.
 */
class Log {
	/**
	 * foodlog arraylist.
	 */
	private ArrayList<Foodlog> flog = new ArrayList<Foodlog>();
	/**
	 * sleeplog arraylist.
	 */
	private ArrayList<Waterlog> walog = new ArrayList<Waterlog>();
	/**
	 * Activitylog arraylist.
	 */
	private ArrayList<Activity> alog = new ArrayList<Activity>();
	/**
	 * weightlog arraylist.
	 */
	private ArrayList<Weight> wlog = new ArrayList<Weight>();
	/**
	 * sleeplog arraylist.
	 */
	private ArrayList<Sleep> slog = new ArrayList<Sleep>();
	/**
	 * addfood method.
	 * 
	 * @param item foodlog.
	 */
    public void addToFood(Foodlog item) {
       flog.add(item);
    }
    /**
	 * addwater method.
	 * 
	 * @param item waterlog.
	 */
    public void addToWater(Waterlog item) {
    	walog.add(item);
    }
    /**
	 * addactivity method.
	 * 
	 * @param item activitylog.
	 */
    public void addToActivity(Activity item) {
    	alog.add(item);
    }
    /**
	 * addweight method.
	 * 
	 * @param item waterlog.
	 */
    public void addToWeight(Weight item) {
    	wlog.add(item);
    }
    /**
	 * addsleep method.
	 * 
	 * @param item sleeplog.
	 */
    public void addToSleep(Sleep item) {
    	slog.add(item);
    }
    /**
     * summary gives all the log details sequentially.
     */
    public void summary() {
    	for (Foodlog i: flog) {
            System.out.println(i);
    	}
    	for (Waterlog j: walog) {
    		System.out.println(j);
    	}
    	for (Activity k: alog) {
    		System.out.println(k);
    	}
    	for (Weight l: wlog) {
    		System.out.println(l);
    	}
    	for (Sleep m: slog) {
    		System.out.println(m);
    	}
    }

}

/**
 * class foodlog.
 */
class Foodlog {
	/**
	 * food item.
	 */
	private String fooditem;
	/**
	 * food quantity.
	 */
    private int quantity;
    /**
     * time quantity.
     */
    private String time;
    /**
     * constructor for fooditem, quantity, time
     * 
     * @param fooditem1 fooditems.
     * @param quantity1 quantity
     * @param time1 time.
     */
    Foodlog(final String fooditem1, final int quantity1, final String time1) {
    	this.fooditem = fooditem1;
    	this.quantity = quantity1;
    	this.time = time1;
    }
    /**
     * to get food item.
     *
     * @return fooditem.
     */
    String getFoodItem() {
    	return this.fooditem;
    }
    /**
     * setting food item.
     * 
     * @param fooditem2 String
     */
    void setFoodItem(final String fooditem2) {
    	this.fooditem = fooditem2;
    }
    /**
     * getting the quantity.
     *
     * @return quantity.
     */
    int getQuantity() {
    	return this.quantity;
    }
    /**
     * setting quantity.
     * 
     * @param quantity int
     */
    void setQuantity(final int quantity2) {
    	this.quantity = quantity2;
    }
    /**
     * getting time.
     *
     * @return time
     */
    String time() {
    	return this.time;
    }
    /**
     * setting time.
     * 
     * @param time2 String
     */
    void setTime(final String time2) {
    	this.time = time2;
    }
}

/**
 * class waterlog.
 */
class Waterlog {
	/**
	 * quantity of water.
	 */
    private int waterQuantity;
    /**
     * constructor for water log.
     *
     * @param waterQuantity1 quantity.
     */
    Waterlog(final int waterQuantity1) {
    	this.waterQuantity = waterQuantity1;
    }
    /**
     * getting water qunatity.
     *
     * @return waterquantity.
     */
    int getWaterQuatity() {
    	return this.waterQuantity;
    }
    /**
     * setting water quantity.
     *
     * @param quantity quantity.
     */
    void setWaterQuantity(final int quantity) {
    	this.waterQuantity = quantity;
    }
}
/**
 * activity class.
 */
class Activity {
	/**
	 * declaring activity.
	 */
	private String activityname;
	/**
	 * declaring date.
	 */
	private String date;
	/**
	 * declaring start time.
	 */
	private String starttime;
	/**
	 * declaring end time.
	 */
	private String endtime;
	/**
	 * declaring notes.
	 */
	private String notes;
	/**
	 * constructor activity.
	 * 
	 * @param activityname1 String.
	 * @param date1 String.
	 * @param starttime1 String.
	 * @param endtime1 String.
	 * @param notes1 String.
	 */
	Activity(final String activityname1, final String date1, final String starttime1, final String endtime1, final String notes1) {
		this.activityname = activityname1;
	    this.date = date1;
	    this.starttime = starttime1;
	    this.endtime = endtime1;
	    this.notes = notes1;
	}
}
/**
 * weight class.
 */
class Weight {
	/**
	 * declaring weight
	 */
	private int weightinkg;
	/**
	 * declaring fat percent.
	 */
	private float fat;
	/**
	 * declaring date.
	 */
	private String date;
	/**
	 * weight constructor.
	 * 
	 * @param weightinkg1 weigth.
	 * @param fat1 fat percent.
	 * @param date1 date.
	 */
	Weight(final int weightinkg1, final float fat1, final String date1) {
		this.weightinkg = weightinkg1;
		this.fat = fat;
		this.date1 = date1;
	}
}
/**
 * class sleep
 */
class Sleep {
	/**
	 * declaring date and starttime. 
	 */
	private String datestart;
	/**
	 * declaring date and end time.
	 */
	private String dateend;
	/**
	 * constructor for sleep contains start and end time.
	 *
	 * @param datestart1 strig.
	 * @param dateend1 string.
	 */
	Sleep(String datestart1, String dateend1) {
		this.datestart = datestart1;
		this.dateend = dateend1;
	}
	/**
	 * get start time.
	 *
	 * @return datestart.
	 */
	String getStart() {
		return this.datestart;
	}
	/**
	 * set start time.
	 *
	 * @param start start time.
	 */
	void setStart(final String start) {
        this.datestart = start;
	}
	/**
	 * get settime.
	 *
	 * @return dateend.
	 */
	String getEnd() {
		return this.dateend;
	}
	/**
	 * set endtime.
	 *
	 * @param end endtime.
	 */
	void setEnd(final String end) {
		this.dateend = end;
	}
}

/**
 * solution class.
 */
public final class Solution {
	/**
	 * @default constructor.
	 */
	private Solution() {

	}
	/**
	 * main class.
	 * @param args the arguments.
	 */
	public static void main(final String[] args) {
        Log log = new Log();
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < testCases; i++) {
            String[] token = scan.nextLine().split(",");
            String[] check = token[0].split(" ");
            switch (check[0]) {
            	case "addToFood":
            	Foodlog f = new Foodlog(check[1], Integer.parseInt(token[1]), token[2]);
            	log.addToFood(f);
            	break;
            	case "addToWater":
            	waterlog w = new waterlog(check[1]);
            	log.addToWater(w);
            	break;
            	case "addToActivity":
            	Activity a = new Activity(check[1], token[1], token[2], token[3], token[4]);
            	log.addToActivity(a);
            	break;
            	case "addToWeight":
            	Weightlog e = new Weightlog(Integer.parseInt(check[1]), Float.parseFloat(token[1]), token[2]);
            	log.addToFood(e);
            	break;
            	case "addToSleep":
            	Sleep s = new Sleep(check[1], token[1]);
            	log.addToFood(s);
            	break;
            	case "summary":
            	log.summary();
            	break;
            	default:
            	break;
            }
        }
	}
}