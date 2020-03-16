package Model;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	/**
	 * semaphoreMonitor: Semaphore used to simulate the actions of the monitor; has a permission since
	 * it can only serve student at a time
	 */
	private Semaphore semaphoreMonitor;
	/**
	 * semaphoreWaitingRoom: Semaphore used to simulate the waiting room; has 3 permission since 
	 * it has 3 chairs.
	 */
	private Semaphore semaphoreWaitingRoom;
	/**
	 * random: Random used for waiting times
	 */
	private Random random;
	
	/**
	 * This method represent the constructor
	 * @param semaphoreMonitor
	 * @param semaphoreWaitingRoom
	 * @param random
	 */
	public Student(Semaphore semaphoreMonitor,Semaphore semaphoreWaitingRoom,Random random) {
		this.semaphoreMonitor = semaphoreMonitor; 
		this.semaphoreWaitingRoom = semaphoreWaitingRoom;
		this.random = random;
	}
	
	@Override
	public void run() {
		System.out.println("The student was create, in this moment thes student is programming in the computer room");
		
		//while(true) {
			
			
			try {
				sleep((random.nextInt(16)+30)*1000);
				System.out.println("A student will try to ask for help from the monitor");
				
				if(semaphoreMonitor.availablePermits()==0) {
					
					
					if(semaphoreWaitingRoom.availablePermits()==0) {
						
						System.out.println("The monitor is busy and there are no chairs in the waiting room ");
						System.out.println("The student returns to the computer room and will return at another time");
						
					}else {
						
						semaphoreWaitingRoom.acquire();
						
						System.out.println("The monitor is busy, so the student sits in one of the waiting chairs");
						
						System.out.println("Chairs avaible: "+ semaphoreWaitingRoom.availablePermits());
					
						semaphoreMonitor.acquire();
						semaphoreWaitingRoom.release();
						
						System.out.println("A student enters the monitor's office");
						
						System.out.println("Chairs avaible: "+ semaphoreWaitingRoom.availablePermits());
						
						sleep((random.nextInt(11)+15)*1000);
						
						semaphoreMonitor.release();
						
						System.out.println("The monitor resolved the student's question");
						
						
						
					}
					
				}else {
					
					System.out.println("The monitor is asleep");
					System.out.println("A student wakes up the monitor");
					
					semaphoreMonitor.acquire();
					
					System.out.println("A student enters the monitor's office");
					
					sleep((random.nextInt(11)+15)*1000);
					
					semaphoreMonitor.release();
					
					System.out.println("The monitor resolved the student's question");	
				}
				
				if(semaphoreWaitingRoom.availablePermits()==3 && semaphoreMonitor.availablePermits()==1) {
					System.out.println("The monitor can go back to sleep");
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
	}
	
	
	
	
	
}
