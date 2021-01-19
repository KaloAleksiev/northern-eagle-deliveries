import axios from "axios";
import authHeader from './auth-header';

const API_URL = "http://localhost:8080/";

class DeliveryService {
    newDelivery(address, weight, paid, price, senderId) {
        return axios
            .post(API_URL + "mod/newdelivery", { address, weight, paid, price, senderId }, { headers: authHeader() })
            .then().catch((err) => console.log(err));
    }

    deleteDelivery(deliveryId) {
        return axios
            .delete(API_URL + "admin/deletedelivery/" + deliveryId, { headers: authHeader() });
    }

    setSent(deliveryId) {
        return axios
            .patch(API_URL + "mod/setSent/" + deliveryId, {}, { headers: authHeader() });
    }

    setDelivered(deliveryId) {
        return axios
            .patch(API_URL + "mod/setDelivered/" + deliveryId, {}, { headers: authHeader() });
    }

    getAllDeliveries() {
        return axios
            .get(API_URL + "mod/alldeliveries", { headers: authHeader() })
            .then((response) => {
                return response.data;
            });
    }

    getDeliveriesBySenderId(senderId) {
        return axios
            .get(API_URL + "user/deliveriesbysender/" + senderId, { headers: authHeader() })
            .then((response) => {
                return response.data;
            });
    }
}

export default new DeliveryService();