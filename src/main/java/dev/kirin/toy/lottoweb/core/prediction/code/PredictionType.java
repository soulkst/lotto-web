package dev.kirin.toy.lottoweb.core.prediction.code;

import dev.kirin.toy.lottoweb.core.prediction.sorter.HighAppearSorter;
import dev.kirin.toy.lottoweb.core.prediction.sorter.LowAppearSorter;
import dev.kirin.toy.lottoweb.core.prediction.sorter.PredictionSorter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PredictionType {
    HIGH_APPEAR(HighAppearSorter.class), LOW_APPEAR(LowAppearSorter.class)
    ;

    private final Class<? extends PredictionSorter> sorterClass;

    public PredictionSorter getSorter() {
        try {
            return sorterClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
