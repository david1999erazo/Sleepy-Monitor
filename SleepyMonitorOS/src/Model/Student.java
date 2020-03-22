package Model;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	/**
	 * semaphoreMonitor: Semaphore used to simulate the actions of the monitor; has
	 * a permission since it can only serve student at a time
	 */
	private Semaphore semaphoreMonitor;
	/**
	 * semaphoreWaitingRoom: Semaphore used to simulate the waiting room; has 3
	 * permission since it has 3 chairs.
	 */
	private Semaphore semaphoreWaitingRoom;
	/**
	 * random: Random used for waiting times
	 */
	private Random random;

	/**
	 * This method represent the constructor
	 * 
	 * @param semaphoreMonitor
	 * @param semaphoreWaitingRoom
	 * @param random
	 */
	public Student(Semaphore semaphoreMonitor, Semaphore semaphoreWaitingRoom, Random random) {
		this.semaphoreMonitor = semaphoreMonitor;
		this.semaphoreWaitingRoom = semaphoreWaitingRoom;
		this.random = random;
	}

	@Override
	public void run() {
		System.out.println("The student was create, in this moment thes student is programming in the computer room");

		boolean control = true;
		while (control) {
			try {

				/**
				 * This line sleeps the thread between 20 to 26 seconds.
				 */
				sleep((random.nextInt(12) + 26) * 1000);
				System.out.println("A student will try to ask for help from the monitor");

				/**
				 * This condition check if the monitor is busy
				 */
				if (semaphoreMonitor.availablePermits() == 0) {

					/**
					 * This condition check if the chairs are available
					 */
					if (semaphoreWaitingRoom.availablePermits() == 0) {

						System.out.println("The monitor is busy and there are no chairs in the waiting room ");
						System.out.println("The student returns to the computer room and will return at another time");

					} else {

						/**
						 * This line represents when the student sits in a chair in the waiting room
						 */
						semaphoreWaitingRoom.acquire();

						System.out.println("The monitor is busy, so the student sits in one of the waiting chairs");

						System.out.println("Chairs avaible: " + semaphoreWaitingRoom.availablePermits());

						/**
						 * This line represents when the student waiting the monitor
						 */
						semaphoreMonitor.acquire();
						/**
						 * This line represents the chair empty in the waiting room
						 */
						semaphoreWaitingRoom.release();

						System.out.println("A student enters the monitor's office");

						System.out.println("Chairs avaible: " + semaphoreWaitingRoom.availablePermits());

						/**
						 * This line sleeps the thread between 30 to 40 seconds. This represent the time
						 * the monitor helps the student
						 */
						sleep((random.nextInt(16) + 30) * 1000);

						/**
						 * This line represent when the monitor is free
						 */
						semaphoreMonitor.release();

						System.out.println("The monitor resolved the student's question");
						control = false;

					}

				} else {

					System.out.println("The monitor is asleep");
					System.out.println("A student wakes up the monitor");

					/**
					 * This line represents when the student wake up the monitor
					 */
					semaphoreMonitor.acquire();

					System.out.println("A student enters the monitor's office");

					/**
					 * This line sleeps the thread between 30 to 40 seconds. This represent the time
					 * the monitor helps the student
					 */
					sleep((random.nextInt(16) + 30) * 1000);

					/**
					 * This line represent when the monitor is free
					 */
					semaphoreMonitor.release();

					System.out.println("The monitor resolved the student's question");
					control = false;
				}

				/**
				 * This condition check if the monitor is free and there is no student in the
				 * waiting room
				 */
				if (semaphoreWaitingRoom.availablePermits() == 3 && semaphoreMonitor.availablePermits() == 1) {
					System.out.println("The monitor can go back to sleep");
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
