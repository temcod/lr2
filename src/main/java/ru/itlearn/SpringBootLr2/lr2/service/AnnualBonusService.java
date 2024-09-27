package ru.itlearn.SpringBootLr2.lr2.service;

import org.springframework.stereotype.Service;
import ru.itlearn.SpringBootLr2.lr2.model.Positions;
@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
