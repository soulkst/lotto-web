package dev.kirin.toy.lottoweb.core.prediction.sorter;

import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionItem;
import dev.kirin.toy.lottoweb.core.prediction.code.PredictionType;

public class HighAppearSorter implements PredictionSorter{
    @Override
    public PredictionType getType() {
        return PredictionType.HIGH_APPEAR;
    }

    @Override
    public int compare(PredictionItem o1, PredictionItem o2) {
        return Integer.compare(o2.getAppearCount(), o1.getAppearCount());
    }
}
