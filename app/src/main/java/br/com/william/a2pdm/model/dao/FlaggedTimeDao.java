package br.com.william.a2pdm.model.dao;

import java.util.ArrayList;
import java.util.List;

public class FlaggedTimeDao {

    private static final List<String> tempos = new ArrayList<>();

    public void flagTime(String tempo) {
        tempos.add(0, tempo);
    }

    public void clean() {
        for (int i = 0; i < tempos.size(); i++) {
            tempos.remove(i);
        }
    }

    public List<String> all() {
        return new ArrayList<>(tempos);
    }


}
