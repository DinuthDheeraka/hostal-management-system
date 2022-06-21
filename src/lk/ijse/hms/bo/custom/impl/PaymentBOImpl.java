/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/22/2022 12:08 AM
 */
package lk.ijse.hms.bo.custom.impl;

import lk.ijse.hms.bo.custom.PaymentBO;
import lk.ijse.hms.dao.DAOFactory;
import lk.ijse.hms.dao.custom.PaymentDAO;
import lk.ijse.hms.dto.PaymentDTO;
import lk.ijse.hms.entity.Payment;

import java.io.IOException;

public class PaymentBOImpl implements PaymentBO {

    //DI
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    public PaymentBOImpl() throws IOException {
    }

    @Override
    public boolean addPayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.add(new Payment(
                paymentDTO.getPaymentId(),paymentDTO.getDate(),paymentDTO.getMonth(),paymentDTO.getAmountToPay(),
                paymentDTO.getPaidAmount(),paymentDTO.getReservationId(),paymentDTO.getStudent()
        ));
    }
}
