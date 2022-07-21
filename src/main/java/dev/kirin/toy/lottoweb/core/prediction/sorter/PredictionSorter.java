package dev.kirin.toy.lottoweb.core.prediction.sorter;

import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionItem;
import dev.kirin.toy.lottoweb.core.prediction.code.PredictionType;

import java.util.Comparator;

public interface PredictionSorter extends Comparator<PredictionItem> {
    PredictionType getType();
}
