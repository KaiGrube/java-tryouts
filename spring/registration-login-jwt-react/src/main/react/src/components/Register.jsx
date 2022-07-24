import React, {useState} from "react";
import axios from "axios";
import {authenticateUser} from "../state/authActions.js";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";

export default function Register() {
    const [firstName, setFirstName]  = useState("Caesar");
    const [lastName, setLastName]  = useState("Cassini");
    const [eMail, setEMail]  = useState("caesar@cassini.com");
    const [password, setPassword]  = useState("cassini");
    const [isError, setIsError] = useState(false);

    const dispatch = useDispatch();
    const navigate = useNavigate();


    async function handleSubmit() {
        setIsError(false);
        // todo: put following lines into auth actions
        try {
            axios({
                method: 'post',
                url: `/register`,
                headers: {"Content-Type": "application/json"},
                data: {
                    "firstName": firstName,
                    "lastName": lastName,
                    "email": eMail,
                    "password": password,
                }
            });
            // await dispatch(registerUser(firstName, lastName, email, password));
            alert("Registration successful. Automatically logging in now.");
            await dispatch(authenticateUser(eMail, password));
            navigate("/users");
        } catch (error) {
            setIsError(true);
        }

    }

    return (
        <div className="Register">
          <h3>Register</h3>
          <div>
              <div className="input-group">
                  <span>First name</span>
                  <input type="text"
                         name="firstName"
                         value={firstName}
                         onChange={e => setFirstName(e.target.value)}
                  />
              </div>
              <div className="input-group">
                  <span>Last name</span>
                  <input type="text"
                         name="lastName"
                         value={lastName}
                         onChange={e => setLastName(e.target.value)}
                  />
              </div>
              <div className="input-group">
                  <span>E-mail</span>
                  <input type="text"
                         name="eMail"
                         value={eMail}
                         onChange={e => setEMail(e.target.value)}
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
        </div>
    )
}