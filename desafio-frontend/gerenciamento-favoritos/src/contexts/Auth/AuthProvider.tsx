import { useEffect, useState } from "react";
import { AuthContext } from "./AuthContext";
import { useApi } from "../../hooks/useApi";
import { Client } from "../../types/Client";
import { FavoriteProductRequest } from "../../types/FavoriteProductRequest";
import { FavoriteListRequest } from "../../types/FavoriteListRequest";

export const AuthProvider = ({ children }: { children: JSX.Element }) => {
  const [client, setClient] = useState<Client | null>(null);
  const api = useApi();

  useEffect(() => {
    const valiteToken = async () => {
      const storageData = localStorage.getItem("authToken");
      if (storageData) {
        const data = await api.validateToken(storageData);
        if (data && !client) {
          setClient(data);
        }
      }
    };
    valiteToken();
  }, [client, api]);

  const signIn = async (email: string, password: string) => {
    const data = await api.signIn(email, password);
    if (data && data.token) {
      setClient(data);
      setToken(data.token);
      return true;
    }
    return false;
  };

  const signUp = async (name: string, email: string, password: string) => {
    const data = await api.signUp(name, email, password);
    if(data && data.token){
      setClient(data);
      setToken(data.token);
      return true;
    }
    return false;
  }

  const addFavoriteProduct = async(dto: FavoriteProductRequest) => {
    const data = await api.favoriteProduct(dto);
    if(data){
      setClient(data);
    }
    return data;
  }

  const removeFavoriteProduct = async(dto: FavoriteProductRequest) => {
    const data = await api.removeFavoriteProduct(dto);
    if(data){
      setClient(data);
    }
    return data;
  }

  const addFavoriteList = async(list: FavoriteListRequest) => {
    const data = await api.favoriteList(list);
    if(data){
      setClient(data);
    }
    return data;
  }

  const removeFavoriteList = async (clientId: string) => {
     const response = await api.removeFavoriteList(clientId);
     console.log("response apos exclusao", response)
     if(response){
      setClient(response);
     }
     return response.data;
  } 

  const signOut = async () => {
    setClient(null);
    setToken('');
  };

  const setToken = (token: string) => {
    localStorage.setItem("authToken", token);
  };

  return (
    <AuthContext.Provider value={{ client, signIn, signUp, signOut, addFavoriteProduct, removeFavoriteProduct, addFavoriteList, removeFavoriteList }}>
      {children}
    </AuthContext.Provider>
  );
};
