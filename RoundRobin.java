import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Process {
    int id;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;
    int startTime;
    int completionTime;

    public Process(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.startTime = -1;
        this.completionTime = 0;
    }
}

public class RoundRobin {

    public static void roundRobin(List<Process> processes, int quantum) {
        Queue<Process> queue = new LinkedList<>();
        int time = 0;

        // Adding processes to the queue initially
        for (Process p : processes) {
            queue.add(p);
        }

        // Process scheduling
        while (!queue.isEmpty()) {
            Process current = queue.poll();

            // If it's the first time the process is being executed, set the start time
            if (current.startTime == -1) {
                current.startTime = time;
            }

            // Execute the process for the quantum or remaining time (whichever is smaller)
            int timeToExecute = Math.min(current.remainingTime, quantum);
            time += timeToExecute;
            current.remainingTime -= timeToExecute;

            // If the process is completed, set the completion time
            if (current.remainingTime == 0) {
                current.completionTime = time;
                current.turnaroundTime = current.completionTime - current.startTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
            } else {
                queue.add(current); // Re-add the process back to the queue
            }

            System.out.println("Process " + current.id + " executed for " + timeToExecute + " units.");
        }

        // Output the results
        System.out.println("\nProcess Summary:");
        int totalWT = 0;
        int totalTAT = 0;
        for (Process p : processes) {
            System.out.println("Process " + p.id + " - Waiting Time: " + p.waitingTime + ", Turnaround Time: " + p.turnaroundTime);
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // Calculate average Waiting Time and Turnaround Time
        System.out.println("\nAverage Waiting Time: " + (double) totalWT / processes.size());
        System.out.println("Average Turnaround Time: " + (double) totalTAT / processes.size());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking number of processes as input
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();

        // Taking burst times as input
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes.add(new Process(i + 1, burstTime)); // Process IDs start from 1
        }

        // Taking time quantum as input
        System.out.print("Enter the time quantum: ");
        int quantum = sc.nextInt();

        // Call roundRobin method to perform scheduling and calculate times
        roundRobin(processes, quantum);

        sc.close();
    }
}
