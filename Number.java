package application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class Number {
	private int firstProperty;
	private int secondProperty;
	private int thirdProperty;
	private int fourthProperty;
	private String fifthProperty;

	public Number(int firstProperty, int secondProperty, int thirdProperty, int fourthProperty , String fifthProperty) {
        this.firstProperty = firstProperty;
        this.secondProperty = secondProperty;
        this.thirdProperty = thirdProperty;
        this.fourthProperty = fourthProperty;
        this.fifthProperty = fifthProperty;
    }
	 public int getFirstProperty() {
	        return firstProperty;
	    }

	    public void setFirstProperty(int firstProperty ) {
	        this.firstProperty = firstProperty;
	    }

	    public int getSecondProperty() {
	        return secondProperty ;
	    }

	    public void setSecondProperty(int secondProperty) {
	        this.secondProperty = secondProperty;
	    }
	    public int getThirdProperty() {
	        return thirdProperty;
	    }

	    public void setThirdProperty(int thirdProperty ) {
	        this.thirdProperty = thirdProperty;
	    }
	    public int getFourthProperty() {
	        return fourthProperty;
	    }

	    public void setFourthProperty(int fourthProperty) {
	        this.fourthProperty = fourthProperty;
	    }

}
