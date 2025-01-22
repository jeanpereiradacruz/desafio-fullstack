import axios from "axios";
import { FavoriteProductRequest } from "../types/FavoriteProductRequest";
import { FavoriteListRequest } from "../types/FavoriteListRequest";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    "Content-Type": "application/json"
  }
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("authToken");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    let errorMessage = "";

    if (error.response) {
      if (error.response.status === 404) {
        errorMessage = "Recurso não encontrado. Tente novamente mais tarde.";
      } else if (error.response.status === 500) {
        errorMessage = "Erro no servidor. Tente novamente mais tarde.";
      } else {
        errorMessage =
          error.response.data?.message || "Houve um erro com a requisição.";
      }
    } else if (error.request) {
      errorMessage =
        "Não foi possível se conectar ao servidor. Verifique sua conexão de internet.";
    } else {
      errorMessage = "Erro desconhecido. Tente novamente mais tarde.";
    }

    return Promise.reject(new Error(errorMessage));
  }
);

export const useApi = () => ({
  signUp: async (name: string, email: string, password: string) => {
    const response = await api.post("/clients/signup", {
      name,
      email,
      password
    });
    return response.data;
  },

  signIn: async (email: string, password: string) => {
    const response = await api.post("/clients/signin", { email, password });
    return response.data;
  },

  validateToken: async (token: string) => {
    const response = await api.post("auth/validate", { token });
    return response.data;
  },

  favoriteProduct: async (dto: FavoriteProductRequest) => {
    const response = await api.post("/favorite_products", dto);
    return response.data;
  },

  removeFavoriteProduct: async (dto: FavoriteProductRequest) => {
    const response = await api.post(`/favorite_products/remove`, dto);
    return response.data;
  },

  favoriteList: async (list: FavoriteListRequest) => {
    const response = await api.post("/favorite_lists", list);
    return response.data;
  },

  removeFavoriteList: async (clientId: string) => {
    const response = await api.post(`/favorite_lists/remove/${clientId}`);
    return response.data;
  }
});
