import "./RegisterPage.css"
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useLocalStorage } from '@rehooks/local-storage';
import Reg from "./Reg"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGoogle, faApple } from '@fortawesome/free-brands-svg-icons';

const RegisterPage = () => {
    const navigate = useNavigate()
    const [lusoAirlinesCurrentUser, setLusoAirlinesCurrentUser] = useLocalStorage("lusoAirlinesCurrentUser");

    const [userFirstName, setUserFirstName] = useState("")
    const [userLastName, setUserLastName] = useState("")
    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [userBirthDate, setUserBirthDate] = useState(new Date())


    const handleSubmit = () => {
        const newUserData = {
            userFirstName: userFirstName,
            userLastName: userLastName,
            userEmail: userEmail,
            userPassword: userPassword,
            userBirthDate: userBirthDate
        }

        console.log(newUserData)

        saveLocalStorage(newUserData)
    }

    const saveLocalStorage = (newUserData) => {
        let LusoAirlinesUsers = window.localStorage.getItem("LusoAirlinesUsers")
        if (LusoAirlinesUsers){
            LusoAirlinesUsers = JSON.parse(LusoAirlinesUsers);
            if (LusoAirlinesUsers.some(element => element.userEmail === newUserData.userEmail)){
                console.log("User already registered!")
            } else {
                LusoAirlinesUsers = [
                    ...LusoAirlinesUsers,
                    newUserData
                ]
                window.localStorage.setItem("LusoAirlinesUsers", JSON.stringify(LusoAirlinesUsers))
                setLusoAirlinesCurrentUser(newUserData)
                navigate("/")
            }

        } else {
            LusoAirlinesUsers = [
                newUserData
            ]
            window.localStorage.setItem("LusoAirlinesUsers", JSON.stringify(LusoAirlinesUsers))
            setLusoAirlinesCurrentUser(newUserData)
            navigate("/")
        }

    }

    const handleNavigateRegisterPage = () =>{
        navigate("/loginPage")
    }


    return(
        <main className="registerPage_main">
            <div className="registerPage_container">
                <section className="registerPage_title">
                    <p>
                        Register
                    </p>
                </section>

                <div class="flex flex-col items-center justify-center h-screen dark">
                    <div class="w-full max-w-md bg-gray-800 rounded-lg shadow-md p-6">
                        <form class="flex flex-col">
                        <div class="flex space-x-4 mb-4">
                            <input
                            placeholder="First Name"
                            class="bg-gray-700 text-gray-200 border-0 rounded-md p-2 w-1/2 focus:bg-gray-600 focus:outline-none focus:ring-1 focus:ring-blue-500 transition ease-in-out duration-150"
                            type="text"
                                value={userFirstName}
                                onChange={
                                    (e) => setUserFirstName(e.target.value)
                                }
                            />
                            <input
                            placeholder="Last Name"
                            class="bg-gray-700 text-gray-200 border-0 rounded-md p-2 w-1/2 focus:bg-gray-600 focus:outline-none focus:ring-1 focus:ring-blue-500 transition ease-in-out duration-150"
                            type="text"
                                value={userLastName}
                                onChange={
                                    (e) => setUserLastName(e.target.value)
                                }
                            />
                        </div>
                        <input
                            placeholder="Email"
                            class="bg-gray-700 text-gray-200 border-0 rounded-md p-2 mb-4 focus:bg-gray-600 focus:outline-none focus:ring-1 focus:ring-blue-500 transition ease-in-out duration-150"
                            type="email"
                            value={userEmail}
                            onChange={
                                (e) => setUserEmail(e.target.value)
                            }
                        />
                        <input
                            class="bg-gray-700 text-gray-200 border-0 rounded-md p-2 mb-4 focus:bg-gray-600 focus:outline-none focus:ring-1 focus:ring-blue-500 transition ease-in-out duration-150"
                            type="password"
                            placeholder="Password"
                            value={userPassword}
                            onChange={
                                (e) => setUserPassword(e.target.value)
                            }
                        />
                        <label class="text-sm mb-2 text-gray-200 cursor-pointer" for="gender">
                            Gender
                        </label>
                        <select
                            class="bg-gray-700 text-gray-200 border-0 rounded-md p-2 mb-4 focus:bg-gray-600 focus:outline-none focus:ring-1 focus:ring-blue-500 transition ease-in-out duration-150"
                            id="gender"
                        >
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                        <label class="text-sm mb-2 text-gray-200 cursor-pointer" for="age">
                            Birth Date
                        </label>
                        <input
                            class="bg-gray-700 text-gray-200 border-0 rounded-md p-2"
                            id="age"
                            type="date"
                            value={userBirthDate}
                            onChange={
                                (e) => setUserBirthDate(e.target.value)
                            }
                        />
                        <p class=" mt-4">
                            Already have an account?
                            <a class="text-sm text-blue-500 -200 hover:underline mt-4" href="#"
                                onClick={() => handleNavigateRegisterPage()}
                                className="registerPage_nav"
                            >Login</a
                            >
                        </p>
                        <button
                            class="bg-gradient-to-r from-indigo-500 to-blue-500 text-white font-bold py-2 px-4 rounded-md mt-4 hover:bg-indigo-600 hover:to-blue-600 transition ease-in-out duration-150"
                            type="submit"
                            onClick={handleSubmit}
                        >
                            Sign Up
                        </button>
                        </form>
                    </div>
                </div>


                <section className="registerPage_loginButtons">
                    <button>Continue with Google &nbsp;<FontAwesomeIcon icon={faGoogle} /> </button>
                    <button>Continue with Apple &nbsp;<FontAwesomeIcon icon={faApple} /></button>
                    
                </section>
            </div>
        </main>
    )
}

export default RegisterPage;