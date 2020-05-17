package application;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimeThread implements Runnable{
	private GameWindow gameWindow;
	private Timeline timeline;
	boolean check = true;
	String answer = null;
	Exchanger<String> message = new Exchanger<String>();
	TimeThread(GameWindow gameWindow){
		this.gameWindow = gameWindow;
	}
	@Override
	public void run(){		
		if(gameWindow.label.getText() != null ) {		
			startPlay();
		}
	}
	private void startPlay() {
		int[] time = {05, 00}; 
		timeline = new Timeline (
				new KeyFrame (
						Duration.millis(1000 * 1), //1000 мс * 1 сек = 1 
						ae -> {      
							if(time[1]==0) {
								time[1]= 60;
								time[0]--;		
							}
							time[1]--;
							if(gameWindow.check == true) {
								timeline.stop();
								time[0] = 5; 
								time[1] = 0;
								gameWindow.label.setText("05:00");
								gameWindow.check = false;
								startPlay();
								
							}
							gameWindow.label.setText("" + time[0]+":"+time[1]);
							if(time[1] == 0 && time[0] == 0) {
								gameWindow.skipAndShowStep();
								time[0] = 0; 
								time[1] = 5;
								timeline.stop();
								gameWindow.label.setText("05:00");
								startPlay();
								
							}
							
						}
				)
		);
		timeline.setCycleCount(300); 
		timeline.play();
	}
}


