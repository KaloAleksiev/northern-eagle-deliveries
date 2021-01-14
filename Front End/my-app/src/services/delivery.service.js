import axios from "axios";

const API_URL = "http://localhost:8080/";

class DeliveryService {
    newDelivery(address, weight, paid, senderId) {
        return axios
            .post(API_URL + "mod/newdelivery", { address, weight, paid, senderId })
            .then().catch((err) => console.log(err));
    }

    deleteDelivery(deliveryId) {
        return axios
            .delete(API_URL + "admin/deletedelivery/" + deliveryId);
    }

    setSent(deliveryId) {
        return axios
            .patch(API_URL + "mod/setSent/" + deliveryId);
    }

    setDelivered(deliveryId) {
        return axios
            .patch(API_URL + "mod/setDelivered/" + deliveryId);
    }

    trackDelivery(deliveryId) {
        return axios
            .get(API_URL + "mod/tracker/" + deliveryId)
            .then((response) => {
                return response.data;
            });
    }

    getAllDeliveries() {
        return axios
            .get(API_URL + "mod/alldeliveries")
            .then((response) => {
                return response.data;
            });
    }

    getDeliveryBySenderId(senderId) {
        return axios
            .get(API_URL + "user/deliveriesbysender/" + senderId)
            .then((response) => {
                return response.data;
            });
    }
}

export default new DeliveryService();