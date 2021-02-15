package com.shailendra.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.shailendra.config.KalahaGameConfig.PITS_FOR_PLAYER;
import static com.shailendra.config.KalahaGameConfig.TOTAL_NUMBER_OF_PITS;
import static com.shailendra.util.CommonUtil.leftpad;
import static java.util.stream.Collectors.toList;

@Component
public class KalahaBoard implements Board {

    protected List<Pit> pits = new ArrayList<>();

    public KalahaBoard() {
        initBoard();
    }

    private void initBoard() {
        for (int i = 1; i <= TOTAL_NUMBER_OF_PITS; i++) {
            pits.add(new Pit(i));

            if (i % 7 == 0) {
                pits.get(i - 1).setEmpty();
                pits.get(i - 1).setBank(true);
            }
        }
    }

    public Pit getPitById(int pitId) {
        return pits.get(pitId - 1);
    }

    @Override
    public void display() {
        System.out.println(" " + getSublist(PITS_FOR_PLAYER, TOTAL_NUMBER_OF_PITS, true) + " ");
        System.out.println(pits.get(TOTAL_NUMBER_OF_PITS - 1).getNoOfMarbles() + leftpad(" ", 18) + pits.get(PITS_FOR_PLAYER - 1).getNoOfMarbles());
        System.out.println(" " + getSublist(0, PITS_FOR_PLAYER, false) + " ");
    }

    private List<String> getSublist(int fromIndex, int toIndex, boolean reversed) {
        List<String> pitsValue = pits.subList(fromIndex, toIndex - 1).stream()
                .map(Pit::getNoOfMarbles)
                .map(String::valueOf)
                .collect(toList());
        if (reversed) {
            Collections.reverse(pitsValue);
        }
        return pitsValue;
    }

    @Override
    public void reset() {
        pits.clear();
        initBoard();
    }

    public List<Pit> getPits() {
        return pits;
    }
}
