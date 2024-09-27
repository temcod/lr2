package ru.itlearn.SpringBootLr2.lr2.service;

import ru.itlearn.SpringBootLr2.lr2.model.Positions;

import java.time.Year;

public class AnnualBonusServiceImpl implements AnnualBonusService{


    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().length(); // Определение количества дней в текущем году
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }
}
