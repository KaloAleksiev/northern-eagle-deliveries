import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

class AuthService {
    login(email, password) {
        return axios
            .post(API_URL + "signin", { email, password })
            .then((response) => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(name, email, password, phoneNumber) {
        return axios.post(API_URL + "signup", {
            name,
            email,
            password,
            phoneNumber
        });
    }
}

export default new AuthService();