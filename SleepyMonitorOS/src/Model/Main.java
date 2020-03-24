package Model;
/**
 * @author David Erazo, Nicolas Biojo
 * Cod: A00130528, A00137580
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String [] args) {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * semaphoreMonitor: Semaphore used to simulate the actions of the monitor; has a permission since
		 * it can only serve student at a time
		 */
		Semaphore semaphoreMonitor = new Semaphore(1, true);
		/**
		 * semaphoreWaitingRoom: Semaphore used to simulate the waiting room; has 3 permission since 
		 * it has 3 chairs.
		 */
		Semaphore semaphoreWaitingRoom = new Semaphore(3,true);
		/**
		 * numStudents: Number of students,for simulation this number can change to another one
		 */
		int numStudents = 5;
		
		/**
		 * random: Random used for waiting times
		 */
		Random random = new Random(200L);
		
		/**
		 * students: Represents the ArrayList that stores the students 
		 */
		ArrayList<Student> students =  new ArrayList<Student>();
		
		/**
		 * Cycle for create the n students
		 */
		for(int i=0; i<numStudents;i++) {
			
			Student student = new Student(semaphoreMonitor, semaphoreWaitingRoom, random);
			students.add(student);
			
		}
		
		/**
		 * Cycle for initialize student threads
		 */
		for(int i=0; i<numStudents;i++) {
			students.get(i).start();
		}
		
		
		
	}
	
}
