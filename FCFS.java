import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Number of Processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n]; // Arrival time
        int bt[] = new int[n]; // Burst time
        int ct[] = new int[n]; // Completion time
        int tat[] = new int[n]; // Turnaround time
        int wt[] = new int[n]; // Waiting time

        int st = 0; // Start time, initially 0
        float avgwt = 0, avgtat = 0;

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter the Arrival Time of Process " + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter the Burst Time of Process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1; // Process ID
        }

        // FCFS Scheduling Logic
        for (int i = 0; i < n; i++) {
            if (at[i] > st) {
                st = at[i]; // If the process arrives after the current time, the system time advances
            }
            // Completion time = current system time + burst time of the process
            ct[i] = st + bt[i];
            st += bt[i]; // Update system time after the process finishes

            // Turnaround time = Completion time - Arrival time
            tat[i] = ct[i] - at[i];

            // Waiting time = Turnaround time - Burst time
            wt[i] = tat[i] - bt[i];
        }

        // Output results
        System.out.println("\nPID | Arrival Time | Burst Time | Completion Time | Turnaround Time | Waiting Time");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += tat[i];
            System.out.println(pid[i] + "   | " + at[i] + "            | " + bt[i] + "          | " + ct[i] + "             | " + tat[i] + "              | " + wt[i]);
        }

        // Average waiting time and turnaround time
        System.out.println("\nAverage Turnaround Time: " + (float) (avgtat / n));
        System.out.println("Average Waiting Time: " + (float) (avgwt / n));

        sc.close();
    }
}
