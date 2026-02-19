package lk.ijse.nrlbag.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.nrlbag.bo.BOFactory;
import lk.ijse.nrlbag.bo.custom.*;
import lk.ijse.nrlbag.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class ReportsController {

    @FXML
    private TextField ocOrderIdField;

    @FXML
    private TextField payReportIdField;

    private final String ORDER_ID_REGEX = "^[0-9]+$";
    private final OrderPopUpBO orderPupBO = (OrderPopUpBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER_POP);
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    private final MaterialBO materialBO = (MaterialBO) BOFactory.getInstance().getBO(BOFactory.BOType.MATERIAL);
    private final ProductBO productModel = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    private final OrdersBO orderBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERS);

    @FXML
    private void handleCustomerList() {
        try {
            customerBO.printCustomerList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleLowStockReport() {
        try {
            materialBO.printLowMaterialStockReport();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleMaterialStockReport() {
        try {
            materialBO.printMaterialStockReport();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleOrderConfirmationReport() {

        try {
            String orderId = ocOrderIdField.getText().trim();

            if (!orderId.matches(ORDER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Order ID").show();
            } else if (checkOrderIdHaving(Integer.parseInt(orderId))) {
                new Alert(Alert.AlertType.ERROR, "Cannot find order ID").show();
            } else {
                orderPupBO.printOrderConfirmation(Integer.parseInt(orderId));
                ocOrderIdField.clear();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleOrderPaymentReport() {
        try {
            String orderId = payReportIdField.getText().trim();

            if (!orderId.matches(ORDER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Order ID").show();
            }  else if (checkOrderIdHaving(Integer.parseInt(orderId))) {
                new Alert(Alert.AlertType.ERROR, "Cannot find order ID").show();
            } else {
                paymentBO.printOrderPaymentReceipt(Integer.parseInt(orderId));
                payReportIdField.clear();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleProductList() {
        try {
            productModel.printProductList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkOrderIdHaving(int id) {
        // in here get the all order id and check the input id is contains their
        try {
//            List<Integer> idList = ordersDAOImpl.getAllOrdersID();

            List<OrderDTO> orderList = orderBO.getOrders();

            List<Integer> idList = new ArrayList<>();

            for (OrderDTO orderDTO : orderList) {
                int orderID = orderDTO.getId();
                idList.add(orderID);
            }
            // in here return the result opposite value
            return !idList.contains(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
