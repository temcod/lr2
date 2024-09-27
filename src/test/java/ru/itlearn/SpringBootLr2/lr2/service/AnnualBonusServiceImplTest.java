package ru.itlearn.SpringBootLr2.lr2.service;

import org.junit.jupiter.api.Test;
import ru.itlearn.SpringBootLr2.lr2.model.Positions;

import java.time.Year;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        int daysInYear = Year.now().length(); // Определение количества дней в текущем году

        double result = new AnnualBonusServiceImpl().calculate(positions, salary, bonus, workDays);

        // Пересчет ожидаемого значения на основе актуального количества дней в году
        double expected = salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;

        assertThat(result).isEqualTo(expected);
    }

}