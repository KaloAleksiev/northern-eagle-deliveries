import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/';

class UserService {
    getPublicContent() {
        return axios.get(API_URL + 'board/all');
    }

    getUserBoard() {
        return axios.get(API_URL + 'board/customer', { headers: authHeader() });
    }

    getModeratorBoard() {
        return axios.get(API_URL + 'board/employee', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_URL + 'board/administrator', { headers: authHeader() });
    }

    getAllUsers() {
        return axios
            .get(API_URL + "mod/getallusers", { headers: authHeader() })
            .then((response) => {
                return response.data;
            })
    }

    makeCustomer(id) {
        return axios
            .patch(API_URL + "admin/makecustomer/" + id, {}, { headers: authHeader() })
    }

    makeEmployee(id) {
        return axios
            .patch(API_URL + "admin/makeemployee/" + id, {}, { headers: authHeader() })
    }

    makeAdmin(id) {
        return axios
            .patch(API_URL + "admin/makeadmin/" + id, {}, { headers: authHeader() })
    }

    deleteAccount(id) {
        return axios
            .delete(API_URL + "user/deleteaccount/" + id, { headers: authHeader() })
    }
}

export default new UserService();