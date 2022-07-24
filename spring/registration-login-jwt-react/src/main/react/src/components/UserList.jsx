import React, {useEffect, useState} from "react";
import axios from "axios";
import {useSelector} from "react-redux";

export default function UserList() {

    const [users, setUsers] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        handleSubmit();
    }, [])

    async function handleSubmit() {
        const response = await axios({
            method: 'get',
            url: `/users`,
            headers: {"jwt": localStorage.getItem("jwt")},
        });
        setIsLoaded(true);
        setUsers(response.data)
        console.log(response.data);
    }

    return (
        <div className="UserList">
          <h3>UserList</h3>
            {
                !isLoaded || users?.length <= 0
                    ? "Loading"
                    : users.map(user =>
                        <div key={user.id}>
                            <div>id: {user.id}</div>
                            <div>First name: {user.firstName}</div>
                            <div>Last name: {user.lastName}</div>
                            <div>E-mail: {user.email}</div>
                            <div>Password: {user.password}</div>
                            <br/>
                        </div>
                      )
            }
        </div>
    )
}