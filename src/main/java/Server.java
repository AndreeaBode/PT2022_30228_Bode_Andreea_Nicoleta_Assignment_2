import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private View view;
    private String s="";
    WriteFile writeFile;
    private boolean ok=true;

    public Server(View view,WriteFile writeFile) throws IOException {
        this.tasks = new LinkedBlockingDeque<>();
        this.waitingPeriod = new AtomicInteger();
        this.view=view;
        this.writeFile= new WriteFile();
        this.writeFile.create();

    }

    //adaugarea unui task la server
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        this.waitingPeriod.getAndIncrement();
    }

    public void q1()
    {
        ok=false;

    }
    @Override
    public void run() {
        while (ok) {
            Task t = tasks.peek();// returneaza elementul din vf stivei
            if (t != null) {
                System.out.println(Thread.currentThread().getName() + ": " + tasks);
                view.setRezultatArea(Thread.currentThread().getName() + ": " + tasks+"\n");
                try {
                    writeFile.writeInFile(Thread.currentThread().getName() + ": " + tasks+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                t.setServiceTime(t.getServiceTime() - 1);
                waitingPeriod.getAndDecrement();
                if (t.getServiceTime() == 0)
                    tasks.remove(t);
            } else {
                System.out.println(Thread.currentThread().getName() + " is closed");
                view.setRezultatArea(Thread.currentThread().getName() + " is closed"+"\n");
                try {
                    writeFile.writeInFile(Thread.currentThread().getName() + " is closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    @Override
    public String toString() {
        String s = "";
        for (Task t : tasks)
            s += t.toString() + ";";
        return s;
    }
}
