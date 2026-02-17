package lk.ijse.nrlbag.dao;

import lk.ijse.nrlbag.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        CUSTOMER
    }

    public SuperDAO getDAO(DAOType daoType) {

        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }

    }

}
