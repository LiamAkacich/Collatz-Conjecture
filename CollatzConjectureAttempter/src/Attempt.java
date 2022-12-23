import java.util.Objects;

public class Attempt {
	
	private long attemptNumber;
	private long stoppingTime;
	private long highestReached;
	
	public Attempt(long attemptNumber, long stoppingTime, long highestReached) {
		this.attemptNumber = attemptNumber;
		this.stoppingTime = stoppingTime;
		this.highestReached = highestReached;
	}
	
	public Attempt(long attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public long getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(long attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public long getStoppingTime() {
		return stoppingTime;
	}

	public void setStoppingTime(long stoppingTime) {
		this.stoppingTime = stoppingTime;
	}

	public long getHighestReached() {
		return highestReached;
	}

	public void setHighestReached(long highestReached) {
		this.highestReached = highestReached;
	}

	@Override
	public String toString() {
		return "Attempt [attemptNumber=" + attemptNumber + ", stoppingTime=" + stoppingTime + ", highestReached="
				+ highestReached + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attemptNumber, highestReached, stoppingTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attempt other = (Attempt) obj;
		return attemptNumber == other.attemptNumber && highestReached == other.highestReached
				&& stoppingTime == other.stoppingTime;
	}

}
