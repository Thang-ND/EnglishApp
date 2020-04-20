package sample;

import java.util.ArrayList;

public class History {
    private String username;
    private int[] diemthi = new int[100];
    private int now = 0;

    public History (){
        this.now = 0;
    }

    public void addHistory(int n){
        this.diemthi[now] = n;
        now++;
    }

    public String getUsername() {
        return username;
    }

    public int getdiemthi(int n){
        return this.diemthi[n];
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public void editHistory(int v, int s){
        this.diemthi[v-1] = s;
    }
}
