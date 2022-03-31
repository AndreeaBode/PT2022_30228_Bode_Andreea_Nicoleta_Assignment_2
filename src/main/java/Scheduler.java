import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {

    private List<Server> serverList;
    private List<Thread> threads;
    private View view;
    private WriteFile writeFile;

    public Scheduler(int noOfServers, View view) throws IOException {
        this.serverList = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.view=view;

        for (int i = 0; i < noOfServers; i++)//se creaza pentru fiecare coada un Thread
        {
            Server s = new Server(view,writeFile);
            serverList.add(s);
            Thread thread = new Thread(s, "Q" + (i + 1));
            threads.add(thread);
            thread.start();
        }
    }

    public void dispachTask(Task t) { // se adauga in cea mai mica coada
        Server currentServer = serverList.get(0);
        int size = serverList.get(0).getTasks().size();
        for (Server s : serverList) {
            if (s.getTasks().size() < size) {
                currentServer = s;// se alege serverul cu cea mai mica dimensiune
                size = s.getTasks().size();
            }
        }
        //adaug serverul
        currentServer.addTask(t);
        currentServer.getWaitingPeriod().getAndSet(t.getServiceTime());


    }

    public List<Server> getServerList() {
        return serverList;
    }
    public List<Thread> getThreadsList() {
        return threads;
    }

    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
    }
}
