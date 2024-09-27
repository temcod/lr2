package ru.itlearn.SpringBootLr2.lr2.service;

import org.junit.jupiter.api.Test;
import ru.itlearn.SpringBootLr2.lr2.model.Positions;

import static org.junit.jupiter.api.Assertions.*;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculateQuarterlyBonus() {
        QuarterlyBonusService quarterlyBonusService = new QuarterlyBonusServiceImpl();
        Positions managerPosition = Positions.PM; // Позиция менеджера
        double salary = 100000.0; // Месячная зарплата
        double bonusPercentage = 0.25; // 25% премия
        int workDaysInQuarter = 80; // Отработано дней в квартале


        double bonus = quarterlyBonusService.calculateQuarterlyBonus(
                managerPosition, salary, bonusPercentage, workDaysInQuarter);


        int daysInQuarter = 90; // Общее количество дней в квартале
        double expectedBonus = salary * bonusPercentage * daysInQuarter * managerPosition.getPositionCoefficient() / workDaysInQuarter;
        assertEquals(expectedBonus, bonus, 0.001);
    }
}