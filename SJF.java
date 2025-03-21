import java.util.Scanner;

public class SJF{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Number of Processes: ");
        int n = sc.nextInt();

        int pid[]= new int[n];
        int at[]= new int[n];
        int bt[]= new int[n];
        int ct[]= new int[n];
        int tat[]= new int[n];
        int wt[]= new int[n];
        int f[]= new int[n];

        int st=0, tot = 0;

        float avgwt=0, avgtat=0;

        for(int i=0; i<n; i++){
            System.out.print("\nEnter the Arrival Time of Process "+ (i + 1)+": ");
            at[i] = sc.nextInt();
            System.out.print("\nEnter the Burst Time of Process "+ (i + 1)+": ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i]=0;
        }

        while (true) {
            int c = n, min = 99;
            if(tot == n){
                System.out.println("Please enter valid Input");
                break;
            }
                for(int i=0; i<n; i++){
                    if((at[i]<= st) && (f[i]== 0) && (bt[i]< min)){
                         min = bt[i];
                         c = i;
                    }     
                 }
            if(c == n){
                st++;
            }
            else{
                ct[c] = st + bt[c];
                st += bt[c];
                tat[c] = ct[c] - at[c];
                wt[c] = tat[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        System.out.println("\npid  arrival  brust  complete   turn    waiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += tat[i];
            System.out.println( pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }
        System.out.println("\nAverage tat is " + (float) (avgtat / n));
        System.out.println("Average wt is " + (float) (avgwt / n));
        sc.close();
    }
}