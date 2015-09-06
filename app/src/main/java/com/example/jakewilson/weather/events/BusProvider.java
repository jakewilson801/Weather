package com.example.jakewilson.weather.events;

import com.squareup.otto.Bus;

/**
 * Created by jakewilson on 9/5/15.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}