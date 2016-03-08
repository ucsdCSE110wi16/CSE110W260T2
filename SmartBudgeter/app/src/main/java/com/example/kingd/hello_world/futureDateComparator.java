package com.example.kingd.hello_world;

import java.util.Comparator;

/**
 * Created by KingD on 7/3/2016.
 */
public class futureDateComparator implements Comparator<Payments> {
    @Override
    public int compare(Payments a, Payments b) {
        return a.getPaymentDate().compareToIgnoreCase(b.getPaymentDate());
    }
}
