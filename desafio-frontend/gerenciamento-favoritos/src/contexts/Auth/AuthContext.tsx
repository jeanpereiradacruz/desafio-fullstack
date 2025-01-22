import { createContext } from "react";
import { Client } from "../../types/Client";
import { FavoriteProductRequest } from "../../types/FavoriteProductRequest";
import { FavoriteListRequest } from "../../types/FavoriteListRequest";

export type AuthContextType = {
    client: Client | null;
    signIn: (email: string, password: string) => Promise<boolean>;
    signUp: (name: string, email: string, password: string) => Promise<boolean>;
    signOut: () => void;
    addFavoriteProduct: (dto: FavoriteProductRequest) => Promise<Client>;
    removeFavoriteProduct: (dto: FavoriteProductRequest) => Promise<Client>;
    addFavoriteList: (list: FavoriteListRequest) => Promise<Client>; 
    removeFavoriteList: (clientId: string) => Promise<Client>;
}

export const AuthContext = createContext<AuthContextType>(null!);