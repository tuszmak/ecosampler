const Login = () => {
    return (
        <div>
            <h1>Login</h1>
            <div>
                <label htmlFor={"email"}>Email: </label>
                <input name={"email"} type={"email"}/>
                <label htmlFor={"password"}>Password: </label>
                <input name={"password"} type={"password"}/>
            </div>
        </div>
    )
}

export default Login;