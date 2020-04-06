package application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class Number {
	private int firstDigit;
	private int secondDigit;
	private int thirdDigit;
	private int fourthDigit;
	private String fifthProperty;

	public Number(int firstDigit, int secondDigit, int thirdDigit, int fourthDigit , String fifthProperty) {
        this.firstDigit = firstDigit;
        this.secondDigit = secondDigit;
        this.thirdDigit = thirdDigit;
        this.fourthDigit = fourthDigit;
        this.fifthProperty = fifthProperty;
    } 
	public Number (int number, int[] bullsAndCows) {
		fourthDigit = number % 10;
		number /= 10;
		thirdDigit = number % 10;
		number /= 10;
		secondDigit = number % 10;
		number /= 10;
		firstDigit = number % 10;
		
		fifthProperty = String.valueOf(bullsAndCows[0])+" bulls "+String.valueOf(bullsAndCows[1])+" cows";
	}
	public int getNumber() {
		return firstDigit*1000+secondDigit*100+thirdDigit*10+ fourthDigit;
	}
	public int getFirstDigit() {
	        return firstDigit;
	    }

	    public void setFirstDigit(int firstDigit ) {
	        this.firstDigit = firstDigit;
	    }

	    public int getSecondDigit() {
	        return secondDigit ;
	    }

	    public void setSecondDigit(int secondDigit) {
	        this.secondDigit = secondDigit;
	    }
	    public int getThirdDigit() {
	        return thirdDigit;
	    }

	    public void setThirdProperty(int thirdDigit ) {
	        this.thirdDigit = thirdDigit;
	    }
	    public int getFourthDigit() {
	        return fourthDigit;
	    }

	    public void setFourthDigit(int fourthDigit) {
	        this.fourthDigit = fourthDigit;
	    }
	    public String getFifthProperty() {
	        return fifthProperty;
	    }

	    public void setFifthProperty(String fifthProperty) {
	        this.fifthProperty = fifthProperty;
	    }
}
