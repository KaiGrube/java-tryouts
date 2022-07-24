import * as AT from "./authTypes";
import axios from "axios";

const AUTH_URL = "/login"; // todo: move to .env

function parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    let jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}

export const authenticateUser = (username, password) => async (dispatch) => {
    dispatch(loginRequest());
    try {
        const response = await axios({
            method: 'post',
            url: AUTH_URL,
            headers: {"Content-Type": "application/json"},
            data: {
                "username": username,
                "password": password,
            }
        });
        const encodedJsonWebToken = response.data.jwt;
        localStorage.setItem("jwt", encodedJsonWebToken);
        const decodedJsonWebToken = parseJwt(encodedJsonWebToken);
        console.log(decodedJsonWebToken);
        dispatch(success(true,  "username", "role"));
    } catch (exception) {
        dispatch (failure());
    }
};

export const logoutUser = () => {
    return (dispatch) => {
        dispatch(logoutRequest());
        localStorage.removeItem("jwt");
        dispatch(success({ username: "", isLoggedIn: false }));
    };
};

const loginRequest = () => {
    return {
        type: AT.LOGIN_REQUEST,
    };
};

const logoutRequest = () => {
    return {
        type: AT.LOGOUT_REQUEST,
    };
};

const success = (isLoggedIn, username, role) => {
    return {
        type: AT.SUCCESS,
        payload: {isLoggedIn, username, role}
    };
};

const failure = () => {
    return {
        type: AT.FAILURE,
        payload: false,
    };
};