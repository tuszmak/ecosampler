import {useState, useEffect} from "react";

const Register = () => {
    const [newUser, setNewUser] = useState({
        name: "",
        email: "",
        password: ""
    });

    const validateSubmit = () => true;

    useEffect(() => {
        console.log(newUser);
    }, [newUser]);

    const onSubmit = (e) => {
        e.preventDefault(); // prevent the form from submitting normally
        fetch("http://localhost:8080/api/v1/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newUser)
        })
    }

    return (
        <div>
            <h1>Register: </h1>
            <form onSubmit={(e) => onSubmit(e)}>
                <label htmlFor={"name"}>Name: </label>
                <input
                    name={"name"}
                    type="text"
                    value={newUser.name}
                    onChange={(e) =>
                        setNewUser({...newUser, name: e.target.value})
                    }
                />
                <label htmlFor={"email"}>Email: </label>
                <input
                    name={"email"}
                    type={"email"}
                    value={newUser.email}
                    onChange={(e) =>
                        setNewUser({...newUser, email: e.target.value})
                    }
                />
                <label htmlFor={"password"}>Password: </label>
                <input
                    name={"password"}
                    type={"password"}
                    value={newUser.password}
                    onChange={(e) =>
                        setNewUser({...newUser, password: e.target.value})
                    }
                />
                <button type="submit" disabled={!validateSubmit()}>
                    Register
                </button>
            </form>
        </div>
    );
};


export default Register;