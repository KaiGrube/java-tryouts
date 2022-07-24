import { LOGIN_REQUEST, LOGOUT_REQUEST, SUCCESS, FAILURE } from "./authTypes";

const initialState = {
    username: "",
    role: null,
    isLoggedIn: false,
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN_REQUEST:
        case LOGOUT_REQUEST:
            return {
                ...state,
            };
        case SUCCESS:
        case FAILURE:
            return {
                username: action.payload.username,
                role: action.payload.role,
                isLoggedIn: action.payload.isLoggedIn,
            };
        default:
            return state;
    }
};

export default reducer;
