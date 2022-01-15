
/* Version 2.0
 * @author: Kshitij
 * Last edit at: 30 October 2021 at 6.44PM SGT
 * Last Edit by: Harsh & Sanskar*/
import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.*;

public class Reservation {
	private int tableNo;
	private LocalTime time;
	private boolean hasArrived;
	private String name;
	private long contactNo;
	private int pax;
	static int delayTime = 20; // The time after which reservation is automatically cancelled

	public Reservation() {
		tableNo = 0;
		time = LocalTime.now();
		hasArrived = false;
		name = null;
		contactNo = 0;
		pax = 0;
	}

	public Reservation(int t, LocalTime x, boolean b, String n, long cno, int p) {
		tableNo = t;
		time = x;
		hasArrived = b;
		name = n;
		contactNo = cno;
		pax = p;
	}

	// accessors
	public int getTableNo() {
		return tableNo;
	}

	public LocalTime getTimeReserved() {
		return time;
	}

	public boolean getHasArrived() {
		return hasArrived;
	}

	public String getName() {
		return name;
	}

	public long contactNo() {
		return contactNo;
	}

	public int getPax() {
		return pax;
	}

	// mutators
	public void setTableNo(int a) {
		tableNo = a;
	}

	public void setTime(LocalTime t) {
		time = t;
	}

	public void setHasArrived(boolean b) {
		hasArrived = b;
	}

	public void setName(String n) {
		name = n;
	}

	public void setContactNo(long n) {
		contactNo = n;
	}

	public void setPax(int p) {
		pax = p;
	}

	public boolean autoCancelReservation() {

		if (MINUTES.between(time, LocalTime.now()) >= delayTime && hasArrived == false
				&& time.isBefore(LocalTime.now())) {
			return true;
		} else {
			return false;
		}

	}

}