import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Process {
    int pid;
    int bt;
    int wt;
    int tat;
    int priority;
    int arrivalTime;

    public Process(int pid, int bt, int priority, int arrivalTime) {
        this.pid = pid;
        this.bt = bt;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }
}

public class PriorityScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Does a lower number have higher priority? (yes/no): ");
        boolean lowerHasHigherPriority = sc.next().equalsIgnoreCase("yes");

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        
        Process[] processes = new Process[n];
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time for Process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter Burst Time for Process " + (i + 1) + ": ");
            int bt = sc.nextInt();
            System.out.print("Enter Priority for Process " + (i + 1) + ": ");
            int priority = sc.nextInt();
            
            processes[i] = new Process(i + 1, bt, priority, arrivalTime);
        }
       
        Arrays.sort(processes, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                if (p1.arrivalTime != p2.arrivalTime) {
                    return Integer.compare(p1.arrivalTime, p2.arrivalTime);
                }
                return lowerHasHigherPriority ? Integer.compare(p1.priority, p2.priority) : Integer.compare(p2.priority, p1.priority);
            }
        });

        int totalWT = 0, totalTAT = 0;
        processes[0].wt = 0;

        for (int i = 1; i < n; i++) {
            processes[i].wt = processes[i - 1].wt + processes[i - 1].bt;
        }

        for (int i = 0; i < n; i++) {
            processes[i].tat = processes[i].bt + processes[i].wt;
            totalWT += processes[i].wt;
            totalTAT += processes[i].tat;
        }

        System.out.println("\nPID\tBurst Time\tPriority\tArrival Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].pid + "\t" + processes[i].bt + "\t\t" + processes[i].priority + "\t\t" + processes[i].arrivalTime + "\t\t" + processes[i].wt + "\t\t" + processes[i].tat);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);
    }
}