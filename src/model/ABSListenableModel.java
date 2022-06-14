package model;

import java.util.HashSet;
import view.ListenerModel;

public abstract class ABSListenableModel{

    private HashSet<ListenerModel> list;

    public ABSListenableModel(){
        this.list = new HashSet<>();
    }

    public void addView(ListenerModel view){
        this.list.add(view);
    }

    public void removeView(ListenerModel view){
        this.list.remove(view);
    }

    public void update(){
        for (ListenerModel view : this.list){
            view.update();
        }
    }
}
