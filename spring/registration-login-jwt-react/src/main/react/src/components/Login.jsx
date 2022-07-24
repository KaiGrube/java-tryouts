import React, {useState} from "react";
import {useDispatch} from "react-redux";
import {authenticateUser} from "../state/authActions.js";
import {useNavigate} from "react-router-dom";

export default function Login() {

    const [username, setUsername] = useState("ulf@user.com");
    const [password, setPassword] = useState("user");
    const [isError, setIsError] = useState(false);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    async function handleSubmit() {
        setIsError(false);
        try {
            await dispatch(authenticateUser(username, password));
            navigate("/users");
        } catch (error) {
            setIsError(true);
        }
    }

    return (
        <div className="Login">
            <h3>Login</h3>
            <div>
                <div className="input-group">
                    <span>E-mail</span>
                    <input type="text"
                           name="username"
                           value={username}
                           onChange={e => setUsername(e.target.value)}
                    />
                </div>
                <div className="input-group">
                    <span>Password</span>
                    <input type="password"
                           name="password"
                           value={password}
                           onChange={e => setPassword(e.target.value)}
                    />
                </div>
            </div>
            <input type="button"
                   value="Submit"
                   onClick={handleSubmit}/>

            {isError && <div>Ups, something went wrong.</div>}
        </div>
    )
}