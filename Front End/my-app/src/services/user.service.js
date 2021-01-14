import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/board/';

class UserService {
    getPublicContent() {
        return axios.get(API_URL + 'all');
    }

    getUserBoard() {
        return axios.get(API_URL + 'customer', { headers: authHeader() });
    }

    getModeratorBoard() {
        return axios.get(API_URL + 'employee', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_URL + 'administrator', { headers: authHeader() });
    }

    getAllUsers() {
        return axios
            .get("http://localhost:8080/mod/getallusers")
            .then((response) => {
                return response.data;
            })
    }
}

export default new UserService();