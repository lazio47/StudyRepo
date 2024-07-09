import { useState } from "react";
import "./LoginPage.css"
import { useNavigate } from "react-router-dom";
import { useLocalStorage } from '@rehooks/local-storage';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGoogle, faApple } from '@fortawesome/free-brands-svg-icons';


const LoginPage = () => {
    const navigate = useNavigate()
    const [lusoAirlinesCurrentUser, setLusoAirlinesCurrentUser] = useLocalStorage("lusoAirlinesCurrentUser");


    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");


    const handleSubmit = () => {
        const userData = {
            userEmail: userEmail,
            userPassword: userPassword
        }

        handleUserVerify(userData)
    }

    const handleUserVerify = (userData) => {
        console.log("userVerify!")
        let LusoAirlinesUsers = window.localStorage.getItem("LusoAirlinesUsers")

        
        if (!LusoAirlinesUsers) {
            console.log("No users found!")
            return;
        }
        
        LusoAirlinesUsers = JSON.parse(LusoAirlinesUsers);
        console.log(LusoAirlinesUsers)

        let userFound = LusoAirlinesUsers.filter((element) => element.userEmail === userData.userEmail)

        if (userFound.length === 0){
            console.log("User not found!")
            return
        }

        if (userFound[0].userPassword === userData.userPassword){
            console.log("User found!");
            setLusoAirlinesCurrentUser(userFound[0])
            navigate("/")
        } else {
            console.log("Wrong password!")
        }

    }

    const handleNavigateRegisterPage = () =>{
        navigate("/registerPage")
    }


    return(
        <main className="loginPage_main">
            <div className="loginPage_container">
                <section className="loginPage_title">
                    <p>
                        Welcome!
                    </p>
                </section>
                <section className="loginPage_input">
                    <form>
                        <div>
                            <label>Email</label>
                            <input 
                                type="email"
                                placeholder="Email"
                                onChange={
                                    (e) => setUserEmail(e.target.value)
                                }
                            />
                        </div>
                        <div>
                            <label>Password</label>
                                <input 
                                    type="password"
                                    placeholder="Password"
                                    onChange={
                                        (e) => setUserPassword(e.target.value)
                                    }
                                />
                        </div>
                    </form>
                    <div>
                        <p>Forgot Password?</p>
                    </div>
                    
                </section>


                <section className="loginPage_loginButtons">
                    <button
                        onClick={handleSubmit}
                    >Login</button>
                    <button>
                        Continue with Google &nbsp;<FontAwesomeIcon icon={faGoogle} /> 
                    </button>
                    <button>
                         Continue with Apple &nbsp;<FontAwesomeIcon icon={faApple} />
                    </button>
                </section>

                <p class=" mt-4">
                            Already have an account?
                            <a class="text-sm text-blue-500 -200 hover:underline mt-4" href="#"
                                 onClick={() => handleNavigateRegisterPage()}
                                className="registerPage_nav"
                            >Sign up!</a>
                </p>
            </div>
        </main>
    )
}

export default LoginPage;