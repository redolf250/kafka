package org.redolf.javafx;

import javafx.concurrent.Task;

public class CounterTask extends Task<Long> {

    private final long limit;

    public CounterTask(long limit) {
        this.limit = limit;
    }

    @Override
    protected Long call() throws Exception {
        long sum =0;
        for (int i = 0; i < limit; i++) {
            sum = sum + i;
            updateValue(sum);
        }
        return sum;
    }
}
