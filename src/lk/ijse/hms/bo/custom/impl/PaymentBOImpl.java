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
import lk.ijse.hms.service.DataConvertor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class PaymentBOImpl implements PaymentBO {

    //DI
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    DataConvertor dataConvertor = DataConvertor.getInstance();

    public PaymentBOImpl() throws IOException {
    }

    @Override
    public boolean addPayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.add(new Payment(
                paymentDTO.getPaymentId(),paymentDTO.getDate(),paymentDTO.getMonth(),paymentDTO.getAmountToPay(),
                paymentDTO.getPaidAmount(),paymentDTO.getReservationId(),paymentDTO.getStudent()
        ));
    }

    @Override
    public String getLastPaymentId() throws Exception {
        return paymentDAO.findLastId();
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        Function<Payment,PaymentDTO> function = (p)->new PaymentDTO(
                p.getPaymentId(),p.getDate(),p.getMonth(),p.getAmountToPay(),p.getPaidAmount(),
                p.getReservationId(),p.getStudent()
        );
        return dataConvertor.convert(paymentDAO.findAll(),function);
    }
}
