package ru.itlearn.SpringBootLr2.lr2.service;

import org.springframework.stereotype.Service;
import ru.itlearn.SpringBootLr2.lr2.model.Positions;
@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {
    @Override
    public double calculateQuarterlyBonus(Positions positions, double salary, double bonusPercentage, int workDaysInQuarter) {

        if (!positions.isManager())
            throw new IllegalArgumentException("квартальная премия доступна только для менеджеров и управленцев");

        int daysInQuarter = 90;

        return salary * bonusPercentage * daysInQuarter * positions.getPositionCoefficient() / workDaysInQuarter;
    }
}
