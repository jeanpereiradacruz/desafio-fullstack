import { useContext } from "react";
import { AuthContext } from "./AuthContext";
import AuthForm from "../../components/AuthForm";

export const RequireAuth = ({children}: { children: JSX.Element }) => {

    const auth = useContext(AuthContext);
    
    if(!auth.client){
        return <AuthForm isLogin={false} />;
    }

    return children;
}