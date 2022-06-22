package lk.ijse.hms.bo.custom;

import lk.ijse.hms.bo.SuperBO;
import lk.ijse.hms.dto.PaymentDTO;

public interface PaymentBO extends SuperBO {
    boolean addPayment(PaymentDTO paymentDTO) throws Exception;
    String getLastPaymentId() throws Exception;
}
