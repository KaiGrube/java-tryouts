import {Routes, Route, useNavigate} from "react-router-dom";
import Register from "./Register.jsx";
import Login from "./Login.jsx";
import UserList from "./UserList.jsx";
import {useDispatch} from "react-redux";
import {logoutUser} from "../state/authActions.js";
import Home from "./Home.jsx";
import {useState} from "react";
import UserDetails from "./UserDetails.jsx";

function App() {

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [userId, setUserId] = useState(0);

    async function logout() {
        await dispatch(logoutUser())
        navigate("/");
    }

    return (
      <div className="App">
          <div className="navigation">
              <input type="button"
                     value="Register"
                     onClick={() => navigate('/register')}/>
              <input type="button"
                     value="Login"
                     onClick={() => navigate('/login')}/>
              <input type="button"
                     value="Logout"
                     onClick={logout}/>
              <input type="button"
                     value="UserList"
                     onClick={() => navigate('/users')}/>

              <Routes>
                  <Route path="/register" element={<Register/>}/>
                  <Route path="/login" element={<Login/>}/>
                  <Route path="/users" element={<UserList/>}/>
                  <Route path="/user/:userId" element={<UserDetails/>}/>
                  <Route path="/" element={<Home/>}/>
              </Routes>
          </div>
      </div>
  );
}

export default App;