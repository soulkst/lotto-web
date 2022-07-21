package dev.kirin.toy.lottoweb.core.prediction.sorter;

import dev.kirin.toy.lottoweb.core.prediction.code.PredictionType;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionItem;

public class LowAppearSorter implements PredictionSorter {
    @Override
    public PredictionType getType() {
        return PredictionType.LOW_APPEAR;
    }

    @Override
    public int compare(PredictionItem o1, PredictionItem o2) {
        return Integer.compare(o1.getAppearCount(), o2.getAppearCount());
    }
}
