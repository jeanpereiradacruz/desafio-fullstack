import { FavoriteList } from "./FavoriteList";

export type Client = {
    id?: string;
    name: string;
    email: string;
    password?: string;
    favoriteList: FavoriteList
}

