package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.CrudDAO;
import lk.ijse.hms.entity.Payment;

import java.io.IOException;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    Double getIncomeByMonth(String month) throws IOException;
}
