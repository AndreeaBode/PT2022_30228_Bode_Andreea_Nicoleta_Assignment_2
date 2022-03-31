import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {

    public int timeLimit;
    public int nrServers;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private View view;
    private float averageWaitingTime;
    private int numberOfClients;;

    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;
    private WriteFile writeFile;

    public SimulationManager(View view) throws IOException {
        this.view = view;
        this.nrServers = view.getNrCoziField();
        this.numberOfClients = view.getNrClientiField();
        this.minArrival = view.getTimpSosire1Field();
        this.maxArrival = view.getTimpSosire2Field();
        this.minService = view.getTimpServire1Field();
        this.maxService = view.getTimpServire2Field();
        this.timeLimit = view.getTimeLimitField();
        this.writeFile= new WriteFile();
        this.writeFile.create();

        scheduler = new Scheduler(nrServers,view);
        generatedTasks = generateNRandomTasks(numberOfClients);
        System.out.println("clienti random: " + generatedTasks);
        this.view = view;

    }

    private List<Task> generateNRandomTasks(int nrClients) {
        Random r = new Random();

        List<Task> tasks = new ArrayList<>();
        int i = 0;
        while (i < nrClients) {
            int arrival = r.nextInt(minArrival, maxArrival);
            int service = r.nextInt(minService, maxService);
            Task t = new Task(i, arrival, service);
            tasks.add(t);
            i++;
        }
        Collections.sort(tasks);
        return tasks;

    }

    @Override
    public void run() {

        int currentTime = 0;
        float waitingTime = 0;
        while (currentTime < timeLimit) {
            System.out.println("\nTime "+currentTime);
            view.setRezultatArea("\nTime "+currentTime+"\n");
            int nr = 0;
            while (nr < generatedTasks.size()) {//daca timpul curent este egal cu arrival adaug in coada
                if (currentTime == generatedTasks.get(nr).getArrivalTime()) {
                    scheduler.dispachTask(generatedTasks.get(nr));
                    generatedTasks.remove(nr);
                    nr = 0;
                } else {
                    nr++;
                }
            }
            System.out.println("Clienti care asteapta sa intre: " + generatedTasks);
            view.setRezultatArea("Clienti care asteapta sa intre: " + generatedTasks+"\n");
            try {
                writeFile.writeInFile("Clienti care asteapta sa intre: " + generatedTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Task tasks : generatedTasks)
                waitingTime = waitingTime + tasks.getServiceTime();

            averageWaitingTime = waitingTime / view.getNrClientiField();


            try {
                writeFile.writeInFile("Average wainting time is " + averageWaitingTime+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Average wainting time is " + averageWaitingTime+"\n");
            view.setRezultatArea("Average wainting time is " + averageWaitingTime+"\n");
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      for(Server s: scheduler.getServerList())
          s.q1();
        }

    /*   if(scheduler.getServerList().isEmpty())
            if(generatedTasks.isEmpty())*/
       for(Thread thread: scheduler.getThreadsList()){
                   thread.stop();
           }
       }
    }



