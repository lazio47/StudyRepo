import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar/Navbar";

const DefaultLayout = (props) => {
    const { children } = props;

    return (
        <div>
            <Navbar/>
            <Outlet/>
            {children ?? null}
        </div>
    )
}

export default DefaultLayout;