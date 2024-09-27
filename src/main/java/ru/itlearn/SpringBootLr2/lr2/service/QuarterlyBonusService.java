package ru.itlearn.SpringBootLr2.lr2.service;
import org.springframework.stereotype.Service;
import ru.itlearn.SpringBootLr2.lr2.model.Positions;
@Service
public interface QuarterlyBonusService {

    double calculateQuarterlyBonus(Positions positions, double salary, double bonusPercentage, int workDaysInQuarter);

}
