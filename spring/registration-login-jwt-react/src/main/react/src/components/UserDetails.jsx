import React, {useEffect, useState} from "react";
import axios from "axios";
import {useSelector} from "react-redux";
import {useParams} from "react-router-dom";

export default function UserDetails() {

    let { userId } = useParams()
    const [user, setUser] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        console.log(userId)
        handleSubmit();
    }, [])

    async function handleSubmit() {
        const response = await axios({
            method: 'get',
            url: `/user/${userId}`,
            headers: {"jwt": localStorage.getItem("jwt")},
        });
        setIsLoaded(true);
        setUser(response.data)
        console.log(response.data);
    }

    return (
        <div className="UserDetails">
          <h3>UserDetails</h3>
            {
                !isLoaded
                    ? "Loading"
                    : <div key={user.id}>
                        <div>id: {user.id}</div>
                        <div>First name: {user.firstName}</div>
                        <div>Last name: {user.lastName}</div>
                        <div>E-mail: {user.email}</div>
                        <div>Password: {user.password}</div>
                        <br/>
                      </div>
            }
        </div>
    )
}